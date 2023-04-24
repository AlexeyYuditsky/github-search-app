package com.alexeyyuditsky.github_search_app.presentation.cards

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alexeyyuditsky.github_search_app.R
import com.alexeyyuditsky.github_search_app.core.App
import com.alexeyyuditsky.github_search_app.databinding.FragmentCardsBinding
import com.alexeyyuditsky.github_search_app.presentation.FragmentRouter

class CardsFragment : Fragment(R.layout.fragment_cards) {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    private val fragmentRouter get() = (requireActivity() as FragmentRouter)
    private val viewModel by viewModels<CardsViewModel>(
        factoryProducer = { (requireActivity().application as App).cardsFactory() }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCardsBinding.bind(view)

        initRecyclerView()
        searchListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun searchListener() {
        binding.search.setOnClickListener {
            val query = checkEditText()
            if (query.isNotBlank()) viewModel.fetchCards(query)
        }
    }

    private fun checkEditText(): String {
        val query = binding.searchEditText.text?.trim().toString()
        if (query.length < 3) {
            Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show()
            return ""
        }
        return query
    }

    private fun initObserver(adapter: CardsAdapter) {
        viewModel.cardsLiveData.observe(viewLifecycleOwner) {
            checkSearchViewState(it)
            adapter.update(it)
        }
    }

    private fun checkSearchViewState(list: List<CardUi>) {
        if (list.contains(CardUi.Progress)) {
            binding.search.isEnabled = false
            binding.searchEditText.isEnabled = false
        } else {
            binding.search.isEnabled = true
            binding.searchEditText.isEnabled = true
        }
    }

    private fun initRecyclerView() {
        val cardsAdapter = CardsAdapter(
            retry = { val query = checkEditText(); if (query.isNotBlank()) viewModel.fetchCards(query) },
            content = { login, repo, path -> fragmentRouter.showContent(login, repo, path) }
        )
        binding.recyclerView.adapter = cardsAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        initObserver(cardsAdapter)
    }

}

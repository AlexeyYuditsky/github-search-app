package com.alexeyyuditsky.github_search_app.presentation.content

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alexeyyuditsky.github_search_app.R
import com.alexeyyuditsky.github_search_app.core.App
import com.alexeyyuditsky.github_search_app.core.Const
import com.alexeyyuditsky.github_search_app.core.Const.login
import com.alexeyyuditsky.github_search_app.core.Const.repo
import com.alexeyyuditsky.github_search_app.databinding.FragmentContentBinding
import com.alexeyyuditsky.github_search_app.presentation.FragmentRouter

class ContentFragment : Fragment(R.layout.fragment_content) {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    private val fragmentRouter get() = (requireActivity() as FragmentRouter)
    private val viewModel by viewModels<ContentViewModel>(
        factoryProducer = { (requireActivity().application as App).contentFactory() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.fetchContent(
                requireArguments().getString(KEY_LOGIN).toString(),
                requireArguments().getString(KEY_REPO).toString(),
                requireArguments().getString(KEY_PATH).toString()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentContentBinding.bind(view)

        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        val contentAdapter = ContentAdapter(
            dir = { path: String -> fragmentRouter.showContent(login, repo, path) },
            file = { url: String -> fragmentRouter.showWebView(url) },
            retry = { viewModel.fetchContent(login, repo, Const.pathToFile) }
        )
        binding.recyclerView.adapter = contentAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        initObserver(contentAdapter)
    }

    private fun initObserver(adapter: ContentAdapter) {
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            adapter.update(it)
        }
    }

    companion object {
        const val KEY_LOGIN = "login"
        const val KEY_REPO = "repo"
        const val KEY_PATH = "path"

        fun newInstance(login: String, repo: String, path: String): ContentFragment {
            Const.login = login
            Const.repo = repo
            Const.pathToFile += if (path.isBlank()) path else "/$path"

            return ContentFragment().apply {
                arguments = bundleOf(KEY_LOGIN to login, KEY_REPO to repo, KEY_PATH to Const.pathToFile)
            }
        }
    }

}
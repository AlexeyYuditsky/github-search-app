package com.alexeyyuditsky.github_search_app.presentation.content.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.alexeyyuditsky.github_search_app.R
import com.alexeyyuditsky.github_search_app.databinding.FragmentWebBinding

class WebViewFragment : Fragment(R.layout.fragment_web) {

    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentWebBinding.bind(view)

        binding.webView.loadUrl(requireArguments().getString(KEY_URL)!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_URL = "key"

        fun newInstance(url: String): WebViewFragment {
            return WebViewFragment().apply {
                arguments = bundleOf(KEY_URL to url)
            }
        }
    }

}
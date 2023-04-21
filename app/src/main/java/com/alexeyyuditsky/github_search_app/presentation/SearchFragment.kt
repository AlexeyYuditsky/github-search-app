package com.alexeyyuditsky.github_search_app.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.alexeyyuditsky.github_search_app.R
import com.alexeyyuditsky.github_search_app.data.search.CardsRepository
import com.alexeyyuditsky.github_search_app.data.search.cloud.ReposCloudDataSource
import com.alexeyyuditsky.github_search_app.data.search.cloud.SearchService
import com.alexeyyuditsky.github_search_app.data.search.cloud.UsersCloudDataSource
import com.alexeyyuditsky.github_search_app.databinding.FragmentSearchBinding
import com.alexeyyuditsky.github_search_app.sl.core.CoreModule
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        lifecycleScope.launch(Dispatchers.IO) {
            CardsRepository.Base(
                UsersCloudDataSource.Base(CoreModule.service),
                ReposCloudDataSource.Base(CoreModule.service)
            )
                .fetchResult("alexey")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

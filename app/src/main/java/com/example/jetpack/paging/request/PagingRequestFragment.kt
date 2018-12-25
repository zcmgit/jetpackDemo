package com.example.jetpack.paging.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.R
import com.example.jetpack.paging.request.adapter.ArticleAdapter
import kotlinx.android.synthetic.main.article_list.*

class PagingRequestFragment : Fragment() {

    val viewModel: PagingRequestViewModel by lazy {
        PagingRequestViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var articleAdapter = ArticleAdapter()

        articleList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter
        }
        viewModel.studentBuild.observe(this, Observer(articleAdapter::submitList))
    }
}
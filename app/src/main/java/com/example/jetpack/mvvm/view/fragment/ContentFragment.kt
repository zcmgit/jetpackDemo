package com.example.jetpack.mvvm.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.jetpack.R
import com.example.jetpack.databinding.ContentFragmentBinding
import com.example.jetpack.mvvm.bean.Story
import com.example.jetpack.mvvm.vm.ContentViewMode
import com.example.jetpack.tool.TitleUtil
import com.example.jetpack.tool.HtmlUtil
import kotlinx.android.synthetic.main.content_fragment.*

class ContentFragment : Fragment() {

    private lateinit var contentViewModel: ContentViewMode

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view = DataBindingUtil.inflate<ContentFragmentBinding>(inflater, R.layout.content_fragment, container, false)
        contentViewModel = ViewModelProviders.of(this).get(ContentViewMode::class.java)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webview.apply {
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        }
        contentViewModel.responses!!.observe(this, Observer {
            var htmlStr = HtmlUtil.createHtmlData(it.body, it.css, it.js)
            webview.loadDataWithBaseURL(null,htmlStr, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING,null)
        })
        var story = arguments!!.getSerializable("content") as Story
        contentViewModel.newsId.value = story.id

        initTitle(story)
    }

    fun initTitle(story: Story) {
        TitleUtil(context as Activity).setCenterTitle(story.title)
                .setLeftImgClick(View.OnClickListener {
                    Navigation.findNavController(it).popBackStack()
                })
    }
}
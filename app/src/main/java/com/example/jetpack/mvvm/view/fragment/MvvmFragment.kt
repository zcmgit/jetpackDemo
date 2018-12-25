package com.example.jetpack.mvvm.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.R
import com.example.jetpack.databinding.MvvmFragmentBinding
import com.example.jetpack.mvvm.bean.Story
import com.example.jetpack.mvvm.view.adapter.NewLatestAdapter
import com.example.jetpack.mvvm.vm.MvvmViewMode
import com.example.jetpack.tool.TitleUtil
import kotlinx.android.synthetic.main.mvvm_fragment.*

class MvvmFragment : Fragment(){

    private lateinit var mvvmViewMode: MvvmViewMode
    private lateinit var newLatestAdapter: NewLatestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view = DataBindingUtil.inflate<MvvmFragmentBinding>(inflater,R.layout.mvvm_fragment,container,false)
        mvvmViewMode = ViewModelProviders.of(this).get(MvvmViewMode::class.java)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitle()
        mvvmViewMode.responses!!.observe(this, Observer {
            mvvmViewMode.notifyDataSetChanged(it)
        })
        newLatestAdapter = mvvmViewMode.adapter.apply {
            setOnItemClickListener(object: NewLatestAdapter.OnItemClickListener{
                override fun OnItemClick(position: Int,story: Story) {
                    var bundle = Bundle()
                    bundle.putInt("content_id",position)
                    bundle.putSerializable("content",story)
                    Navigation.findNavController(view).navigate(R.id.action_mvvmFragment_to_contentFragment,bundle)
                }
            })
        }
        newsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newLatestAdapter
        }
        mvvmViewMode.isRefresh.value = true
    }


    fun initTitle(){
        TitleUtil(context as Activity)
                .setCenterTitle("热门消息")
                .setLeftImgVisible(true)
                .setLeftImgClick(View.OnClickListener {
                    Navigation.findNavController(it).popBackStack()
                })
    }
}


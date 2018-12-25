package com.example.jetpack.paging.room

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.R
import com.example.jetpack.paging.room.adapter.StudentAdapter
import com.example.jetpack.paging.room.adapter.StudentViewHolder
import com.example.jetpack.tool.TitleUtil
import kotlinx.android.synthetic.main.student_fragment.*
import org.jetbrains.anko.toast

class PagingFragment : Fragment() {

    private val viewModel: StudentViewModel by lazy {
        StudentViewModel(activity as Context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.student_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var studentAdapter = StudentAdapter()
        studentList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = studentAdapter
        }
        initTitle()
        deleteItem()
        viewModel.studentBuild.observe(this, Observer(studentAdapter::submitList))
        //添加信息
        addBtn.setOnClickListener {
            addItem()
        }

        // 当用户点击屏幕键盘上的“完成”按钮时，保存item.
        nameEd.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addItem()
                return@setOnEditorActionListener true
            }
            false
        }

        // 当用户单击按钮或按enter时，保存该 item.
        nameEd.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addItem()
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun addItem() {
        if (nameEd.text.isEmpty()) {
            context?.toast("请输入信息后重试")
        } else {
            viewModel.insert(nameEd.text.toString())
            nameEd.setText("")
        }
    }

    private fun initTitle() {
        TitleUtil(context as Activity).setCenterTitle("Pading测试")
                .setLeftImgClick(View.OnClickListener {
                    Navigation.findNavController(it).popBackStack()
                })
    }

    //删除条目
    fun deleteItem() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            //左滑/右滑
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as? StudentViewHolder)?.student?.let {
                    viewModel.delete(it)
                }
            }
        }).attachToRecyclerView(studentList)
    }
}
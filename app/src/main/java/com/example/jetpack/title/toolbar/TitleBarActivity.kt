package com.example.jetpack.title.toolbar

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import toolbar.toolbar.TitleBarHelper

open class TitleBarActivity : AppCompatActivity() {

    lateinit var mToolbarHelper: TitleBarHelper
    lateinit var toolbar: Toolbar

    lateinit var centerTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        mToolbarHelper = TitleBarHelper(this,layoutResID)
        toolbar = mToolbarHelper.getToolBar()
        setContentView(mToolbarHelper.getContentView())
        setSupportActionBar(toolbar)
        onCreateCustomToolBar(toolbar)
        centerTitle = mToolbarHelper.getCenterTitleTxt()
    }

    fun onCreateCustomToolBar(toolbar: Toolbar)
    {
        toolbar.setContentInsetsRelative(0, 0)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    //设置中间标题内容
    fun setCenterText(title: String){
        centerTitle.text = title
    }

    fun showNavigationIcon(isShow: Boolean,id: Int){
        if(isShow){
            toolbar.navigationIcon?.setVisible(true, false)
            toolbar.navigationIcon = this.resources.getDrawable(id,null)
        }else{
            toolbar.navigationIcon?.setVisible(false,false)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.getItemId() == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
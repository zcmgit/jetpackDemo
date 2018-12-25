package toolbar.toolbar;

import android.annotation.SuppressLint
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.jetpack.R

class TitleBarHelper(context: Context, layoutId: Int) {
    //上下文，创建view的时候需要用到
    lateinit var mContext: Context
    //上下文，创建view的时候需要用到
    lateinit var mContextView: RelativeLayout
    //用户定义的view
    lateinit var mUserView: View
    //toolbar
    lateinit var mToolbar: Toolbar
    //视图构造器
    var mInflater: LayoutInflater

    lateinit var centerTitle: TextView

    //两个属性:1、toolbar是否悬浮在窗口之上 2、toolbar的高度获取
    val ATTRS: IntArray = intArrayOf(R.attr.windowActionBarOverlay, R.attr.actionBarSize)

    init {
        this.mContext = context
        this.mInflater = LayoutInflater.from(mContext)
        initContentView()
        initUserView(layoutId)
        initToolbar()
    }

    //初始化toolbar
    private fun initToolbar() {
        var toolbar = mInflater.inflate(R.layout.toolbar, mContextView)
        mToolbar = toolbar.findViewById(R.id.tool_bar)
//        mToolbar.setDisplayShowTitleEnabled(false)
        centerTitle = toolbar.findViewById<TextView>(R.id.centerTitle)
    }

    //初始化用户定义的布局
    @SuppressLint("ResourceType")
    private fun initUserView(layoutId: Int) {
        mUserView = mInflater.inflate(layoutId, null)
        var param = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        var typedArray = mContext.theme.obtainStyledAttributes(ATTRS)
        var overly = typedArray.getBoolean(0, false)
        var toolbarSize = typedArray.getDimension(1, mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material))
        typedArray.recycle()
        if (overly) {
            param.topMargin = toolbarSize.toInt()
        } else {
            param.topMargin = 0
        }
        mContextView.addView(mUserView, param)
    }

    //初始化整个内容
    private fun initContentView() {
        mContextView = RelativeLayout(mContext)
        var params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        mContextView.layoutParams = params
    }

    fun getContentView(): RelativeLayout {
        return mContextView
    }

    fun getToolBar(): Toolbar {
        return mToolbar
    }

    fun getCenterTitleTxt(): TextView{
        return centerTitle
    }
}

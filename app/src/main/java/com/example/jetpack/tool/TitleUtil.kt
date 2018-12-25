package com.example.jetpack.tool

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.jetpack.R

class TitleUtil {

    private lateinit var leftImg: ImageView

    private lateinit var centerTitle: TextView

    private lateinit var rightImg: ImageView

    private lateinit var rightText: TextView

    constructor(context: Activity) {
        leftImg = context.findViewById(R.id.leftImg)
        centerTitle = context.findViewById(R.id.title)
        rightImg = context.findViewById(R.id.rightImg)
        rightText = context.findViewById(R.id.rightText)
    }

    fun setLeftImg(leftImgId: Int): TitleUtil {
        leftImg.setImageResource(leftImgId)
        return this
    }

    fun setLeftImgClick(listener: View.OnClickListener): TitleUtil {
        if (leftImg.visibility == View.VISIBLE) {
            leftImg.setOnClickListener(listener)
        }
        return this
    }

    fun setLeftImgVisible(isVisible: Boolean): TitleUtil {
        if (isVisible) {
            leftImg.visibility = View.VISIBLE
        } else {
            leftImg.visibility = View.GONE
        }
        return this
    }

    fun setCenterTitle(title: String): TitleUtil {
        centerTitle.text = title
        return this
    }

    fun setRightImg(rightImgId: Int): TitleUtil {
        rightImg.setImageResource(rightImgId)
        return this
    }

    fun setRightImgClick(listener: View.OnClickListener): TitleUtil {
        if (rightImg.visibility == View.VISIBLE) {
            rightImg.setOnClickListener(listener)
        }
        return this
    }

    fun setRightImgVisible(isVisible: Boolean): TitleUtil {
        if (isVisible) {
            rightImg.visibility = View.VISIBLE
        } else {
            rightImg.visibility = View.GONE
        }
        return this
    }

    fun setRightText(rightTxt: String): TitleUtil {
        rightText.text = rightTxt
        return this
    }

    fun setRightTextClick(listener: View.OnClickListener): TitleUtil {
        rightText.setOnClickListener(listener)
        return this
    }

    fun setRightTextVisible(isVisible: Boolean): TitleUtil {
        if (isVisible) {
            rightText.visibility = View.VISIBLE
        } else {
            rightText.visibility = View.GONE
        }
        return this
    }
}
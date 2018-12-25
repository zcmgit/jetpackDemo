package com.example.jetpack.room

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.jetpack.R
import com.example.jetpack.room.data.AppDataBase
import com.example.jetpack.room.entity.UserEntity
import com.example.jetpack.tool.TitleUtil
import kotlinx.android.synthetic.main.add_user_fragment.*

class AddUserInfoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view = inflater.inflate(R.layout.add_user_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null && arguments!!.getSerializable("user_info") != null) {
            var userInfo = arguments!!.getSerializable("user_info") as UserEntity
            edName.setText(userInfo.name)
            edAge.setText(userInfo.age.toString())
            edSex.setText(userInfo.sex)
            initTitle("修改数据")
        } else {
            initTitle("添加数据")
        }
        saveBtn.setOnClickListener {
            var name = edName.text.toString()
            var sex = edSex.text.toString()
            var age = edAge.text.toString()
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(age)) {
                Toast.makeText(context, "请输入完整信息", Toast.LENGTH_LONG).show()
            } else {
                if (arguments != null && arguments!!.getSerializable("user_info") != null) {
                    var userInfo = arguments!!.getSerializable("user_info") as UserEntity
                    userInfo.age = age.toInt()
                    userInfo.name = name
                    userInfo.sex = sex
                    AppDataBase.getUserDao(this.context!!).update(userInfo)
                } else {
                    var userEntity = UserEntity()
                    userEntity.age = age.toInt()
                    userEntity.name = name
                    userEntity.sex = sex
                    AppDataBase.getUserDao(this.context!!).insert(userEntity)
                }
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

    fun initTitle(title: String) {
        TitleUtil(context as Activity).setCenterTitle(title)
                .setLeftImgClick(View.OnClickListener {
                    Navigation.findNavController(it).popBackStack()
                })
    }
}
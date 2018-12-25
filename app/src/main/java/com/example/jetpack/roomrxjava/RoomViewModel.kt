package com.example.jetpack.roomrxjava

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.jetpack.roomrxjava.adapter.UserListAdapter
import com.example.jetpack.roomrxjava.data.AppDataBase
import com.example.jetpack.roomrxjava.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast

class RoomViewModel : ViewModel() {
    private val disposable = CompositeDisposable()
    val userAdapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    fun refreshUserList(context: Context) {
        var useerInfos = AppDataBase.getUserDao(context).searchAllInfo()
        disposable.add(useerInfos
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userAdapter.setUserInfo(it.toMutableList())
                    userAdapter.notifyDataSetChanged()
                }, {
                    context.toast("查询失败")
                }))
    }

    fun deleteAllData(context: Context) {
        disposable.add(Completable.fromAction {
            AppDataBase.getUserDao(context).deleteAll()
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userAdapter.userInfos = arrayListOf()
                    userAdapter.notifyDataSetChanged()
                },{

                }))

    }

    fun deleteData(context: Context, userEntity: UserEntity) {
        disposable.add(Completable.fromAction {
            AppDataBase.getUserDao(context).delete(userEntity)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userAdapter.userInfos.remove(userEntity)
                    userAdapter.notifyDataSetChanged()
                }, {
                    context.toast("清除失败")
                }))
    }

    fun updateData(context: Context, userEntity: UserEntity, view: View) {
        disposable.add(Completable.fromAction {
            AppDataBase.getUserDao(context).update(userEntity)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Navigation.findNavController(view).popBackStack()
                }, {
                    context.toast("更新失败")
                }))
    }

    fun insertData(context: Context, userEntity: UserEntity, view: View) {
        disposable.add(Completable.fromAction {
            AppDataBase.getUserDao(context).insert(userEntity)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Navigation.findNavController(view).popBackStack()
                }, {
                    context.toast("插入失败")
                }))
    }

    fun closeDisposable(){
        disposable.clear()
    }
}
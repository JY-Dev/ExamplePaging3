package com.example.examplepaging3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.examplepaging3.data.network.UserDataSource
import com.example.examplepaging3.models.entity.UserEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(private val userDataSource: UserDataSource) {
    private val pager = Pager(PagingConfig(10), null){
        UserPagingSource(userDataSource)
    }
    val flow = pager.flow

    fun getUser(page: Int, limit: Int): Single<List<UserEntity>> {
        return userDataSource.getUser(page, limit).map {
            it.data.map { data ->
                data.dataToEntity()
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}

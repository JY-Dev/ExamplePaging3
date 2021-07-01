package com.example.examplepaging3.data.repository

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.examplepaging3.data.network.UserDataSource
import com.example.examplepaging3.models.entity.UserEntity
import com.example.examplepaging3.models.response.UserResponse
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UserPagingSource(private val dataSource: UserDataSource) : RxPagingSource<Int, UserEntity>() {
    override fun getRefreshKey(state: PagingState<Int, UserEntity>): Int? {
        val anchorPosition = state.anchorPosition
        anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.let {
                val prevKey = it.prevKey
                prevKey?.let {
                    return prevKey + 1
                }
                val nextKey = it.nextKey
                nextKey?.let {
                    return nextKey - 1
                }

            }

        }
        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, UserEntity>> {
        val nextPageNumber = params.key?: 1
        return dataSource.getUser(nextPageNumber,10)
            .subscribeOn(Schedulers.io()).map(::toLoadResult).onErrorReturn{
            LoadResult.Error(it)
        }
    }

    private fun toLoadResult(userResponse: UserResponse)  : LoadResult<Int,UserEntity> {
        return LoadResult.Page(userResponse.data.map {
            it.dataToEntity()
        },null,userResponse.page+1)
    }
}
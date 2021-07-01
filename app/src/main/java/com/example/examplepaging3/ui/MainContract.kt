package com.example.examplepaging3.ui

import androidx.paging.PagingData
import com.example.examplepaging3.models.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface MainContract {
    interface View{
        fun updateUser(userList : List<UserEntity>)
    }
    interface Presenter {
        fun gerUser(page : Int , limit : Int)
        fun getUserFlow() : Flow<PagingData<UserEntity>>
    }
}
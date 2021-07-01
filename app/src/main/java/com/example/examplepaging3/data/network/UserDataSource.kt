package com.example.examplepaging3.data.network

import com.example.examplepaging3.models.response.UserResponse
import io.reactivex.Single

class UserDataSource {
    fun getUser(page : Int , limit : Int) : Single<UserResponse> =
        Network.dummyService.getUser("60dc24d2e2f6259b12afd117",page, limit)
}
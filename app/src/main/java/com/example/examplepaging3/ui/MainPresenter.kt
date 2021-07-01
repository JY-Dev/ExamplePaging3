package com.example.examplepaging3.ui

import androidx.paging.PagingData
import com.example.examplepaging3.data.repository.UserRepository
import com.example.examplepaging3.models.entity.UserEntity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.flow.Flow

class MainPresenter(
    private val view: MainContract.View,
    private val userRepository: UserRepository
) : MainContract.Presenter {
    private val compositeDisposable = CompositeDisposable()
    override fun gerUser(page: Int, limit: Int) {
        userRepository.getUser(page, limit).subscribe((view::updateUser),{
            it.printStackTrace()
        }).addTo(compositeDisposable)
    }

    override fun getUserFlow(): Flow<PagingData<UserEntity>> {
        return userRepository.flow
    }

}
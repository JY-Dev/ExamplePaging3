package com.example.examplepaging3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.examplepaging3.data.network.UserDataSource
import com.example.examplepaging3.data.repository.UserRepository
import com.example.examplepaging3.databinding.ActivityMainBinding
import com.example.examplepaging3.models.entity.UserEntity
import com.example.examplepaging3.ui.adapter.UserAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter: MainContract.Presenter by lazy {
        MainPresenter(
            this,
            UserRepository(UserDataSource())
        )
    }
    lateinit var binding : ActivityMainBinding
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userAdapter = UserAdapter()
        binding.userRecyclerView.adapter = userAdapter
        lifecycleScope.launch{
            presenter.getUserFlow().collectLatest {
                userAdapter.submitData(it)
            }
        }
    }

    override fun updateUser(userList: List<UserEntity>) {
        userList.forEach {
            println("UserData:$it")
        }
    }
}
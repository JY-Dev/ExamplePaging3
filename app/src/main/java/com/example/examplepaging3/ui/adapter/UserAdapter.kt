package com.example.examplepaging3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.examplepaging3.databinding.ItemUserBinding
import com.example.examplepaging3.models.entity.UserEntity

class UserAdapter : PagingDataAdapter<UserEntity, UserAdapter.UserViewHolder>(diffCallback) {
    class UserViewHolder(private val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : UserEntity?){
            item?.let { user ->
                with(binding){
                    emailTextView.text = user.email
                }
            }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context)))
    }
    companion object{
        val diffCallback  = object : DiffUtil.ItemCallback<UserEntity>(){
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem.email == newItem.email
            }

            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}
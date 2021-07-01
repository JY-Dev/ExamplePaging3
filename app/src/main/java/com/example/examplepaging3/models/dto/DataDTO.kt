package com.example.examplepaging3.models.dto

import com.example.examplepaging3.models.entity.UserEntity


data class DataDTO(
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String
){
    fun dataToEntity() : UserEntity {
        return UserEntity(email, picture)
    }
}
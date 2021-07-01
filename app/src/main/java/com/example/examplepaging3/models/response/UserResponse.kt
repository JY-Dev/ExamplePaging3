package com.example.examplepaging3.models.response

import com.example.examplepaging3.models.dto.DataDTO

data class UserResponse(
    val data: List<DataDTO>,
    val limit: Int,
    val offset: Int,
    val page: Int,
    val total: Int
)
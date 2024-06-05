package com.example.sampleapp.ui.home.viewmodel

import com.example.sampleapp.data.models.roommodel.UserTable

data class HomeState(
    val allUsersData: List<UserTable> = listOf()
)
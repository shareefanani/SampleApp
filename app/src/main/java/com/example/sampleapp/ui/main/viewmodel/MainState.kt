package com.example.sampleapp.ui.main.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.sampleapp.common.BottomNavigationScreens
import com.example.sampleapp.data.models.roommodel.UserTable

data class MainState(
    val bottomBar: BottomNavigationScreens = BottomNavigationScreens.HOME,
    val bottomTabItemLists: SnapshotStateList<BottomNavigationScreens> = mutableStateListOf(),
    val showBottomBar: Boolean = false,
    val isLoading: Boolean = false,
    val allUsersData: List<UserTable> = emptyList()
)
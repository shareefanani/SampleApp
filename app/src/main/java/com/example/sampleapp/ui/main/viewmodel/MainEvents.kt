package com.example.sampleapp.ui.main.viewmodel

import com.example.sampleapp.common.BottomNavigationScreens

sealed class MainEvents {
    data class SelectedBottomBar(val bottomBar: BottomNavigationScreens) : MainEvents()
    data class ShowBottomBar(val showBottomBar: Boolean = false) : MainEvents()
}
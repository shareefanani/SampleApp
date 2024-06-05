package com.example.sampleapp.ui.home.viewmodel

sealed class HomeEvent {
    data class ShowToastMessage(val type: String) : HomeEvent()
    data object GetAllUsers : HomeEvent()
}
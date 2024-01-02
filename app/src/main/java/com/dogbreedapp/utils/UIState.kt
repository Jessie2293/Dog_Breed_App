package com.dogbreedapp.utils



data class UIState(
    val isLoading:Boolean=false,
    val data: List<String>? = emptyList(),
    val error:String=""
)
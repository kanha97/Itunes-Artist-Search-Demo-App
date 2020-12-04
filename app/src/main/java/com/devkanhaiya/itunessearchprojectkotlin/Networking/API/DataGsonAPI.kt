package com.devkanhaiya.itunessearchprojectkotlin.Networking.API

data class DataGsonAPI(
    val resultCount: Int,
    val results: List<Result>
)
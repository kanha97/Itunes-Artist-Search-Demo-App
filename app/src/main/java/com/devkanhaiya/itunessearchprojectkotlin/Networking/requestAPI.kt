package com.devkanhaiya.itunessearchprojectkotlin.Networking

import com.devkanhaiya.itunessearchprojectkotlin.Networking.API.DataGsonAPI
import retrofit2.http.GET
import retrofit2.http.Query


interface requestAPI {


    @GET("/search")

    suspend fun getData(@Query("term") artist : String):DataGsonAPI
}
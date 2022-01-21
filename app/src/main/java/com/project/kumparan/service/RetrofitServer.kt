package com.project.kumparan.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServer {

    companion object {
        const val baseURL = "https://jsonplaceholder.typicode.com/"
    }

    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): APIRequestData {
        return getRetrofitClient().create(APIRequestData::class.java)
    }
}
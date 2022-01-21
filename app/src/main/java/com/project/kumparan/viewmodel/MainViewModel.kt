package com.project.kumparan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.kumparan.model.PostCommentModel
import com.project.kumparan.model.PostModel
import com.project.kumparan.model.UserModel
import com.project.kumparan.service.RetrofitServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    fun getAllPosts(): LiveData<List<PostModel>> {

        val mutableData = MutableLiveData<List<PostModel>>()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getAllPosts()
                .enqueue(object : Callback<List<PostModel>> {
                    override fun onResponse(
                        call: retrofit2.Call<List<PostModel>>,
                        response: Response<List<PostModel>>
                    ) {
                        if (response.isSuccessful) {
                            mutableData.value = response.body()
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")
                    }

                    override fun onFailure(call: retrofit2.Call<List<PostModel>>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

    fun getAllUsers(): LiveData<List<UserModel>> {

        val mutableData = MutableLiveData<List<UserModel>>()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getAllUsers()
                .enqueue(object : Callback<List<UserModel>> {
                    override fun onResponse(
                        call: retrofit2.Call<List<UserModel>>,
                        response: Response<List<UserModel>>
                    ) {
                        if (response.isSuccessful) {
                            mutableData.value = response.body()
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")
                    }

                    override fun onFailure(call: retrofit2.Call<List<UserModel>>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }
}
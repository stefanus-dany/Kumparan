package com.project.kumparan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.kumparan.model.AlbumModel
import com.project.kumparan.model.PostCommentModel
import com.project.kumparan.service.RetrofitServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel : ViewModel() {

    fun getUserAlbums(idUser: Int): LiveData<List<AlbumModel>> {

        val mutableData = MutableLiveData<List<AlbumModel>>()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getUserAlbums(idUser.toString())
                .enqueue(object : Callback<List<AlbumModel>> {
                    override fun onResponse(
                        call: retrofit2.Call<List<AlbumModel>>,
                        response: Response<List<AlbumModel>>
                    ) {
                        if (response.isSuccessful) {
                            mutableData.value = response.body()
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")
                    }

                    override fun onFailure(
                        call: retrofit2.Call<List<AlbumModel>>,
                        t: Throwable
                    ) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }
}
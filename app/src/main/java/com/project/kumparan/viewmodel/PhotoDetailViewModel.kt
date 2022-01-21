package com.project.kumparan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.kumparan.model.PhotoModel
import com.project.kumparan.service.RetrofitServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class PhotoDetailViewModel : ViewModel() {

    fun getAllPhotos(idAlbum: Int): LiveData<List<PhotoModel>> {

        val mutableData = MutableLiveData<List<PhotoModel>>()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getAlbumPhotos(idAlbum.toString())
                .enqueue(object : Callback<List<PhotoModel>> {
                    override fun onResponse(
                        call: retrofit2.Call<List<PhotoModel>>,
                        response: Response<List<PhotoModel>>
                    ) {
                        if (response.isSuccessful) {
                            mutableData.value = response.body()
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")
                    }

                    override fun onFailure(
                        call: retrofit2.Call<List<PhotoModel>>,
                        t: Throwable
                    ) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }
}
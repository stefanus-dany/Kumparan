package com.project.kumparan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.kumparan.model.PostCommentModel
import com.project.kumparan.service.RetrofitServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class DetailPostViewModel : ViewModel() {

    fun getAllComments(idPost: Int): LiveData<List<PostCommentModel>> {

        val mutableData = MutableLiveData<List<PostCommentModel>>()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getPostComment(idPost.toString())
                .enqueue(object : Callback<List<PostCommentModel>> {
                    override fun onResponse(
                        call: retrofit2.Call<List<PostCommentModel>>,
                        response: Response<List<PostCommentModel>>
                    ) {
                        if (response.isSuccessful) {
                            mutableData.value = response.body()
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")
                    }

                    override fun onFailure(
                        call: retrofit2.Call<List<PostCommentModel>>,
                        t: Throwable
                    ) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

}
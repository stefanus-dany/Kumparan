package com.project.kumparan.service

import com.project.kumparan.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIRequestData {

    @GET("posts")
    fun getAllPosts(): Call<List<PostModel>>

    @GET("users")
    fun getAllUsers(): Call<List<UserModel>>

    @GET("posts/{idPost}/comments")
    fun getPostComment(@Path("idPost") idPost: String): Call<List<PostCommentModel>>

    @GET("users/{idUser}/albums")
    fun getUserAlbums(@Path("idUser") idUser: String): Call<List<AlbumModel>>

    @GET("albums/{idAlbum}/photos")
    fun getAlbumPhotos(@Path("idAlbum") idAlbum: String): Call<List<PhotoModel>>

}
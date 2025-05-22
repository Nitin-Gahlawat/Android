package com.example.imageshare.di

import com.example.imageshare.data.UserDataFetch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.QueryMap


interface EditProfile {
//    @Multipart
//    @POST("edit/profile")
//    suspend fun editProfile(
//        @Part("username") username: String,
//        @Part("email") email: String,
//        @Part("username") country: String,
//        @Part("occupation") occupation: String,
//        @Part("gender") gender: String,
//        @Part("dob") dob: String,
//        @Part("phone_no") phone_no: String,
//        @Part("interests[]") interests: Array<String>,
//        @Part("hobbies[]") hobbies: Array<String>,
//        @Part("is_advice") is_advice: Int,
//        @Part("about") about: String,
//        @Part profilePic: MultipartBody.Part,
//    ): Call<BaseResponse>


    @Multipart
    @POST("edit/profile")
    suspend fun editProfileNew(
        @Part profilePic: MultipartBody.Part,
        @PartMap map: HashMap<String, RequestBody>
    ): Response<BaseResponse>

    @POST("get/profile")
    suspend fun getProfile(
        @QueryMap user_id: HashMap<String,String>
    ):Response<UserDataFetch>

}

data class BaseResponse(
    var status: String, var message: String, var statusCode: Int
)

package com.app.marier.Utils

import com.app.marier.Models.AddinterestExample.AddinterestExample
import com.app.marier.Models.AddsexualOrientation.AddSexualOrientationExample
import com.app.marier.Models.EditUserDetail.EditUserDetailExample
import com.app.marier.Models.GetAllInterest.GetallInterestExample
import com.app.marier.Models.GetCurrentUser.GetCurrentUserExample
import com.app.marier.Models.GetLikesModel.GetLikeExample
import com.app.marier.Models.GetRandomList.GetRandomUserExample
import com.app.marier.Models.GetSexualOrientation.GetSexualOrientationExample
import com.app.marier.Models.LikeUser.LikeUserExample
import com.app.marier.Models.Loginotp.LoginExample
import com.app.marier.Models.OtpVerifcation.OtpVerficationExample
import com.app.marier.Models.RegisterUser.CreateUserExample
import com.app.marier.Models.UploadGalleryImages.UploadGalleryImageExample
import com.app.marier.Models.UploadProfileImage.UploadProfileImageExample
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface API {


    @POST("users/loginOtp")
    fun loginAPI(@Body jsonObject: JsonObject):Call<LoginExample>
    @POST("users/create")
    fun createUserAPI(@Body jsonObject: JsonObject):Call<CreateUserExample>
    //otpverficationapi
    @POST("users/loginOtpVerification")
    fun otpverifcationAPI(@Header("x-access-token")token:String,@Body jsonObject: JsonObject):Call<OtpVerficationExample>
    //getsexualorientation
    @GET("sexualOrientations/getAll")
    fun getSexualOrientationAPI():Call<GetSexualOrientationExample>
    //getallinterestexample
    @GET("interests/getAll")
    fun getallinterestAPI(@Header("x-access-token")token: String):Call<GetallInterestExample>
    @Multipart
    @PUT("users/uploadImagesByUserId/{id}")
    fun uploadgalleryimages(@Part image:ArrayList<MultipartBody.Part>,@Path("id")id:String):Call<UploadGalleryImageExample>
    //addsexualorientation
    @PUT("users/addSexualOrientaion/{id}")
    fun addSexualOrientationAPI(@Header("x-access-token")token: String,@Path("id")id: String,@Body jsonObject: JsonObject):Call<AddSexualOrientationExample>
    //addinterestAPI
    @PUT("users/addIntersts/{id}")
    fun addinterestAPI(@Header("x-access-token")token: String,@Path("id")id: String,@Body jsonObject: JsonObject):Call<AddinterestExample>
    //getrandomuser
    @GET("users/randomList/{id}")
    fun getRandomuserAPI(@Header("x-access-token")token: String,@Path("id")id: String):Call<GetRandomUserExample>
    //getcurrentuser
    @GET("users/current/{id}")
    fun getcurrentUser(@Header("x-access-token")token: String,@Path("id")id: String):Call<GetCurrentUserExample>
    //updateuser
    @PUT("users/update/{id}")
    fun updateuserDataAPI(@Header("x-access-token")token: String,@Path("id")id: String,@Body jsonObject: JsonObject):Call<EditUserDetailExample>
    //uploadprofileimage
    @Multipart
    @PUT("users/profileImageUpload/{id}")
    fun uploadprofileimage(@Part image:MultipartBody.Part,@Path("id")id: String):Call<UploadProfileImageExample>
    //likeuser
    @POST("likes/create")
    fun likeuserAPI(@Body jsonObject: JsonObject):Call<LikeUserExample>
    //getlikeUser
    @GET("likes/likesByOther/{userId}")
    fun getlikeAPI(@Header("x-access-token")token: String,@Path("userId")id: String):Call<GetLikeExample>
}
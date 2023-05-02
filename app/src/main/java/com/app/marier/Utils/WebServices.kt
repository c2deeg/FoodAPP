package com.app.marier.Utils

import android.support.multidex.BuildConfig.DEBUG
import com.app.marier.Handler.*
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
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WebServices {
    private val TAG = "WebSrvices"
    private lateinit var api: API


    fun create() {
        retrofit =
            Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create())
                .client(httpClient).build()
//
        api = retrofit.create(API::class.java)


    }

    val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor())
            .addInterceptor(loggingInterceptor())
//        .authenticator(TokenAuthenticator())
            .build()
    }

    fun headerInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()

                .header("Accept", "application/json")
                .header("Platform", "android")
                .header("Content-Type", "application/json")
                .header("Cache-Control", "no-cache")

                .build()

            it.proceed(request)
        }
    }

    fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }


    companion object Factory1 {
        private lateinit var mInstance: WebServices

        private lateinit var retrofit: Retrofit

        val base_url = "https://marier.one:9001/api/v1/"


        //        val base_url = "http://93.188.167.68:8004/api/"

        internal var okHttpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .build();


        fun getInstance(): WebServices? {
            mInstance = WebServices()
            return mInstance
        }
    }

    fun apiCreate() {
        api = retrofit.create(API::class.java)
    }

    fun loginMethod(phonenum: String, loginOtpHandler: LoginOtpHandler) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("phoneNumber", phonenum)

        apiCreate()
        api?.loginAPI(jsonObject)?.enqueue(object :
            Callback<LoginExample?> {
            override fun onResponse(call: Call<LoginExample?>, response: Response<LoginExample?>) {
                if (response.code() == 200) {
                    loginOtpHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            loginOtpHandler.onError(message)
                        } else {
                            loginOtpHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }


            }

            override fun onFailure(call: Call<LoginExample?>, t: Throwable) {
                loginOtpHandler.onError(t.message)
            }

        })
    }

    fun createMethod(
        name: String,
        email: String,
        phonenum: Int,
        sex: String,
        dob: String,
        address: String,
        type: String,
        createUserHandler: CreateUserHandler
    ) {
        val arrayList: ArrayList<Double> = ArrayList()
        arrayList.add(30.7046)
        arrayList.add(76.7179)
        val jsonObject = JsonObject()
        jsonObject.addProperty("name", name)
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("password", "password")
        jsonObject.addProperty("phoneNumber", phonenum)
        jsonObject.addProperty("sex", sex)
        jsonObject.addProperty("dob", dob)
        jsonObject.addProperty("address", address)
        val jsonObject2 = JsonObject()
        jsonObject2.addProperty("type", type)
        val jsonArray1 = Gson().toJsonTree(arrayList).asJsonArray
        jsonObject2.add("coordinates", jsonArray1)
        jsonObject.add("location", jsonObject2)
        apiCreate()
        api?.createUserAPI(jsonObject)?.enqueue(object :
            Callback<CreateUserExample?> {
            override fun onResponse(
                call: Call<CreateUserExample?>,
                response: Response<CreateUserExample?>
            ) {
                if (response.code() == 200) {
                    createUserHandler.onSuccess(
                        response.body()
                    )
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            createUserHandler.onError(message)
                        } else {
                            createUserHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }


            }

            override fun onFailure(call: Call<CreateUserExample?>, t: Throwable) {
                createUserHandler.onError(t.message)
            }

        })
    }

    //otpverificationMethod
    fun otpverificationMethod(
        token: String,
        otp: String,
        otpVerificationHandler: OtpVerificationHandler
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("otp", otp)

        apiCreate()
        api?.otpverifcationAPI(token, jsonObject)?.enqueue(object :
            Callback<OtpVerficationExample?> {
            override fun onResponse(
                call: Call<OtpVerficationExample?>,
                response: Response<OtpVerficationExample?>
            ) {
                if (response.code() == 200) {
                    otpVerificationHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            otpVerificationHandler.onError(message)
                        } else {
                            otpVerificationHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }


            }

            override fun onFailure(call: Call<OtpVerficationExample?>, t: Throwable) {
                otpVerificationHandler.onError(t.message)
            }

        })
    }

    //getsexualorientation
    fun getsexualorientation(getSexualOrientationHandler: GetSexualOrientationHandler) {
        apiCreate()
        api?.getSexualOrientationAPI()?.enqueue(object :
            Callback<GetSexualOrientationExample?> {
            override fun onResponse(
                call: Call<GetSexualOrientationExample?>,
                response: Response<GetSexualOrientationExample?>
            ) {
                if (response.code() == 200) {
                    getSexualOrientationHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getSexualOrientationHandler.onError(message)
                        } else {
                            getSexualOrientationHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }


            }

            override fun onFailure(call: Call<GetSexualOrientationExample?>, t: Throwable) {
                getSexualOrientationHandler.onError(t.message)
            }

        })
    }

    //getallinterest
    fun getallinterest(token: String, getAllInterestHandler: GetAllInterestHandler) {
        apiCreate()
        api?.getallinterestAPI(token)?.enqueue(object :
            Callback<GetallInterestExample?> {
            override fun onResponse(
                call: Call<GetallInterestExample?>,
                response: Response<GetallInterestExample?>
            ) {
                if (response.code() == 200) {
                    getAllInterestHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getAllInterestHandler.onError(message)
                        } else {
                            getAllInterestHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }


            }

            override fun onFailure(call: Call<GetallInterestExample?>, t: Throwable) {
                getAllInterestHandler.onError(t.message)
            }

        })
    }

    //uploadgalleryimages
    fun uploadgalleryimages(
        images: ArrayList<MultipartBody.Part>,
        id: String,
        uploadGalleryimageHandler: UploadGalleryimageHandler
    ) {
        apiCreate()
        api?.uploadgalleryimages(images, id)?.enqueue(object :
            Callback<UploadGalleryImageExample?> {
            override fun onResponse(
                call: Call<UploadGalleryImageExample?>,
                response: Response<UploadGalleryImageExample?>
            ) {
                if (response.code() == 200) {
                    uploadGalleryimageHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            uploadGalleryimageHandler.onError(message)
                        } else {
                            uploadGalleryimageHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
//

            }

            override fun onFailure(call: Call<UploadGalleryImageExample?>, t: Throwable) {
                uploadGalleryimageHandler.onError(t.message)
            }

        })
    }

    //addsexualorientationAPI
    fun addsexualorientation(
        token: String,
        id: String,
        sexualorientation: ArrayList<String>,
        addSexualOrientationHandler: AddSexualOrientationHandler
    ) {
        apiCreate()
        val jsonObject = JsonObject()
        val jsonArray = Gson().toJsonTree(sexualorientation).asJsonArray
        jsonObject.add("sexualOrientations", jsonArray)

        api?.addSexualOrientationAPI(token, id, jsonObject)?.enqueue(object :
            Callback<AddSexualOrientationExample?> {

            override fun onResponse(
                call: Call<AddSexualOrientationExample?>,
                response: Response<AddSexualOrientationExample?>
            ) {
                if (response.code() == 200) {
                    addSexualOrientationHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            addSexualOrientationHandler.onError(message)
                        } else {
                            addSexualOrientationHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<AddSexualOrientationExample?>, t: Throwable) {
                addSexualOrientationHandler.onError(t.message)
            }

        })
    }

    //addinterestAPI
    fun addinterest(
        token: String,
        id: String,
        interest: ArrayList<String>,
        addinterestHandler: AddinterestHandler
    ) {
        apiCreate()
        val jsonObject = JsonObject()
        val jsonArray = Gson().toJsonTree(interest).asJsonArray
        jsonObject.add("interests", jsonArray)

        api?.addinterestAPI(token, id, jsonObject)?.enqueue(object :
            Callback<AddinterestExample?> {

            override fun onResponse(
                call: Call<AddinterestExample?>,
                response: Response<AddinterestExample?>
            ) {
                if (response.code() == 200) {
                    addinterestHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            addinterestHandler.onError(message)
                        } else {
                            addinterestHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<AddinterestExample?>, t: Throwable) {
                addinterestHandler.onError(t.message)
            }

        })
    }

    //getrandomuserList
    fun getrandomUser(token: String, id: String, getRandomUserHandler: GetRandomUserHandler) {
        apiCreate()
        api?.getRandomuserAPI(token, id)?.enqueue(object :
            Callback<GetRandomUserExample?> {

            override fun onResponse(
                call: Call<GetRandomUserExample?>,
                response: Response<GetRandomUserExample?>
            ) {
                if (response.code() == 200) {
                    getRandomUserHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getRandomUserHandler.onError(message)
                        } else {
                            getRandomUserHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetRandomUserExample?>, t: Throwable) {
                getRandomUserHandler.onError(t.message)
            }

        })
    }

    //getcurrentuser
    fun getcurrentuser(token: String, id: String, getCurrentUserHandler: GetCurrentUserHandler) {
        apiCreate()
        api?.getcurrentUser(token, id)?.enqueue(object :
            Callback<GetCurrentUserExample?> {

            override fun onResponse(
                call: Call<GetCurrentUserExample?>,
                response: Response<GetCurrentUserExample?>
            ) {
                if (response.code() == 200) {
                    getCurrentUserHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getCurrentUserHandler.onError(message)
                        } else {
                            getCurrentUserHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetCurrentUserExample?>, t: Throwable) {
                getCurrentUserHandler.onError(t.message)
            }

        })
    }

    //updatecurrentuserdetail
    fun updatecurrentuser(
        token: String, id: String, name: String, phoneNumber: String,
        sex: String, dob: String, address: String, to: Int, from: Int,
        distance: Int, sextype: String,
        langauge: String, updateUserDetailHandler: UpdateUserDetailHandler
    ) {
        val arrayList: ArrayList<Double> = ArrayList()
        arrayList.add(30.7046)
        arrayList.add(76.7179)
        apiCreate()
        val jsonObject = JsonObject()
        jsonObject.addProperty("name", name)
        jsonObject.addProperty("phoneNumber", phoneNumber)
        jsonObject.addProperty("sex", sex)
        jsonObject.addProperty("dob", dob)
        jsonObject.addProperty("address", address)
        val jsonobjectsetting = JsonObject()
        val jsonobjectlocation = JsonObject()
        jsonobjectlocation.addProperty("type", "Point")
        val jsonArray1 = Gson().toJsonTree(arrayList).asJsonArray
        jsonobjectlocation.add("coordinates", jsonArray1)
        val jsonobjectageRange = JsonObject()
        jsonobjectageRange.addProperty("to", to)
        jsonobjectageRange.addProperty("from", from)
        jsonobjectsetting.add("location", jsonobjectlocation)
        jsonobjectsetting.add("ageRange", jsonobjectageRange)
        jsonobjectsetting.addProperty("distance", distance)
        jsonobjectsetting.addProperty("sexType", sextype)
        jsonobjectsetting.addProperty("language", langauge)
        jsonObject.add("setting", jsonobjectsetting)

        api?.updateuserDataAPI(token, id, jsonObject)?.enqueue(object :
            Callback<EditUserDetailExample?> {
            override fun onResponse(
                call: Call<EditUserDetailExample?>,
                response: Response<EditUserDetailExample?>
            ) {
                if (response.code() == 200) {
                    updateUserDetailHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            updateUserDetailHandler.onError(message)
                        } else {
                            updateUserDetailHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<EditUserDetailExample?>, t: Throwable) {
                updateUserDetailHandler.onError(t.message)
            }

        })
    }
    //uploadprofileimage
    fun uploadprofileimage(image:MultipartBody.Part, id: String, uploadProfileImageHandler: UploadProfileImageHandler) {
        apiCreate()
        api?.uploadprofileimage(image, id)?.enqueue(object :
            Callback<UploadProfileImageExample?> {

            override fun onResponse(
                call: Call<UploadProfileImageExample?>,
                response: Response<UploadProfileImageExample?>
            ) {
                if (response.code() == 200) {
                    uploadProfileImageHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            uploadProfileImageHandler.onError(message)
                        } else {
                            uploadProfileImageHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<UploadProfileImageExample?>, t: Throwable) {
                uploadProfileImageHandler.onError(t.message)
            }

        })
    }
    //likeuser
    fun likeuser(likeBy:String,likeTo:String,isSuperLike:String, likeUserHandler: LikeUserHandler) {
        apiCreate()
        val jsonObject =JsonObject()
        jsonObject.addProperty("likeBy",likeBy)
        jsonObject.addProperty("likeTo",likeTo)
        jsonObject.addProperty("isSuperLike",isSuperLike)
        api?.likeuserAPI(jsonObject)?.enqueue(object :
            Callback<LikeUserExample?> {

            override fun onResponse(
                call: Call<LikeUserExample?>,
                response: Response<LikeUserExample?>
            ) {
                if (response.code() == 200) {
                    likeUserHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            likeUserHandler.onError(message)
                        } else {
                            likeUserHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<LikeUserExample?>, t: Throwable) {
                likeUserHandler.onError(t.message)
            }

        })
    }
    //getlikeAPI
    fun getlikeMethod(token:String,Userid:String, getLikeHandler: GetLikeHandler) {
        apiCreate()

        api?.getlikeAPI(token,Userid)?.enqueue(object :
            Callback<GetLikeExample?> {

            override fun onResponse(
                call: Call<GetLikeExample?>,
                response: Response<GetLikeExample?>
            ) {
                if (response.code() == 200) {
                    getLikeHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getLikeHandler.onError(message)
                        } else {
                            getLikeHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetLikeExample?>, t: Throwable) {
                getLikeHandler.onError(t.message)
            }

        })
    }





}
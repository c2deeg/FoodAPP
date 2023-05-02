package com.app.marier.Models.OtpVerifcation

data class OtpVerficationExample(
    val `data`: Data,
    val isSuccess: Boolean,
    val message: String,
    val statusCode: Int,
    val token: String
)
package com.app.marier.Handler

import com.app.marier.Models.OtpVerifcation.OtpVerficationExample
import com.app.marier.Models.RegisterUser.CreateUserExample

interface OtpVerificationHandler {
    fun onSuccess(otpVerficationExample: OtpVerficationExample?)
    fun onError(message: String?)
}
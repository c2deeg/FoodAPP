package com.app.marier.Handler

import com.app.marier.Models.Loginotp.LoginExample

interface LoginOtpHandler {
    fun onSuccess(loginExample: LoginExample?)
    fun onError(message: String?)
}
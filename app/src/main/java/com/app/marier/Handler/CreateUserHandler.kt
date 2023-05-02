package com.app.marier.Handler

import com.app.marier.Models.RegisterUser.CreateUserExample

interface CreateUserHandler {
    fun onSuccess(loginExample: CreateUserExample?)
    fun onError(message: String?)
}
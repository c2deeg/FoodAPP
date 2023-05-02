package com.app.marier.Handler

import com.app.marier.Models.GetCurrentUser.GetCurrentUserExample
import com.app.marier.Models.GetRandomList.GetRandomUserExample

interface GetCurrentUserHandler {
    fun onSuccess(getCurrentUserExample: GetCurrentUserExample?)
    fun onError(message: String?)
}
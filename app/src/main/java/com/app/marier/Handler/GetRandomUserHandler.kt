package com.app.marier.Handler

import com.app.marier.Models.AddsexualOrientation.AddSexualOrientationExample
import com.app.marier.Models.GetRandomList.GetRandomUserExample

interface GetRandomUserHandler {
    fun onSuccess(getRandomUserExample: GetRandomUserExample?)
    fun onError(message: String?)
}
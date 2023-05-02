package com.app.marier.Handler

import com.app.marier.Models.AddsexualOrientation.AddSexualOrientationExample

interface AddSexualOrientationHandler {
    fun onSuccess(addsexualOrientationExample: AddSexualOrientationExample?)
    fun onError(message: String?)
}
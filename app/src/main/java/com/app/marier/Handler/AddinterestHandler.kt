package com.app.marier.Handler

import com.app.marier.Models.AddinterestExample.AddinterestExample
import com.app.marier.Models.AddsexualOrientation.AddSexualOrientationExample

interface AddinterestHandler {
    fun onSuccess(addinterestExample: AddinterestExample?)
    fun onError(message: String?)
}


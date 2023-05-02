package com.app.marier.Handler

import com.app.marier.Models.GetSexualOrientation.GetSexualOrientationExample
import com.app.marier.Models.OtpVerifcation.OtpVerficationExample

interface GetSexualOrientationHandler {
    fun onSuccess(getSexualOrientationExample: GetSexualOrientationExample?)
    fun onError(message: String?)
}
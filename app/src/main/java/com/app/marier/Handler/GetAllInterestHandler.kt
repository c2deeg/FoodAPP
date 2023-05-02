package com.app.marier.Handler

import com.app.marier.Models.GetAllInterest.GetallInterestExample
import com.app.marier.Models.GetSexualOrientation.GetSexualOrientationExample

interface GetAllInterestHandler {
    fun onSuccess(getallInterestExample: GetallInterestExample?)
    fun onError(message: String?)
}
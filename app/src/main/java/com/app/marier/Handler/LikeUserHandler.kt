package com.app.marier.Handler

import com.app.marier.Models.GetSexualOrientation.GetSexualOrientationExample
import com.app.marier.Models.LikeUser.LikeUserExample

interface LikeUserHandler {
    fun onSuccess(likeUserExample: LikeUserExample?)
    fun onError(message: String?)
}
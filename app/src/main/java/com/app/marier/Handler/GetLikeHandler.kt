package com.app.marier.Handler

import com.app.marier.Models.GetLikesModel.GetLikeExample
import com.app.marier.Models.LikeUser.LikeUserExample

interface GetLikeHandler {
    fun onSuccess(getLikeExample: GetLikeExample?)
    fun onError(message: String?)
}
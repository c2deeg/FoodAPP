package com.app.marier.Handler

import com.app.marier.Models.UploadGalleryImages.UploadGalleryImageExample
import com.app.marier.Models.UploadProfileImage.UploadProfileImageExample

interface UploadProfileImageHandler {
    fun onSuccess(uploadProfileImageExample: UploadProfileImageExample?)
    fun onError(message: String?)
}
package com.app.marier.Handler

import com.app.marier.Models.OtpVerifcation.OtpVerficationExample
import com.app.marier.Models.UploadGalleryImages.UploadGalleryImageExample

interface UploadGalleryimageHandler {
    fun onSuccess(uploadGalleryImageExample: UploadGalleryImageExample?)
    fun onError(message: String?)
}
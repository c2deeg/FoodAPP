package com.app.marier.fragments.AddPhotosFragment.Presenter

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.marier.Handler.UploadGalleryimageHandler
import com.app.marier.Models.UploadGalleryImages.UploadGalleryImageExample
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.fragments.AddPhotosFragment.AddPhotosFragment
import com.app.marier.fragments.AddPhotosFragment.View.AddPhotosView
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class AddPhotosPresenter(
   private val activity: FragmentActivity,
    private val addPhotosFragment: AddPhotosFragment,
   private val addPhotosView: AddPhotosView
) {

    fun addgalleryimages(uri: ArrayList<Uri>) {
        var multiParts: ArrayList<MultipartBody.Part> = ArrayList<MultipartBody.Part>()
        val bitmapList = ArrayList<Bitmap>()


        for (uri in uri) {
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(activity.contentResolver, uri)
                bitmapList.add(bitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        for (bitmap in bitmapList) {
            // Convert the bitmap to a File object
            val file = bitmapToFile(bitmap, activity)


            // Create a new RequestBody for the file
            val requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file)

            // Create a new MultipartBody.Part for the file
            val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

            // Add the MultipartBody.Part to the ArrayList of image parts
            multiParts.add(body)
        }
        var id = CSPreferences.readString(activity, Utils.USERID)

        if (Utils.isNetworkConnected(activity!!)) {
            addPhotosView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.uploadgalleryimages(multiParts!!,id!!, object :
                UploadGalleryimageHandler {

                override fun onSuccess(uploadGalleryImageExample: UploadGalleryImageExample?) {
                    addPhotosView.hideDialog()
                    addPhotosView.showMessage(activity,uploadGalleryImageExample?.message)
                }

                override fun onError(message: String?) {
                    addPhotosView.hideDialog()
                    addPhotosView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
    fun bitmapToFile(bitmap: Bitmap, context: Context): File {
        // Get the cache directory
        val cacheDir = context.cacheDir

        // Create a temporary file in the cache directory
        val file = File.createTempFile("temp_image", null, cacheDir)

        // Use a FileOutputStream to write the bitmap to the file
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        fos.flush()
        fos.close()

        return file
    }

}
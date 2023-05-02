package com.app.marier.activities.InviteFriendScreenActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.marier.R
import com.app.marier.databinding.ActivityInviteFriendScreenBinding

class InviteFriendScreenActivity : AppCompatActivity(), View.OnClickListener {
    private var binding:ActivityInviteFriendScreenBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInviteFriendScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        listeners()
    }

    private fun listeners(){
        binding?.linshare?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.linshare->{
                val appLink = "https://example.com/app" // Replace with your app link
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, appLink)
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }
    }

//    private fun shareImage() {
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.type = "image/*"
//        val saveImageUri = mSaveImageUri
//        if (saveImageUri == null) {
//            showSnackbar(getString(R.string.msg_save_image_to_share))
//            return
//        }
//        intent.putExtra(Intent.EXTRA_STREAM, buildFileProviderUri(saveImageUri))
//        startActivity(Intent.createChooser(intent, getString(R.string.msg_share_image)))
//    }

}
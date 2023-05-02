package com.app.marier.activities.ChatDeatilActivity

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.marier.R
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.ChatApplication.ChatApplication
import com.app.marier.Utils.Messages
import com.app.marier.Utils.Utils
import com.app.marier.activities.Videocallactivity.Videocallactivity
import com.app.marier.adapter.ChatAdapterRecyclerview
import com.app.marier.databinding.ActivityChatDeatilBinding
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ChatDeatilActivity : AppCompatActivity(), View.OnClickListener {
    private var binding:ActivityChatDeatilBinding?=null
    private var messageList: ArrayList<Messages> = ArrayList()
    private var chatAdapterRecyclerview: ChatAdapterRecyclerview? = null
    var mSocket: com.github.nkzawa.socketio.client.Socket? = null
    var activity: Activity? = null
    private var userid:String?=null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = ChatApplication()
        mSocket = app.getmSocket()
//        mSocket.connect();

        //        mSocket.connect();
        mSocket!!.on(Socket.EVENT_CONNECT, onConnect)
        mSocket!!.on(Socket.EVENT_CONNECT_ERROR, onConnectionError)
        //  mSocket.on("set-user-data",)
        //  mSocket.on("set-user-data",)
        mSocket!!.on("set-room", onGettingRoomId)
        mSocket!!.on("chat-msg", onNewMessage)
        //mSocket.on("typing", onTyping);
        //mSocket.on("typing", onTyping);
        mSocket!!.connect()
        Log.d(ContentValues.TAG, "socket" + mSocket!!.connected())
        mSocket = app.getmSocket()
        binding = ActivityChatDeatilBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initrecyclerview()
        listeners()
        userid = CSPreferences.readString(this,Utils.USERID)



    }


    private fun initrecyclerview(){
        chatAdapterRecyclerview = ChatAdapterRecyclerview(messageList)
        binding!!.chatrecyclerview!!.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding!!.chatrecyclerview?.adapter = chatAdapterRecyclerview
    }


    private fun listeners(){
        binding!!.imgsend.setOnClickListener(this)
        binding!!.imgback.setOnClickListener(this)
        binding!!.linvideocall.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.imgsend->attemptSend()
            R.id.imgback->finish()
            R.id.linvideocall->{
                var intent = Intent(this,Videocallactivity::class.java)
                startActivity(intent)

            }
        }
    }


    private fun attemptSend() {
//        if (!mSocket.connected()) return
        val message: String = binding!!.etmsg?.getText().toString()
        if (TextUtils.isEmpty(message)) {
            binding!!.etmsg?.requestFocus()
            return
        }
        binding!!.etmsg?.setText("")
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val strDate = mdformat.format(calendar.time)
        val jsonObject = JSONObject()
        try {
            jsonObject.put("msg", message)
            jsonObject.put("msgTo", "msgTo")
            jsonObject.put("date", strDate)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        Log.d(ContentValues.TAG, "$jsonObject   emit this")
        addMessage(strDate, message, Messages.TYPE_MESSAGE)
//        mSocket?.emit("chat-msg", jsonObject)
    }


    private fun addMessage(time: String, message: String, type: Int) {
        messageList.add(Messages.Builder(type).time(time).message(message).build())
        chatAdapterRecyclerview!!.notifyItemInserted(messageList!!.size - 1)
//        scrollToBottom();
    }


    var onConnect = Emitter.Listener {
        Log.d(ContentValues.TAG, "Socket Connected!" + mSocket!!.connected())
        activity!!.runOnUiThread {
            Toast.makeText(activity, "connected", Toast.LENGTH_SHORT).show()
            //                    if (!isconnected) {
            //                        Snackbar s = Snackbar.make(binding.getRoot(), "Loading..", Snackbar.LENGTH_LONG);
            //                        s.getView().setBackgroundColor(binding.getRoot().getResources().getColor(R.color.orange));
            //                        isconnected = true;
            //                        s.show();
            //                    }
        }
    }


    private val onConnectionError = Emitter.Listener {
        activity!!.runOnUiThread {
            Toast.makeText(activity, "Connection error", Toast.LENGTH_SHORT).show()
        }
    }


    private val onGettingRoomId = Emitter.Listener {
        activity!!.runOnUiThread {
            var roomId = it[0].toString();
            Log.d(" checkroomid", roomId)
//            chatDetailPresenter?.getOldchat(roomId)
            //                    if (!roomId.isEmpty()) {
            //                        //  getOldChats(roomId);
            //                    }
        }
    }


    private val onNewMessage =
        Emitter.Listener { args ->
            if (activity != null) {
                activity!!.runOnUiThread(Runnable {
//                    chatrecyclerview!!.post {
//                        chatrecyclerview!!.scrollToPosition(chatAdapterRecyclerview!!.itemCount - 1)
//                        // Here adapter.getItemCount()== child count
//                    }
                    val jsonObject = args[0] as JSONObject

//                    if (jsonObject.has("msgFrom") && jsonObject.optString("msgFrom")
//                            .equals(toUser, ignoreCase = true)
                    var msgFrom = jsonObject.getString("msgFrom")
                    if (msgFrom != userid) {
                        val time: String
                        val message: String
                        try {
                            time = jsonObject.getString("date")
                            message = jsonObject.getString("msg")
                            Toast.makeText(
                                activity,
                                jsonObject.getString("msgFrom"),
                                Toast.LENGTH_SHORT
                            ).show()

                            Log.d(ContentValues.TAG, "$message   received msg")
                            Log.d("Checkrecivedmsg", "$message   received msg")
                        } catch (e: JSONException) {
                            return@Runnable
                        }
                        addMessage(time, message, Messages.TYPE_MESSAGE_REMOTE)
                    } else {
                        Log.d("error", "Error")
                    }
//
                })
            }
        }


    override fun onStart() {
        super.onStart()
        if (!mSocket!!.connected()) {
            mSocket!!.connect()
            Log.d(ContentValues.TAG, "onStart: " + mSocket!!.connect())
        }
        mSocket!!.emit("set-user-data", userid)
    }

    override fun onResume() {
        super.onResume()
        mSocket!!.connect()
//        username = CSPreferences.readString(activity as ChatDetailActivity, Utils.USERNAME)
//        username = CSPreferences.readString(activity, Utils.USERNAME)
//        userId = CSPreferences.readString(activity, Utils.USERID)

//        toUser =  PreferenceHelper.getInstance(activity).getfriendname();
//        val currentRoom: String = username + "-" + toUser
////        val currentRoom: String = username + "-" + "employee"
//        val reverseRoom = "$toUser-$username"
//        val reverseRoom = "employee" + "-" + username
//        Log.d("chchhchc",currentRoom)
//        Log.d(ContentValues.TAG, "current room " + currentRoom + "reverse room " + reverseRoom)
        val jsonObject = JSONObject()
        try {
            jsonObject.put("name1", userid + "-" + "msgTo")
            jsonObject.put("name2", "msgTo" + "-" + userid)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        mSocket!!.emit("set-room", jsonObject)
        mSocket!!.emit("set-user-data", "username")
//        mSocket!!.on("set-room",onGettingRoomId)

    }
}
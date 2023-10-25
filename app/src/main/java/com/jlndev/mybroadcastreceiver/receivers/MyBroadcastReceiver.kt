package com.jlndev.mybroadcastreceiver.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jlndev.mybroadcastreceiver.view.MainActivity

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val i = Intent(context, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(i)
    }

}
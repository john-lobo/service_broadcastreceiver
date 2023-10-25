package com.jlndev.mybroadcastreceiver.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

// Precisa implementar pela activity
class AirPlaneBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent!!.action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                try {
                    val isAirplaneModeOn = intent.getBooleanExtra("state", false)
                    if (isAirplaneModeOn) {
                        Toast.makeText(context, "Você ativou o modo avião!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Você desativou o modo avião!", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Ocorreu um erro, tente novamente", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
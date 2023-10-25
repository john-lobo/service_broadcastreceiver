package com.jlndev.mybroadcastreceiver.receivers

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

// Pode ser implementado pelo manifesto
class BluetoothBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent!!.action) {
            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                when (state) {
                    BluetoothAdapter.STATE_ON -> {
                        Toast.makeText(context, "Você ativou o Bluetooth!", Toast.LENGTH_SHORT).show()
                    }
                    BluetoothAdapter.STATE_OFF -> {
                        Toast.makeText(context, "Você desativou o Bluetooth", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
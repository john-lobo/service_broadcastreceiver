package com.jlndev.mybroadcastreceiver.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.jlndev.mybroadcastreceiver.AppUtil.SERVICE_EXTRA

class CounterService : Service() {

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var intentService: Intent
    private var score = 0L

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        handler.removeCallbacks(updateScoreUI)
        handler.postDelayed(updateScoreUI, 3000)

        return START_STICKY

    }

    private val updateScoreUI: Runnable = object : Runnable {
        override fun run() {
            score++
            intentService.putExtra(SERVICE_EXTRA, score)
            //enviando o evento do servico para broadcast
            sendBroadcast(intentService)
            handler.postDelayed(this, 3000)
        }
    }

    override fun onCreate() {
        super.onCreate()
        intentService = Intent(CounterService::class.java.name)
    }
}
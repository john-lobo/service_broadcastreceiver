package com.jlndev.mybroadcastreceiver.view

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jlndev.mybroadcastreceiver.AppUtil
import com.jlndev.mybroadcastreceiver.AppUtil.TAG_SERVICE
import com.jlndev.mybroadcastreceiver.R
import com.jlndev.mybroadcastreceiver.controller.CounterController
import com.jlndev.mybroadcastreceiver.model.Counter
import com.jlndev.mybroadcastreceiver.service.CounterService

class MainActivity : AppCompatActivity() {

    private lateinit var counter: Counter
    private lateinit var controller: CounterController
    private lateinit var textScoreView: TextView
    private lateinit var intentCounterTask: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initService()
        controller = CounterController(this)
        textScoreView = findViewById(R.id.textScoreView)
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            try {
                updaterUI(intent)
            } catch (e : Exception) {
                Log.e(TAG_SERVICE, "broadcastReceiver: ${e.message}" )
            }
        }
    }

    private fun updaterUI(intent: Intent?) {
        intent?.let {
            counter = if(controller.getLatestItemId() != -1L) {
                Counter(controller.getLatestItemId() + 1)
            } else {
                val score = intent.getLongExtra(AppUtil.SERVICE_EXTRA, 0)
                Counter(score)
            }

            if (controller.saveCounter(counter)) {
                Log.i(TAG_SERVICE, "Salvo com sucesso " + counter.scoreDescription)
                Log.i(TAG_SERVICE, counter.scoreDescription)
            } else {
                Log.e(TAG_SERVICE, "Falha ao Salvar " + counter.score)
            }
            textScoreView.text = counter.score.toString()
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private fun initService() {
        intentCounterTask = Intent(this, CounterService::class.java)
        startService(Intent(this, CounterService::class.java))

        // registrando um broadcastReceiver para receber o evento
        registerReceiver(broadcastReceiver, IntentFilter(CounterService::class.java.name))
    }

    override fun onDestroy() {
        unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }
}
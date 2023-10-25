package com.jlndev.mybroadcastreceiver.controller

import android.content.ContentValues
import android.content.Context
import com.jlndev.mybroadcastreceiver.database.CounterDataBase
import com.jlndev.mybroadcastreceiver.model.Counter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CounterController(context: Context) : CounterDataBase(context) {

    private lateinit var values: ContentValues

    fun getCounter(counter: Counter) : Counter {
        return counter.apply {
            scoreDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            scoreHour = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
            scoreDescription = "Contagem atual: $score - data: $scoreDate - hora: $scoreHour"
        }
    }

    fun saveCounter(counter: Counter): Boolean {
        counter.apply {
            scoreDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            scoreHour = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
            scoreDescription = "Contagem atual: $score - data: $scoreDate - hora: $scoreHour"
        }

        values = ContentValues()
        values.put("date", counter.scoreDate)
        values.put("hour", counter.scoreHour)
        values.put("description", counter.scoreDescription)

        return insert(values)
    }
}
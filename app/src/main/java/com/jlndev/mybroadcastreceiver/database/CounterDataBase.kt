package com.jlndev.mybroadcastreceiver.database

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.jlndev.mybroadcastreceiver.AppUtil
import com.jlndev.mybroadcastreceiver.AppUtil.TABLE_NAME

open class CounterDataBase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    companion object {
        private const val DATABASE_NAME = "db_counter"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        try {
            db.execSQL(AppUtil.CREATE_TABLE_COUNTER)
        } catch (e: Exception) {
            Log.e(DATABASE_NAME, "onCreate dataBase: ${e.message}", e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    protected fun insert(values: ContentValues) : Boolean {
        val db = this.writableDatabase
        return try {
//            // Verifique o número de registros na tabela
//            val count = DatabaseUtils.queryNumEntries(db, TABLE_NAME)
//
//            // Se o número de registros for igual ou maior que 100, exclua todos os registros
//            if (count >= 100) {
//                db.delete(TABLE_NAME, null, null)
//            }

            db.insert(TABLE_NAME, null, values) > 0
        } catch (e: Exception) {
            false
        }
    }

    fun getLatestItemId(): Long {
        val db = this.readableDatabase

        val query = "SELECT id FROM $TABLE_NAME ORDER BY id DESC LIMIT 1"
        val cursor = db.rawQuery(query, null)

        var latestId: Long = -1 // Valor padrão se a tabela estiver vazia

        if (cursor.moveToFirst()) {
            latestId = cursor.getLong(cursor.getColumnIndex("id"))
        }

        cursor.close()
        return latestId
    }

    fun deleteAll() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
    }

}
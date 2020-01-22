package com.example.applicationlogic

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun getTime(date: String): String {
    var date1: Date? = null
    val simpleDateFormat = SimpleDateFormat("d-M-yyyy")
    try {
        date1 = simpleDateFormat.parse(date)
        Log.e("kotlinhelper ", "date1"+date1.toString())
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    val timeStr: String
    val inputFormat = SimpleDateFormat("dd-MM-yyyy")
    timeStr = inputFormat.format(date1)
    Log.e("kotlinhelper timeStr","timeStr"+timeStr)
    return timeStr
}

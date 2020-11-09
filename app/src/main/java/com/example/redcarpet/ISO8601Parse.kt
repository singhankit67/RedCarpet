package com.example.redcarpet

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ISO8601Parse {
    @SuppressLint("SimpleDateFormat")
    fun parse(params:String): Date {
        var input = params
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        if (input.endsWith("Z"))
        {
            input = input.substring(0, input.length - 1) + "GMT-00:00"
        }
        else
        {
            val inset = 6
            val startText = input.substring(0, input.length - inset)
            val endText = input.substring(input.length - inset, input.length)
            input = StringBuilder(startText).append("GMT").append(endText).toString()//startText + "GMT" + endText
        }
        return df.parse(input)
    }
    @SuppressLint("SimpleDateFormat")
    fun toString(date:String):String{
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        val tz =TimeZone.getTimeZone("UTC")
        df.timeZone = tz
        val output = df.format(date)
        val inset0 = 9
        val inset1 = 6
        val s0 = output.substring(0,output.length-inset0)
        val s1 = output.subSequence(output.length-inset1,output.length)
        var result = s0+s1
        result = result.replace("UTC".toRegex(),"+00:00")
        return result
    }
    @SuppressLint("SimpleDateFormat")
    fun DateFormat(oldstringDate:String):String {
        var newDate:String
        val dateFormat = SimpleDateFormat("E, d MMM yyyy")
        try
        {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate)
            newDate = dateFormat.format(date)
        }
        catch (e: ParseException) {
            e.printStackTrace()
            newDate = oldstringDate
        }
        return newDate
    }

}
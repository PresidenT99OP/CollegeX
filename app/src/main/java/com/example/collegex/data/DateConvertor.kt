package com.example.collegex.data

import androidx.room.TypeConverter
import java.util.*

class DateConvertor {

    @TypeConverter
    fun fromTimeStamp(value : Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimeStamp(date: Date): Long {
        return  date.time
    }
}
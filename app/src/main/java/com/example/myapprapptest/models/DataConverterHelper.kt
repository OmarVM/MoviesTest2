package com.example.myapprapptest.models

import androidx.room.TypeConverter

class DataConverterHelper {

    companion object{
        @TypeConverter
        @JvmStatic
        fun arrayIntToStream(list: List<Int>): String{
            return list.joinToString { "," }
        }

        @TypeConverter
        @JvmStatic
        fun streamToArray(text: String): List<Int>{
            return text.map { it.toInt() }
        }

    }

}
package com.example.testkuadran.core.util

import android.content.Context
import android.content.SharedPreferences

fun getSharePreference(
    context: Context
) : SharedPreferences {
    return context.getSharedPreferences("Default", Context.MODE_PRIVATE)
}
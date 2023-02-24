package com.example.testkuadran.core.util

import com.example.testkuadran.core.pattern.JSONConvertible
import com.google.gson.Gson


private var gson: Gson? = null

fun prepareGson() {
    gson = Gson()
}

fun toJson(obj: JSONConvertible): String {

    if (gson == null)
        prepareGson()

    return gson!!.toJson(obj)
}

inline fun <reified R> String.toObject(): R {
    return Gson().fromJson(this, R::class.java)
}
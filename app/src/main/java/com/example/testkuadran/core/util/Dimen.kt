package com.example.testkuadran.core.util

import android.content.res.Resources
import kotlin.math.ceil

fun Int.dp(): Int {
    return ceil(this * Resources.getSystem().displayMetrics.density * 1.0).toInt()
}
package com.example.testkuadran.core.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

typealias StartDate = Long
typealias EndDate = Long
typealias Year = Int
typealias Month = Int
typealias Day = Int

fun convertLongToTime(time: Long, pattern: String? = null): String {
    val date = Date(time)
    val format = SimpleDateFormat(pattern ?: "dd MMMM yyyy")
    return format.format(date)
}

fun currentTimeToLong(): Long {
    return System.currentTimeMillis()
}

fun convertDateToLong(date: String, pattern: String? = null): Long {
    val df = SimpleDateFormat(pattern ?: "yyyy.MM.dd HH:mm")
    return df.parse(date).time
}

fun getAge(
    year: Int,
    month: Int,
    dayOfMonth: Int
): Triple<Year, Month, Day> {
    return Period.between(
        LocalDate.of(year, month, dayOfMonth),
        LocalDate.now()
    ).run {
        Triple(this.years, this.months, this.days)
    }
}
package com.example.testkuadran.module.util

import com.example.testkuadran.core.util.convertDateToLong
import com.example.testkuadran.core.util.convertLongToTime
import com.example.testkuadran.module.data.model.Zodiac

enum class ZodiacEnum(val data: Zodiac) {
    Aries(
        Zodiac(
            name = "Aries",
            symbol = "♈",
            desc = "Ram",
            //March 21–April 19
            date = Pair(
                convertDateToLong(
                    "21-03",
                    "dd-MM"
                ),
                convertDateToLong(
                    "19-04",
                    "dd-MM"
                )
            )
        )
    ),
    Taurus(
        Zodiac(
            name = "Taurus",
            symbol = "♉",
            desc = "Bull",
            //April 20–May 20
            date = Pair(
                convertDateToLong(
                    "20-04",
                    "dd-MM"
                ),
                convertDateToLong(
                    "20-05",
                    "dd-MM"
                )
            )
        )
    ),
    Gemini(
        Zodiac(
            name = "Gemini",
            symbol = "♊",
            desc = "Twins",
            //May 21–June 21
            date = Pair(
                convertDateToLong(
                    "21-05",
                    "dd-MM"
                ),
                convertDateToLong(
                    "21-06",
                    "dd-MM"
                )
            )
        )
    ),
    Cancer(
        Zodiac(
            name = "Cancer",
            symbol = "♋",
            desc = "Crab",
            //June 22–July 22
            date = Pair(
                convertDateToLong(
                    "22-06",
                    "dd-MM"
                ),
                convertDateToLong(
                    "22-07",
                    "dd-MM"
                )
            )
        )
    ),
    Leo(
        Zodiac(
            name = "Leo",
            symbol = "♌",
            desc = "Lion",
            //July 23–August 22
            date = Pair(
                convertDateToLong(
                    "23-07",
                    "dd-MM"
                ),
                convertDateToLong(
                    "22-08",
                    "dd-MM"
                )
            )
        )
    ),
    Virgo(
        Zodiac(
            name = "Virgo",
            symbol = "♍",
            desc = "Virgin",
            //August 23–September 22
            date = Pair(
                convertDateToLong(
                    "23-08",
                    "dd-MM"
                ),
                convertDateToLong(
                    "22-09",
                    "dd-MM"
                )
            )
        )
    ),
    Libra(
        Zodiac(
            name = "Libra",
            symbol = "♎",
            desc = "Balance",
            //September 23–October 23
            date = Pair(
                convertDateToLong(
                    "23-09",
                    "dd-MM"
                ),
                convertDateToLong(
                    "23-10",
                    "dd-MM"
                )
            )
        )
    ),
    Scorpius(
        Zodiac(
            name = "Scorpius",
            symbol = "♏",
            desc = "Scorpion",
            //October 24–November 21
            date = Pair(
                convertDateToLong(
                    "24-10",
                    "dd-MM"
                ),
                convertDateToLong(
                    "21-11",
                    "dd-MM"
                )
            )
        )
    ),
    Sagittarius(
        Zodiac(
            name = "Sagittarius",
            symbol = "♐",
            desc = "Archer",
            //November 22–December 21
            date = Pair(
                convertDateToLong(
                    "22-11",
                    "dd-MM"
                ),
                convertDateToLong(
                    "21-12",
                    "dd-MM"
                )
            )
        )
    ),
    Capricornus(
        Zodiac(
            name = "Capricornus",
            symbol = "♑",
            desc = "Goat",
            //December 22–January 19
            date = Pair(
                convertDateToLong(
                    "22-12",
                    "dd-MM"
                ),
                convertDateToLong(
                    "19-01",
                    "dd-MM"
                )
            )
        )
    ),
    Aquarius(
        Zodiac(
            name = "Aquarius",
            symbol = "♒",
            desc = "Water Bearer",
            //January 20–February 18
            date = Pair(
                convertDateToLong(
                    "20-01",
                    "dd-MM"
                ),
                convertDateToLong(
                    "18-02",
                    "dd-MM"
                )
            )
        )
    ),
    Pisces(
        Zodiac(
            name = "Pisces",
            symbol = "♓",
            desc = "Fish",
            //February 19–March 20
            date = Pair(
                convertDateToLong(
                    "19-02",
                    "dd-MM"
                ),
                convertDateToLong(
                    "20-03",
                    "dd-MM"
                )
            )
        )
    )
}

fun getZodiac(
    month: Int,
    day: Int
) : Zodiac {

    val monthString = if (month<10) "0$month" else "$month"
    val dayString = if (day<0) "0$day" else "$day"

    val dateLong = convertDateToLong(
        "$monthString-$dayString",
        "MM-dd"
    )

    lateinit var result: Zodiac

    ZodiacEnum.values()
        .iterator()
        .forEach {

            val zodiacStart = it.data.date.first
            val zodiacEnd = it.data.date.second
            val deltaZodiac = zodiacEnd-zodiacStart

            val deltaDate = dateLong - zodiacStart

            if (deltaDate in 1..deltaZodiac){
                result = it.data
            }
        }

    return result
}
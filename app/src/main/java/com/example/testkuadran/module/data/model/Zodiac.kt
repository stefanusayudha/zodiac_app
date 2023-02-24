package com.example.testkuadran.module.data.model

import com.example.testkuadran.core.util.EndDate
import com.example.testkuadran.core.util.StartDate

data class Zodiac(
    val name: String,
    val symbol: String,
    val desc: String,
    val date: Pair<StartDate, EndDate>
)
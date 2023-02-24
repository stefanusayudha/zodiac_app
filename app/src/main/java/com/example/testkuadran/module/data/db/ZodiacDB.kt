package com.example.testkuadran.module.data.db

import com.example.testkuadran.module.data.model.Zodiac
import com.example.testkuadran.module.util.ZodiacEnum

class ZodiacDB {
    fun getZodiacList(): List<Zodiac> = ZodiacEnum.values().toList().map { it.data }
}
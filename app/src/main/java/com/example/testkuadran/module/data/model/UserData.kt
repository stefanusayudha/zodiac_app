package com.example.testkuadran.module.data.model

import com.example.testkuadran.core.pattern.JSONConvertible
import com.example.testkuadran.core.util.toJson
import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("name")
    val name: String,
    @SerializedName("dateBirth")
    val dateBirth: Long
) : JSONConvertible {
    override fun toJson(): String {
        return toJson(this)
    }
}

package com.example.testkuadran.module.data.db

import android.content.Context
import android.util.Log
import com.example.testkuadran.core.util.toJson
import com.example.testkuadran.core.util.getSharePreference
import com.example.testkuadran.core.util.toObject
import com.example.testkuadran.module.data.model.UserData

class UserDB(private val context: Context) {
    companion object {
        const val USERDATA = "USERDATA"
    }

    private val pref = getSharePreference(context)

    fun addUserData(
        userData: UserData
    ) {
        val prevData = getUserData()

        val newData = prevData.toMutableList()
            .apply {
                add(userData)
            }
            .map {
                toJson(it)
            }

        pref.edit()
            .putStringSet(USERDATA, newData.toSet())
            .apply()
    }

    fun getUserData(): List<UserData> {
        val set = (pref.getStringSet(USERDATA, setOf()) ?: setOf())

        Log.d("TAG", "getUserData: $set")
        return set.map {
            val obj = it.toObject<UserData>()

            obj
        }
    }
}
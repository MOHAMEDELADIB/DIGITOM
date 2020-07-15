package burullus.digitom.app.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import burullus.digitom.app.APPContext
import burullus.digitom.app.data.network.api.SHARED_PREFERENCE_FILE
import burullus.digitom.app.data.network.api.SP_TOKEN


object MySharedPreferences {
    private val sharedPreferences: SharedPreferences =
        APPContext.applicationContext()
            .getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE)

    fun getToken(): ByteArray? {
        val cryptedToken: String? = sharedPreferences.getString(SP_TOKEN, "")
        return Base64.decode(cryptedToken, Base64.NO_WRAP)
    }

    fun getiv(): ByteArray? {
        val iv: String? = sharedPreferences.getString("iv", "")
        return Base64.decode(iv, Base64.NO_WRAP)
    }

    fun saveToken(token: String) =
        sharedPreferences.edit().putString(SP_TOKEN, token).apply()

    fun saveIV(iv: String) = sharedPreferences.edit().putString("iv", iv).apply()
    fun clearToken() {
        sharedPreferences.edit().putString(SP_TOKEN, "").apply()
        sharedPreferences.edit().putString("iv", "").apply()
    }

}
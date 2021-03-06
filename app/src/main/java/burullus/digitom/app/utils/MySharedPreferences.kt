package burullus.digitom.app.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import burullus.digitom.app.DIGITOM
import burullus.digitom.app.data.network.api.RF_TOKEN
import burullus.digitom.app.data.network.api.SHARED_PREFERENCE_FILE
import burullus.digitom.app.data.network.api.SP_TOKEN


/**
 *
 */
object MySharedPreferences {
    private val sharedPreferences: SharedPreferences =
        DIGITOM.applicationContext()
            .getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE)

    /**
     *
     */
    fun getToken(): ByteArray? {
        val cryptedToken: String? = sharedPreferences.getString(SP_TOKEN, "")
        return Base64.decode(cryptedToken, Base64.NO_WRAP)
    }

    /**
     *
     */
    fun getRefresh(): ByteArray? {
        val cryptedToken: String? = sharedPreferences.getString(RF_TOKEN, "")
        return Base64.decode(cryptedToken, Base64.NO_WRAP)
    }

    /**
     *
     */
    fun getiv(): ByteArray? {
        val iv: String? = sharedPreferences.getString("iv", "")
        return Base64.decode(iv, Base64.NO_WRAP)
    }

    /**
     *
     */
    fun getiv2(): ByteArray? {
        val iv: String? = sharedPreferences.getString("iv2", "")
        return Base64.decode(iv, Base64.NO_WRAP)
    }

    /**
     *
     */
    fun saveToken(token: String): Unit =
        sharedPreferences.edit().putString(SP_TOKEN, token).apply()

    /**
     *
     */
    fun saveRefToken(token: String): Unit =
        sharedPreferences.edit().putString(RF_TOKEN, token).apply()

    /**
     *
     */
    fun saveIV2(iv: String): Unit = sharedPreferences.edit().putString("iv2", iv).apply()

    /**
     *
     */
    fun saveIV(iv: String): Unit = sharedPreferences.edit().putString("iv", iv).apply()

    /**
     *
     */
    fun clearToken() {
        sharedPreferences.edit().putString(SP_TOKEN, "").apply()
        sharedPreferences.edit().putString("iv", "").apply()
    }

    /**
     *
     */
    fun clearRefresh() {
        sharedPreferences.edit().putString(RF_TOKEN, "").apply()
        sharedPreferences.edit().putString("iv2", "").apply()
    }

}
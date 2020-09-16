package burullus.digitom.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import burullus.digitom.app.data.network.api.ApiService
import java.util.logging.Level
import java.util.logging.Logger


/**
 *
 */
class DIGITOM : Application() {

    private val LOG : Unit = Logger.getLogger(ApiService::class.java.name).setLevel(Level.FINE)

    init {
        instance = this
    }

    companion object {
        private var instance: DIGITOM? = null

        /**
         *
         */
        @SuppressLint("SyntheticAccessor")
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}
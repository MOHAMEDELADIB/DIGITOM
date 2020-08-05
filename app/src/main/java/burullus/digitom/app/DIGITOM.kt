package burullus.digitom.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context


/**
 *
 */
class DIGITOM : Application() {


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
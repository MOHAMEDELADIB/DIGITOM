package burullus.digitom.app.ui.profile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.util.*

interface ProfileMvpInteractor {

    /**
     *
     */
    fun loaddata()

    /**
     *
     */
    fun savedata(map : HashMap<String, RequestBody>, body : MultipartBody.Part?)

    /**
     *
     */
    fun signout()
}
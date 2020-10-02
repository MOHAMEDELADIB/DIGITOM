package burullus.digitom.app.ui.profile

import android.net.Uri
import burullus.digitom.app.data.network.model.requests.UserProfile
import java.io.File

/**
 *
 */
interface ProfileMvpPresenter {

    /**
     *
     */
    fun backpressed()

    /**
     *
     */
    fun savepressed(phone : String, job : String, first : String, last : String, file : File?)

    /**
     *
     */
    fun logoutpressed()

    /**
     *
     */
    fun loadprofile(userProfile : UserProfile)

    /**
     *
     */
    fun getProfile()

    /**
     *
     */
    fun onerror(serverError : String)

    /**
     *
     */
    fun cameraClick()

    /**
     *
     */
    fun ChooseGalleryClick()

    /**
     *
     */

    fun saveImage(uri : Uri?)

    /**
     *
     */
    fun permissionDenied()

    /**
     *
     */
    fun showPreview(mFilePath : File)

    fun loggedout(detail : String) {

    }

    fun saved()

    /**
     *
     */
    fun changedPasswordPressed()

}
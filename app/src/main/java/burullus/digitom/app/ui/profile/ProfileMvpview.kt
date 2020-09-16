package burullus.digitom.app.ui.profile

import android.net.Uri
import burullus.digitom.app.data.network.model.requests.UserProfile
import java.io.File

interface ProfileMvpview {

    /**
     *
     */
    fun backactivity()

    /**
     *
     */
    fun loaddata(userProfile : UserProfile)

    /**
     *
     */
    fun errormessage(error : String)

    /**
     *
     */
    fun checkPermission() : Boolean

    /**
     *
     */
    fun showPermissionDialog()

    /**
     *
     */
    fun openSettings()

    /**
     *
     */
    fun showErrorDialog()

    /**
     *
     */
    fun availableDisk() : Int

    /**
     *
     */
    fun showNoSpaceDialog()

    /**
     *
     */
    fun newFile() : File?

    /**
     *
     */
    fun getFilePath() : File?

    /**
     *
     */
    fun startCamera(file : File?)

    /**
     *
     */
    fun chooseGallery()

    /**
     *
     */
    fun displayImagePreview(mFilePath : File)


    /**
     *
     */
    fun getRealPathFromUri(contentUri : Uri?) : String?


    /**
     *
     */
    fun progressbarView()

    /**
     *
     */
    fun progressbarHidden()

    /**
     *
     */
    fun showSettingsDialog()

    /**
     *
     */
    fun loginActivity()
}
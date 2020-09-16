package burullus.digitom.app.ui.profile

import android.net.Uri
import burullus.digitom.app.data.network.model.requests.UserProfile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.util.*

/**
 *
 */
class ProfilePresenter(
    /**
     *
     */

    val view : ProfileMvpview
) : ProfileMvpPresenter {

    /**
     *
     */
    var interactor : ProfileMvpInteractor = ProfileInteractor(this)
    override fun backpressed() {
        view.backactivity()
    }


    override fun savepressed(
        phone : String,
        job : String,
        first : String,
        last : String,
        file : File?
    ) {
        view.progressbarView()
        val first_name : RequestBody = createPartFromString(first)
        val last1 : RequestBody = createPartFromString(last)
        val phone1 : RequestBody = createPartFromString(phone)
        val job1 : RequestBody = createPartFromString(job)

        val map : HashMap<String, RequestBody> = HashMap()
        map["first_name"] = first_name
        map["last_name"] = last1
        map["job"] = job1
        map["phone_number"] = phone1
        val file1 = file
        if (file1 != null) {
            val requestFile : RequestBody = file1.asRequestBody("image".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData(
                "photo",
                file1.name, requestFile
            )
            interactor.savedata(map, body)
        } else {
            interactor.savedata(map, null)
        }
    }

    override fun logoutpressed() {
        view.progressbarView()
        interactor.signout()

    }

    override fun loggedout(detail : String) {
        view.progressbarHidden()
        view.errormessage(detail)
        view.loginActivity()
    }

    override fun saved() {
        view.progressbarHidden()
        view.errormessage("Your profile is updated")
    }

    override fun loadprofile(userProfile : UserProfile) {
        view.loaddata(userProfile)
        view.progressbarHidden()

    }

    override fun getProfile() {
        view.progressbarView()
        interactor.loaddata()
    }


    override fun onerror(serverError : String) {
        view.progressbarHidden()
        view.errormessage(serverError)
    }

    override fun cameraClick() {
        if (!view.checkPermission()) {
            view.showPermissionDialog()
            return
        }
        if (view.availableDisk() <= 5) {
            view.showNoSpaceDialog()
            return
        }

        val file : File? = view.newFile()


        view.startCamera(file)

    }

    override fun ChooseGalleryClick() {
        if (!view.checkPermission()) {
            view.showPermissionDialog()
            return
        }
        view.chooseGallery()
    }

    override fun saveImage(uri : Uri?) {}

    override fun permissionDenied() {
        view.showPermissionDialog()
    }

    override fun showPreview(mFilePath : File) {
        view.displayImagePreview(mFilePath)
    }

    private fun createPartFromString(description : String) : RequestBody {
        return description.toRequestBody(MultipartBody.FORM)
    }

}
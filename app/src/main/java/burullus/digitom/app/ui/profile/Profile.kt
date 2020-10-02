@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "NAME_SHADOWING")

package burullus.digitom.app.ui.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import burullus.digitom.app.BuildConfig
import burullus.digitom.app.R
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.changepassword2.ChangePassword2
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.utils.FileCompressor
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.profile_page.*
import kotlinx.android.synthetic.main.profile_page.view.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.*
import burullus.digitom.app.data.network.model.requests.UserProfile as UserProfile1

/**
 *
 */
class Profile : BaseActivity(), ProfileMvpview {


    /**
     *
     */
    lateinit var presenter : ProfileMvpPresenter

    /**
     *
     */
    var mPhotoFile : File? = null
    private var mCompressor : FileCompressor? = null

    /**
     *
     */

    /**
     *
     */
    override fun onCreate(savedInstanceState : Bundle?) {
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)
        mCompressor = FileCompressor(this)
        presenter = ProfilePresenter(this)
        back_btn.setOnClickListener {
            presenter.backpressed()
        }
        presenter.getProfile()
        sign_out.setOnClickListener {
            presenter.logoutpressed()
        }
        save.setOnClickListener {
            val phone = phone_num.text.toString()
            val job = job_title.text.toString()
            val first = first_name.text.toString()
            val last = last_name.text.toString()
            presenter.savepressed(phone, job, first, last, mPhotoFile)
            // uploadProfile(phone, job, first, last)
        }
        chang_pass.setOnClickListener {
            presenter.changedPasswordPressed()
        }
        cardview.setOnClickListener {
            selectImage()
        }
    }

    /**
     *
     */
    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                try {

                    mPhotoFile = (mCompressor ?: return).compressToFile(mPhotoFile!!)
                    presenter.showPreview(mPhotoFile ?: return)
                } catch (e : IOException) {
                    e.printStackTrace()
                }
            } else if (requestCode == REQUEST_GALLERY_PHOTO) {
                val selectedImage = (data ?: return).data
                try {
                    mPhotoFile = (mCompressor ?: return).compressToFile(
                        File(
                            Objects.requireNonNull(
                                getRealPathFromUri(selectedImage)
                            )
                        )
                    )
                    presenter.showPreview(mPhotoFile ?: return)
                } catch (e : IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     *
     */
    override fun backactivity() {
        super.onBackPressed()
    }

    /**
     *
     */
    @SuppressLint("SetTextI18n")
    override fun loaddata(userProfile : UserProfile1) {
        val user = userProfile.username.substring(0, 1)
            .toUpperCase(Locale.US) + userProfile.username.substring(1).replace(".", " ")
        user_name.setText(user)
        last_name.setText(userProfile.last_name)
        first_name.setText(userProfile.first_name)
        job_title.setText(userProfile.job)
        phone_num.setText(userProfile.phone_number)
        userName.text = user
        val image = findViewById<CircleImageView>(R.id.profile_image)
        Glide.with(this)
            .load(userProfile.photo)
            .apply(RequestOptions().centerCrop().circleCrop().placeholder(R.drawable.profile_pic))
            .into(image)
    }

    /**
     *
     */
    override fun errormessage(error : String) {

        Toast.makeText(this, error, Toast.LENGTH_LONG)
            .show()
    }

    override fun checkPermission() : Boolean {
        val result = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (result == PackageManager.PERMISSION_DENIED) return false
        return true
    }

    /**
     *
     */
    override fun checkGalleryPermission() : Boolean {
        val result =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (result == PackageManager.PERMISSION_DENIED) return false
        return true
    }

    override fun showPermissionDialog() {
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response : PermissionGrantedResponse?) {
                    presenter.cameraClick()
                }

                override fun onPermissionDenied(response : PermissionDeniedResponse?) {
                    Timber.i("Access is denied")
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission : PermissionRequest?,
                    token : PermissionToken?
                ) {
                    token?.cancelPermissionRequest()
                    showSettingsDialog()
                }

            }).withErrorListener { showErrorDialog() }
            .onSameThread()
            .check()

    }

    /**
     *
     */
    override fun showGalleryPermissionDialog() {
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response : PermissionGrantedResponse?) {
                    presenter.ChooseGalleryClick()
                }

                override fun onPermissionDenied(response : PermissionDeniedResponse?) {
                    Timber.i("Access is denied")
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission : PermissionRequest?,
                    token : PermissionToken?
                ) {
                    token?.cancelPermissionRequest()
                    showSettingsDialog()
                }

            }).withErrorListener { showErrorDialog() }
            .onSameThread()
            .check()

    }


    private fun selectImage() {
        val items = arrayOf<CharSequence>(
            getString(R.string.take_photo), getString(R.string.choose_gallery),
            getString(R.string.Cancel)
        )
        val builder = AlertDialog.Builder(this@Profile)
        builder.setItems(items) { dialog : DialogInterface, item : Int ->
            if (items[item] == "Take photo") {
                presenter.cameraClick()
            } else if (items[item] == "Choose from gallery") {
                presenter.ChooseGalleryClick()
            } else if (items[item] == "Cancel") {
                dialog.dismiss()
            }
        }
        builder.show()
    }

    /**
     *
     */
    override fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.message_need_permission))
        builder.setMessage(getString(R.string.message_grant_permission))
        builder.setPositiveButton(getString(R.string.label_setting)) { dialog, which ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton(getString(R.string.Cancel)) { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    override fun loginActivity() {
        val intent = Intent(this, Login::class.java)
        intent.clearTop()
        intent.clearTask()
        startActivity(intent)
    }

    /**
     *
     */
    override fun changePassword() {
        val intent = Intent(this@Profile, ChangePassword2::class.java)
        startActivity(intent)
    }

    override fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        // intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivityForResult(intent, 101)
    }

    /**
     *
     */
    override fun showErrorDialog() {
        Toast.makeText(applicationContext, getString(R.string.error_message), Toast.LENGTH_LONG)
            .show()
    }

    override fun availableDisk() : Int {
        val mFilePath = getFilePath()
        val freeSpace = mFilePath!!.freeSpace
        return Math.round(freeSpace / 1048576.toFloat())
    }

    override fun showNoSpaceDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.error_message_no_more_space))
        builder.setMessage(getString(R.string.error_message_insufficient_space))
        builder.setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    /**
     *
     */
    override fun newFile() : File? {
        val cal = Calendar.getInstance()
        val timeInMillis = cal.timeInMillis
        val mFileName = "$timeInMillis.jpeg"
        val mFilePath = getFilePath()
        try {
            val newFile = File(mFilePath!!.absolutePath, mFileName)
            newFile.createNewFile()
            return newFile
        } catch (e : IOException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     *
     */
    override fun getFilePath() : File? {
        return getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    }

    override fun startCamera(file : File?) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (checkPermission()) {
            if (file != null) {
                val mPhotoURI =
                    FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file)

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI)
                mPhotoFile = file
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
            }
        }
    }

    override fun chooseGallery() {
        val pickPhoto = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(pickPhoto, REQUEST_GALLERY_PHOTO)
    }

    override fun displayImagePreview(mFilePath : File) {
        Glide.with(this).load(mFilePath)
            .apply(RequestOptions().centerCrop().circleCrop().placeholder(R.drawable.profile_pic))
            .into(profile_image)


    }


    /**
     *
     */
    override fun getRealPathFromUri(contentUri : Uri?) : String? {
        var cursor : Cursor? = null
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = contentResolver.query(contentUri ?: return null, proj, null, null, null)
            assert(cursor != null)
            val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(columnIndex)
        } finally {
            cursor?.close()
        }
    }

    /**
     *
     */
    override fun progressbarView() {
        profile_pbar.visibility = View.VISIBLE
        disableViews()
    }

    /**
     *
     */
    override fun progressbarHidden() {
        profile_pbar.visibility = View.INVISIBLE
        enableViews()

    }


    companion object {
        /**
         *
         */

        /**
         *
         */
        const val REQUEST_TAKE_PHOTO = 101

        /**
         *
         */
        val REQUEST_GALLERY_PHOTO : Int = 102

        /**
         *
         */
        var permissions : List<String> = listOf(
            Manifest.permission.CAMERA
        )
        var gallery_permissions : List<String> = listOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    private fun disableViews() {
        val layout : ConstraintLayout = findViewById<View>(R.id.layoutprofile) as ConstraintLayout
        for (i in 0 until layout.childCount) {
            val child : View = layout.getChildAt(i)
            if (child != child.back_btn) child.isEnabled = false
        }


    }

    private fun enableViews() {
        val layout : ConstraintLayout = findViewById<View>(R.id.layoutprofile) as ConstraintLayout
        for (i in 0 until layout.childCount) {
            val child : View = layout.getChildAt(i)
            child.isEnabled = true
        }
    }
}






package burullus.digitom.app.ui.splash

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import burullus.digitom.app.R
import burullus.digitom.app.ui.home.Home.Companion.getStartIntent
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.utils.MySharedPreferences
import burullus.digitom.app.utils.keystore.Decrypt
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import burullus.digitom.app.ui.splash.SplashMvpView as SplashMvpView1

/**
 *
 */
private const val UPDATE_REQUEST_CODE = 123

/**
 *
 */
class SplashActivity : AppCompatActivity(R.layout.splash_screen), SplashMvpView1 {
    /**
     *
     */
    lateinit var presenter : SplashMvpPresenter
    private var mDecrypt : Decrypt? = null
    private var mDelayHandler : Handler? = null
    private lateinit var mRunnable : Runnable
    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(this) }
    private var Update_Not_Available = true
    private var onetime : Boolean = false

    /**
     *
     */
    override fun onCreate(savedInstanceState : Bundle?) {

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        mDecrypt = Decrypt()
        presenter = SplashPresenter(this, mDecrypt ?: return)
        presenter.decode(MySharedPreferences.getToken(), MySharedPreferences.getRefresh())
        mRunnable = Runnable {
            if (!this@SplashActivity.isFinishing && Update_Not_Available) {
                setuppicasso()
                presenter.isAuthen(refresh_Token)
            }
        }

        mDelayHandler = Handler(
            Looper.myLooper() ?: return
        )
        (mDelayHandler ?: return).postDelayed(mRunnable, 1000)

        appUpdateManager.registerListener {
            if (it.installStatus() == InstallStatus.DOWNLOADED) {
                DownloadDialog()
            }

        }

        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(
                    AppUpdateType.FLEXIBLE
                ) && appUpdateInfo.updatePriority() < 3
            ) {
                Update_Not_Available = false
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.FLEXIBLE,
                    this,
                    UPDATE_REQUEST_CODE
                )
            }
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.updatePriority() >= 3
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                Update_Not_Available = false
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    UPDATE_REQUEST_CODE
                )
            }
        }.addOnFailureListener {
            Log.e("FlexibleUpdateActivity", "Failed to check for update: $it")
        }

    }

    /**
     *
     */
    override fun homeActivity() {

        val intent =
            getStartIntent(this@SplashActivity as Context)
        this@SplashActivity.startActivity(intent)
    }

    /**
     *
     */
    override fun loginActivity() {

        val intent =
            Login.getStartIntent(this@SplashActivity as Context)
        this@SplashActivity.startActivity(intent)
    }


    /**
     *
     */
    override fun loginMessage(message : String) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    }

    /**
     *
     */
    override fun onDestroy() {
        if (mDelayHandler != null) {
            (mDelayHandler ?: return).removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

    /**
     *
     */
    override fun onBackPressed() {
    }

    /**
     *
     */
    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                Update_Not_Available = false
                DownloadDialog()
            }

        }

    }

    private fun DownloadDialog() {

        val listener = DialogInterface.OnClickListener { _, _ ->
            appUpdateManager.completeUpdate()
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Digitom")
            .setMessage("Update downloaded!")
            .setPositiveButton("Install", listener)
            .show()
    }

    /**
     *
     */
    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UPDATE_REQUEST_CODE && resultCode == Activity.RESULT_CANCELED) {
            presenter.isAuthen(refresh_Token)
        }
        if (requestCode == UPDATE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            presenter.isAuthen(refresh_Token)
        }

    }

    companion object {
        /**
         *
         */


        /**
         *
         */
        var accesstoken : String = ""

        /**
         *
         */
        var refresh_Token : String = ""
    }

    private fun setuppicasso() {
        if (!onetime) {
            onetime = true
            val options = BitmapFactory.Options()
            options.inSampleSize = 25
            options.inPreferredConfig = Bitmap.Config.RGB_565
            val picasso = Picasso.Builder(this)
                .memoryCache(LruCache(1024 * 100 * 1024))
                .build()
            Picasso.setSingletonInstance(picasso)
        }
    }
}
package burullus.digitom.app.ui.splash

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import burullus.digitom.app.R
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.home.Home.Companion.getStartIntent
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.utils.MySharedPreferences
import burullus.digitom.app.utils.NotificationHelper
import burullus.digitom.app.utils.keystore.Decrypt
import burullus.digitom.app.ui.splash.SplashMvpView as SplashMvpView1

/**
 *
 */
class SplashActivity : BaseActivity(), SplashMvpView1 {
    /**
     *
     */
    lateinit var presenter : SplashMvpPresenter
    private var mDecrypt : Decrypt? = null
    private var mDelayHandler : Handler? = null
    private lateinit var mRunnable : Runnable
    private lateinit var helper : NotificationHelper
    lateinit var notificationManager : NotificationManager

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
            if (!this@SplashActivity.isFinishing) {
                setuppicasso()
                presenter.isAuthen(refresh_Token)
            }
            helper.notify(
                1, helper.getNotification1("sss", getString(R.string.searching_for_kks))
            )

        }
        helper = NotificationHelper(this)
        mDelayHandler = Handler(
            Looper.myLooper() ?: return
        )
        (mDelayHandler ?: return).postDelayed(mRunnable, 1000)
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

        helper.notify(
            1, helper.getNotification1("sss", getString(R.string.default_web_client_id))
        )

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

    companion object {
        /**
         *
         */
        var onetime : Boolean = false

        /**
         *
         */
        var accesstoken : String = ""

        /**
         *
         */
        var refresh_Token : String = ""
    }
}
package burullus.digitom.app.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.core.content.ContextCompat
import burullus.digitom.app.R
import burullus.digitom.app.data.network.api.TokenCheck
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.home.Home.Companion.getStartIntent
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.utils.MySharedPreferences
import burullus.digitom.app.utils.keystore.Decrypt
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.splash_screen.*
import burullus.digitom.app.ui.splash.SplashMvpView as SplashMvpView1

class SplashActivity : BaseActivity(), SplashMvpView1 {
    lateinit var presenter: SplashMvpPresenter
    private var mDecrypt: Decrypt? = null
    private var mDelayHandler: Handler? = null
    private lateinit var mRunnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        mDecrypt = Decrypt()
        presenter = SplashPresenter(this, mDecrypt!!)
        presenter.decode(MySharedPreferences.getToken())
        presenter.isAuthen(TokenCheck)
    }

    override fun homeActivity() {
        mRunnable = Runnable {
            if (!this@SplashActivity.isFinishing) {
                setuppicasso()
                val intent =
                    getStartIntent(this@SplashActivity as Context)
                this@SplashActivity.startActivity(intent)
            }
        }
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, 1000)
    }

    override fun loginActivity() {
        mRunnable = Runnable {
            if (!this@SplashActivity.isFinishing) {
                setuppicasso()
                val intent =
                    Login.getStartIntent(this@SplashActivity as Context)
                this@SplashActivity.startActivity(intent)
            }
        }
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, 3000)
    }

    override fun loginMessage(message: String) {
        Snackbar.make(linearLayout, message, Snackbar.LENGTH_LONG)
            .setTextColor(ContextCompat.getColor(this, R.color.material_blue_a700))
            .show()
    }

    override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
    }

    companion object {
        var onetime = false
        var accesstoken: String = ""
    }
}
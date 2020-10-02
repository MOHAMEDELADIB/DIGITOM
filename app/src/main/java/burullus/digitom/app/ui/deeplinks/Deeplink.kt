package burullus.digitom.app.ui.deeplinks

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import burullus.digitom.app.R
import burullus.digitom.app.data.network.api.Active_email
import burullus.digitom.app.data.network.api.Active_email_Seg
import burullus.digitom.app.data.network.api.Rest_key
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.changepassword.ChangePassword
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.deeplink_activity.*

/**
 *
 */
@SuppressLint("GoogleAppIndexingApiWarning")
class Deeplink : BaseActivity(), DeeplinkMvpview {
    private var uri : Uri? = null
    private lateinit var param : List<String>
    private var key = ""
    private var key2 = ""

    /**
     *
     */
    lateinit var presenter : DeeplinkMvpPresenter

    /**
     *
     */
    override fun onCreate(savedInstanceState : Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deeplink_activity)
        presenter = DeepLinkPresenter(this)
    }

    /**
     *  this function for load deep link parameters
     */
    override fun onStart() {
        super.onStart()
        deeplinkfun()
    }

    /**
     *  this function for hide progressbar
     */
    override fun hideprogressbar() {
        pbar.visibility = View.VISIBLE
    }

    /**
     * this function for show progressbar
     */
    override fun showprogressbar() {
        pbar.visibility = View.VISIBLE
    }

    /**
     * this function for display success message if  the token is valid
     * navigate to login activity
     */
    override fun deeplinkSuccess(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        intent = Intent(this@Deeplink, Login::class.java)
        startActivity(intent)
    }

    /**
     *  this function for display error in case of token failure
     *  and navigate to Register activity for get another token
     */
    override fun deeplinkError(message : String) {
        intent = Intent(this@Deeplink, RegisterActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * This function is used to pass the token from deeplink to change password activity
     */
    override fun deeplinknavigate(key : String) {
        val intent = Intent(this@Deeplink, ChangePassword::class.java)
        intent.putExtra("tokenkey", key)
        startActivity(intent)
    }

    private fun deeplinkfun() {
        val mainIntent = intent
        uri = mainIntent.data
        if (uri != null) {
            param = (uri ?: return).pathSegments
            key = (param[param.size - 1])
            key2 = (param[param.size - 2])
            if (key2 == Active_email) presenter.activeAccount(key)
            mainIntent.data = null
        }
        if (uri != null) {
            param = (uri ?: return).pathSegments
            if (param.size > 2) {
                key = (param[param.size - 2]) + "/" + (param[param.size - 1])
                key2 = (param[param.size - 4])
                if (key2 == Rest_key) {
                    presenter.forgetpass(key)
                }
            } else {
                key = (param[param.size - 1])
                key2 = (param[param.size - 2])
                if (key2 == Active_email_Seg) {
                    presenter.activeAccount(key)
                }
                mainIntent.data = null
            }
            mainIntent.data = null
        }
    }
}
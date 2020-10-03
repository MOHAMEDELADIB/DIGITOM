package burullus.digitom.app.ui.forgetpassword


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import burullus.digitom.app.R
import burullus.digitom.app.data.network.api.Verify
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.utils.NotificationHelper
import burullus.digitom.app.utils.Validator
import kotlinx.android.synthetic.main.activity_forget_password2.*


/**
 *
 */
class ForgetpasswordActivity : BaseActivity(), ForgetpasswordMvpView {
    private val validator = Validator()

    /**
     *
     */
    lateinit var presenter : ForgetpasswordMvpPresenter


    private lateinit var helper : NotificationHelper

    /**
     *
     */
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState : Bundle?) {

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password2)
        validator.add(Layout_reg_mail)
        presenter = ForgetpasswordPresenter(this)
        stepnext.setOnClickListener {
            val mail = findViewById<EditText>(R.id.reg_mail).text.toString()
            if (validator.result()) presenter.forgetpressed(mail)
        }
        f3back.setOnClickListener {
            presenter.backpressed()
        }
        helper = NotificationHelper(this)
    }

    /**
     *
     */
    override fun onStart() {
        super.onStart()
        reg_mail.text?.clear()
    }

    /**
     *
     */
    @SuppressLint("NewApi")
    override fun onsucess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        val intent = Intent(this@ForgetpasswordActivity, Login::class.java)
        startActivity(intent)
        helper.notify(1, helper.getNotification1("Email Verification", "$message $Verify"))
    }

    /**
     *
     */
    override fun onerror(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     *
     */
    override fun backActivity() {
        super.onBackPressed()
    }

    /**
     *
     */
    override fun showprogressbar() {
        forgetProgressbar.visibility = View.VISIBLE
    }

    /**
     *
     */
    override fun hideprogressbar() {
        forgetProgressbar.visibility = View.INVISIBLE
    }

    companion object {
        /**
         *
         */
        fun getStartIntent(context: Context?): Intent? {
            return Intent(context, ForgetpasswordActivity::class.java)
        }
    }

}

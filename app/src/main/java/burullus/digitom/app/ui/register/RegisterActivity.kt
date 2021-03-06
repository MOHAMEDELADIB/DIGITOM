package burullus.digitom.app.ui.register

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.activity_sign_up.*


/**
 *
 */
class RegisterActivity : BaseActivity(), RegisterMvpView {
    /**
     *
     */
    lateinit var presenter: RegisterMvpPresenter
    private val validator = Validator()
    private lateinit var helper : NotificationHelper
    /**
     *
     */
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        validator
            .add(confirmPasswordLayout)
            .add(Passwordlayout)
            .add(inputEmail)
        presenter = RegisterPresenter(this)

        rback.setOnClickListener {
            presenter.backpressed()
        }
        forget_pass.setOnClickListener {
            val mail = findViewById<EditText>(R.id.email_login)
            val password = findViewById<EditText>(R.id.create_password)
            val confirmPassword = findViewById<EditText>(R.id.confirm_password)
            if (validator.result()) presenter.signuppressed(
                mail.text.toString(),
                password.text.toString(),
                confirmPassword.text.toString()
            )
        }
        helper = NotificationHelper(this)
    }

    /**
     *
     */
    @SuppressLint("NewApi")
    override fun onsucess(message: String) {
        Toast.makeText(this@RegisterActivity, "$message $Verify", Toast.LENGTH_LONG).show()
        val intent = Intent(this@RegisterActivity, Login::class.java)
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
        progressBar2.visibility = View.VISIBLE
    }

    /**
     *
     */
    override fun hideprogressbar() {
        progressBar2.visibility = View.INVISIBLE
    }


}







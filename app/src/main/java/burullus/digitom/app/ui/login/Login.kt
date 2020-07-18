package burullus.digitom.app.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import burullus.digitom.app.R
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.forgetpassword.ForgetpasswordActivity
import burullus.digitom.app.ui.home.Home
import burullus.digitom.app.ui.register.RegisterActivity
import burullus.digitom.app.utils.Validator
import burullus.digitom.app.utils.keystore.Encrypt
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Login : BaseActivity(), LoginMvpView {
    /**
     *
     */
    lateinit var presenter: LoginMvpPresenter
    private var mEncrypt: Encrypt? = null
    private val validator = Validator()

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validator
            .add(inputPassword)
            .add(inputEmail)
        mEncrypt = Encrypt()
        presenter = LoginPresenter(this, mEncrypt ?: return)

        sign_in.setOnClickListener {
            val mail = findViewById<EditText>(R.id.email_login)
            val password = findViewById<EditText>(R.id.pswrd)
            if (validator.result()) presenter.signinpressed(
                mail.text.toString(),
                password.text.toString()
            )
        }
        forget_pass.setOnClickListener {
            presenter.forgetpressed()
        }
        register.setOnClickListener {
            presenter.registerPressed()
        }
    }

    /**
     *
     */
    override fun onsucess() {
        val intent = Home.getStartIntent(this@Login as Context)
        intent?.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        this@Login.startActivity(intent)
    }

    /**
     *
     */
    override fun onerror(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     *
     */
    override fun forgetActivity() {
        val intent =
            ForgetpasswordActivity.getStartIntent(this@Login as Context)
        this@Login.startActivity(intent)
    }

    /**
     *
     */
    override fun showprogressbar() {
        progressBar.visibility = View.VISIBLE
    }

    /**
     *
     */
    override fun hideprogressbar() {
        progressBar.visibility = View.INVISIBLE
    }

    /**
     *
     */
    override fun registerActivity() {
        val intent = Intent(this@Login, RegisterActivity::class.java)
        startActivity(intent)
    }

    companion object {
        /**
         *
         */
        fun getStartIntent(context: Context?): Intent? {
            return Intent(context, Login::class.java)
        }
    }

    /**
     *
     */
    override fun onBackPressed() {
    }
}









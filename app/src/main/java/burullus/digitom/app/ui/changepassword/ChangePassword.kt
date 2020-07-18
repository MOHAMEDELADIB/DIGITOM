package burullus.digitom.app.ui.changepassword


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import burullus.digitom.app.R
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.ui.register.RegisterActivity
import burullus.digitom.app.utils.Validator
import kotlinx.android.synthetic.main.activity_forget_password.*


/**
 * Change Password View
 */
@Suppress("DEPRECATED_IDENTITY_EQUALS")
class ChangePassword : BaseActivity(), ChangePasswordMvpView {
    /**
     * Present Interface declaration
     */
    lateinit var presenter: ChangePasswordMvpPresenter
    private val validator = Validator()
    private var tokenkeys: String = ""

    /**
     *On create Function for activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        validator.add(inputPassword)
        validator.add(password1layout)
        presenter = ChnagePasswordPresenter(this)
        fback.setOnClickListener {
            presenter.backpressed()
        }
        tokenkeys = intent.extras?.getString("tokenkey").toString()
        changePassword.setOnClickListener {
            val password1 = password1.text.toString()
            val password2 = password2.text.toString()
            if (password1 == password2 && validator.result()) {
                presenter.authtoken(tokenkeys, password1, password2)
            }
        }

    }

    /**
     * Show error message
     */
    override fun onerror(message: String) {

        val intent = Intent(this@ChangePassword, RegisterActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Show sucess message
     */
    override fun onsucess(message: String) {
        val intent = Intent(this@ChangePassword, Login::class.java)
        startActivity(intent)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Navigate to register Activity
     */
    override fun backActivity() {
        val intent = Intent(this@ChangePassword, RegisterActivity::class.java)
        startActivity(intent)

    }

    /**
     * Show Progressbar
     */
    override fun showprogressbar() {
        Progressbar.visibility = View.VISIBLE
    }

    /**
     * Hide progressbar
     */
    override fun hideprogressbar() {
        Progressbar.visibility = View.INVISIBLE
    }


}






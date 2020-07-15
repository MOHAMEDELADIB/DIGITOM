package burullus.digitom.app.ui.changepassword


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import burullus.digitom.app.R
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.utils.Validator
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_forget_password.*


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class ChangePassword : BaseActivity(), ChangePasswordMvpView {
    lateinit var presenter: ChangePasswordMvpPresenter
    private val validator = Validator()
    private var tokenkeys: String = ""
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

    companion object;

    override fun onerror(message: String) {
        Snackbar.make(layout, message, Snackbar.LENGTH_SHORT)
            .setTextColor(ContextCompat.getColor(this, R.color.material_blue_a700))
            .show()
    }

    override fun onsucess(message: String) {
        val intent = Intent(this@ChangePassword, Login::class.java)
        startActivity(intent)
        Snackbar.make(layout, message, Snackbar.LENGTH_SHORT)
            .setTextColor(ContextCompat.getColor(this, R.color.material_blue_a700))
            .show()
    }

    override fun backActivity() {

    }

    override fun showprogressbar() {
        Progressbar.visibility = View.VISIBLE
    }

    override fun hideprogressbar() {
        Progressbar.visibility = View.INVISIBLE
    }


}






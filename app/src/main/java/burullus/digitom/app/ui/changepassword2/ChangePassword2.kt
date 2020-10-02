package burullus.digitom.app.ui.changepassword2


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import burullus.digitom.app.R
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.changepassword.ChangePassword2MvpPresenter
import burullus.digitom.app.ui.changepassword.ChangePassword2MvpView
import burullus.digitom.app.ui.changepassword.ChnagePassword2Presenter
import burullus.digitom.app.ui.home.Home
import burullus.digitom.app.utils.Validator
import kotlinx.android.synthetic.main.activity_forget_password.*


/**
 * Change Password View
 */
@Suppress("DEPRECATED_IDENTITY_EQUALS")
class ChangePassword2 : BaseActivity(), ChangePassword2MvpView {
    /**
     * Present Interface declaration
     */
    lateinit var presenter : ChangePassword2MvpPresenter
    private val validator = Validator()
    private var tokenkeys : String = ""

    /**
     *On create Function for activity
     */
    override fun onCreate(savedInstanceState : Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        validator
            .add(password1layout)
            .add(inputPassword)
        presenter = ChnagePassword2Presenter(this)
        fback.setOnClickListener {
            presenter.backpressed()
        }
        changePassword.setOnClickListener {
            val password1 = password1.text.toString()
            val password2 = password2.text.toString()

            if (password1 == password2 && validator.result()) {
                presenter.authtoken(password1, password2)
            }

            if (password1 != password2) {
                Toast.makeText(this, "Password doesn't Match", Toast.LENGTH_LONG).show()
            }
        }

    }

    /**
     * Show error message
     */
    override fun onerror(message : String) {


        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     *
     */
    override fun onStart() {
        super.onStart()
        password1.text?.clear()
        password2.text?.clear()
    }

    /**
     * Show sucess message
     */
    override fun onsucess(message : String) {
        val intent = Intent(this@ChangePassword2, Home::class.java)
        startActivity(intent)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Navigate to register Activity
     */
    override fun backActivity() {
        super.onBackPressed()
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






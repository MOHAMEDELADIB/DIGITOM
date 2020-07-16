package burullus.digitom.app.ui.forgetpassword


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import burullus.digitom.app.R
import burullus.digitom.app.ui.base.BaseActivity
import burullus.digitom.app.ui.login.Login
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
    lateinit var presenter: ForgetpasswordMvpPresenter

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
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

    }

    /**
     *
     */
    override fun onsucess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

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
    override fun backActivity() {
        val intent = Login.getStartIntent(this@ForgetpasswordActivity as Context)
        this@ForgetpasswordActivity.startActivity(intent)

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

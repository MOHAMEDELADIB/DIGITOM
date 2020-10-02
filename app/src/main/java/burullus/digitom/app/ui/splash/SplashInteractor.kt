package burullus.digitom.app.ui.splash

import android.annotation.SuppressLint
import android.util.Base64
import android.widget.Toast
import burullus.digitom.app.DIGITOM
import burullus.digitom.app.data.network.api.Check
import burullus.digitom.app.data.network.api.Network_Message
import burullus.digitom.app.data.network.api.Server_error
import burullus.digitom.app.data.network.api.welcome_Message
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import burullus.digitom.app.ui.splash.SplashActivity.Companion.accesstoken
import burullus.digitom.app.ui.splash.SplashActivity.Companion.refresh_Token
import burullus.digitom.app.utils.MySharedPreferences
import burullus.digitom.app.utils.keystore.Encrypt
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 *
 */
class SplashInteractor(private val presenter : SplashMvpPresenter) : SplashMvpInteractor {

    /**
     *
     */
    @SuppressLint("CheckResult")
    override fun isAuthen(refresh_token : String) {
        accesstoken = ""
        val mEncrypt = Encrypt()
        Repository.getrefresh(refresh_token)
            ?.subscribe({ (access) ->

                accesstoken = access
                val encryptedText =
                    mEncrypt.encryptaccesstoken("ALIAS", accesstoken)
                val text = Base64.encodeToString(encryptedText, Base64.NO_WRAP)
                val encryptedText2 =
                    mEncrypt.encryptRefreshToken("ALIAS2", refresh_Token)
                val text2 =
                    Base64.encodeToString(encryptedText2, Base64.NO_WRAP)
                MySharedPreferences.saveToken(text)
                MySharedPreferences.saveRefToken(text2)
                presenter.onsucess()
                presenter.loginMessage(welcome_Message)
            }, { error ->
                if (error is HttpException) {
                    if (error.code() != 500 && error.code() != 405) {
                        presenter.onerror()
                        val gson = GsonBuilder().create()
                        val mError : ErrorModelClass
                        val responseBody : ResponseBody? = error.response()?.errorBody()
                        mError = gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                        val msg : String
                        if (mError != null) {
                            msg = if (!mError.detail.isNullOrEmpty())
                                mError.detail
                            else Check
                            Toast.makeText(
                                DIGITOM.applicationContext(),
                                msg,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        presenter.onerror()
                        presenter.loginMessage(Server_error)
                    }
                }
                if (error is IOException) {
                    presenter.loginMessage(Network_Message)
                    presenter.onerror()
                }
            })

    }

}
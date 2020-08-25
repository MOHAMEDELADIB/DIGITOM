package burullus.digitom.app.ui.login


import android.util.Base64
import burullus.digitom.app.data.network.api.Network_Message
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.ui.splash.SplashActivity.Companion.accesstoken
import burullus.digitom.app.ui.splash.SplashActivity.Companion.refresh_Token
import burullus.digitom.app.utils.MySharedPreferences
import burullus.digitom.app.utils.keystore.Encrypt
import java.io.IOException
import java.security.*
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException


/**
 *
 */
@Suppress("SENSELESS_COMPARISON")
class LoginPresenter(
    /**
     *
     */
    val view: LoginMvpView, private val mEncrypt: Encrypt
) : LoginMvpPresenter {
    private var interactor: LoginMvpInteractor = LoginInteractor(this)

    /**
     *
     */
    override fun signinpressed(email: String, password: String) {
        view.showprogressbar()
        interactor.signin(email, password)
    }

    /**
     *
     */
    override fun forgetpressed() {
        view.forgetActivity()
    }

    /**
     *
     */
    override fun onsuccess() {
        view.hideprogressbar()
        view.onsucess()
    }

    /**
     *
     */
    override fun onerror(merror: ErrorModelClass) {
        var message = ""
        view.hideprogressbar()
        if (merror.detail != null) view.onerror(merror.detail)
        if (merror.non_field_errors != null) {
            for (i in 0 until merror.non_field_errors.size) {
                message += merror.non_field_errors[i]
                if (i > 0) message += ",\n"
            }
            view.onerror(message)
        }
        if (merror.email != null) {
            for (i in 0 until merror.email.size) {
                message += merror.email[i]
                if (i > 0) message += ",\n"
            }
            view.onerror(message)
        }
    }

    /**
     *
     */
    override fun registerPressed() {
        view.registerActivity()
    }

    /**
     *
     */
    override fun onNetworkError(message: String) {
        view.hideprogressbar()
        view.onerror(Network_Message)
    }

    /**
     *
     */
    override fun encrypt(token: String, ref_token: String) {


        try {
            accesstoken = token
            refresh_Token = ref_token
            val encryptedText = mEncrypt.encryptaccesstoken("ALIAS", accesstoken)
            val text = Base64.encodeToString(encryptedText, Base64.NO_WRAP)
            val encryptedText2 = mEncrypt.encryptRefreshToken("ALIAS2", refresh_Token)
            val text2 = Base64.encodeToString(encryptedText2, Base64.NO_WRAP)
            MySharedPreferences.saveToken(text)
            MySharedPreferences.saveRefToken(text2)

        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        } catch (e: SignatureException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: UnrecoverableEntryException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: NoSuchProviderException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


}




package burullus.digitom.app.ui.splash

import burullus.digitom.app.ui.splash.SplashActivity.Companion.accesstoken
import burullus.digitom.app.utils.MySharedPreferences
import burullus.digitom.app.utils.keystore.Decrypt
import java.io.IOException
import java.security.*
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException

/**
 *
 */
class SplashPresenter(
    /**
     *
     */
    val View: SplashMvpView, private val mDecrypt: Decrypt
) : SplashMvpPresenter {
    /**
     *
     */
    var interactor: SplashMvpInteractor = SplashInteractor(this)

    /**
     *
     */
    override fun isAuthen(kks: String) {
        interactor.isAuthen(kks)
    }

    /**
     *
     */
    override fun onerror() {
        View.loginActivity()
    }

    /**
     *
     */
    override fun onsucess() {
        View.homeActivity()
    }

    /**
     *
     */
    override fun loginMessage(message: String) {
        View.loginMessage(message)
    }

    /**
     *
     */
    override fun decode(token: ByteArray?) {

        if ((MySharedPreferences.getiv() ?: return).isNotEmpty() && (MySharedPreferences.getToken()
                ?: return)
                .isNotEmpty()
        ) {
            try {
                accesstoken = mDecrypt.decryptData("ALIAS", token, MySharedPreferences.getiv())
            } catch (e: UnrecoverableEntryException) {
            } catch (e: NoSuchAlgorithmException) {
            } catch (e: KeyStoreException) {
            } catch (e: NoSuchPaddingException) {
            } catch (e: NoSuchProviderException) {
            } catch (e: IOException) {
            } catch (e: InvalidKeyException) {
            } catch (e: IllegalBlockSizeException) {
                e.printStackTrace()
            } catch (e: BadPaddingException) {
                e.printStackTrace()
            } catch (e: InvalidAlgorithmParameterException) {
                e.printStackTrace()
            }
        }
    }


}
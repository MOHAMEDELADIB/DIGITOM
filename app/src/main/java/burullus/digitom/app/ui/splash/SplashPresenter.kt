package burullus.digitom.app.ui.splash

import burullus.digitom.app.ui.splash.SplashActivity.Companion.refresh_Token
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
    override fun decode(token: ByteArray?, refresh: ByteArray?) {

        if ((MySharedPreferences.getiv() ?: return).isNotEmpty()) {
            if ((MySharedPreferences.getToken() ?: return).isNotEmpty()) {
                try {

                    refresh_Token =
                        mDecrypt.decryptaccesstoken("ALIAS2", refresh, MySharedPreferences.getiv2())
                    SplashActivity.accesstoken =
                        mDecrypt.decryptaccesstoken("ALIAS", token, MySharedPreferences.getiv())
                } catch (e: UnrecoverableEntryException) {
                    e.printStackTrace()
                } catch (e: NoSuchAlgorithmException) {
                    e.printStackTrace()
                } catch (e: KeyStoreException) {
                    e.printStackTrace()
                } catch (e: NoSuchPaddingException) {
                    e.printStackTrace()
                } catch (e: NoSuchProviderException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: InvalidKeyException) {
                    e.printStackTrace()
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


}
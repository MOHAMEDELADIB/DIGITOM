package burullus.digitom.app.ui.splash


/**
 *
 */
interface SplashMvpPresenter {
    /**
     *
     */
    fun isAuthen(refresh_token : String)

    /**
     *
     */
    fun onerror()

    /**
     *
     */
    fun onsucess()

    /**
     *
     */
    fun loginMessage(message : String)

    /**
     *
     */
    fun decode(token : ByteArray?, refresh : ByteArray?)

}
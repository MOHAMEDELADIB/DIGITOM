package burullus.digitom.app.ui.login

/**
 *
 */
interface LoginMvpView {
    /**
     *
     */
    fun onsucess()

    /**
     *
     */
    fun onerror(message: String)

    /**
     *
     */
    fun forgetActivity()

    /**
     *
     */
    fun showprogressbar()

    /**
     *
     */
    fun hideprogressbar()

    /**
     *
     */
    fun registerActivity()
}
package burullus.digitom.app.ui.forgetpassword

/**
 *
 */
interface ForgetpasswordMvpView {
    /**
     *
     */
    fun onsucess(message: String)

    /**
     *
     */
    fun onerror(message: String)

    /**
     *
     */
    fun backActivity()

    /**
     *
     */
    fun showprogressbar()

    /**
     *
     */
    fun hideprogressbar()
}
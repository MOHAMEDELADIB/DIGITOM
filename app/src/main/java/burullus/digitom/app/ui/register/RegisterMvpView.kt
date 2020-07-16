package burullus.digitom.app.ui.register

/**
 *
 */
interface RegisterMvpView {
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
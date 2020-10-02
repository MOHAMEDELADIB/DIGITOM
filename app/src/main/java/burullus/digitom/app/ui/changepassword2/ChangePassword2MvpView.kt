package burullus.digitom.app.ui.changepassword

/**
 * View Interface
 */
interface ChangePassword2MvpView {
    /**
     * how messsage function
     */
    fun onerror(message : String)

    /**
     *  Show messsage function
     */
    fun onsucess(message : String)

    /**
     * Navigate Activity
     */
    fun backActivity()

    /**
     * Show Progressbar
     */
    fun showprogressbar()

    /**
     * Hide Progressbar
     */
    fun hideprogressbar()
}
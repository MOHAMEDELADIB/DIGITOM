package burullus.digitom.app.ui.deeplinks

/**
 * Interface class between view and presenter
 */
interface DeeplinkMvpview {
    /**
     * hide progressbar function
     */
    fun hideprogressbar()

    /**
     * show progressbar function
     */
    fun showprogressbar()

    /**
     * show message in case of token valid
     */
    fun deeplinkSuccess(message: String)

    /**
     * error message in case fault url
     */
    fun deeplinkError(message: String)

    /**
     * for navigate after token check
     */
    fun deeplinknavigate(key: String)

}
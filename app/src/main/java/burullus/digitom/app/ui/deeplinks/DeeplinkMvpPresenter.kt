package burullus.digitom.app.ui.deeplinks

import burullus.digitom.app.data.network.model.ErrorModelClass

/**
 * Presenter Interface
 */
interface DeeplinkMvpPresenter {
    /**
     * Show Message in case of Network Failure
     */
    fun onNetworkError(message: String)

    /**
     * Presenter function to send active token
     */
    fun activeAccount(key: String)

    /**
     *Function to show error message
     */
    fun onDeeplinkError(merror: ErrorModelClass)

    /**
     * show Success message
     */
    fun onDeepLinkSuccess(message: String)

    /**
     * To pass forget token to change password
     */
    fun forgetpass(key: String)
}
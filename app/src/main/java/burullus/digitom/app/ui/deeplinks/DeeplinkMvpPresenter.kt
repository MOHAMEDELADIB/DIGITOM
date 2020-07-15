package burullus.digitom.app.ui.deeplinks

import burullus.digitom.app.data.network.model.ErrorModelClass

interface DeeplinkMvpPresenter {
    fun onNetworkError(message: String)
    fun activeAccount(key: String)
    fun onDeeplinkError(merror: ErrorModelClass)
    fun onDeepLinkSuccess(message: String)
    fun forgetpass(key: String)
}
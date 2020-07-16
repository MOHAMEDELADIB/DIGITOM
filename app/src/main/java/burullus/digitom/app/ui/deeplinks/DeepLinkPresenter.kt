package burullus.digitom.app.ui.deeplinks

import burullus.digitom.app.data.network.model.ErrorModelClass

/**
 * The presenter Class for deeplink
 */
@Suppress("SENSELESS_COMPARISON")
class DeepLinkPresenter(
    /**
     * View interface
     */
    val view: DeeplinkMvpview
) : DeeplinkMvpPresenter {
    private var interactor: DeepLinkMvpInteractor = DeepLinkInteractor(this)

    /**
     * for send token to check
     */
    override fun activeAccount(key: String) {
        view.showprogressbar()
        interactor.activedeeplink(key)
    }

    /**
     * in case of non valid token
     */
    override fun onDeeplinkError(merror: ErrorModelClass) {
        view.hideprogressbar()
        var message = ""
        view.hideprogressbar()
        if (merror.detail != null) view.deeplinkError(merror.detail)
        if (merror.non_field_errors != null) {
            for (i in 0 until merror.non_field_errors.size) {
                message += merror.non_field_errors[i]
                if (i > 0) message += ",\n"
            }
            view.deeplinkError(message)
        }
        if (merror.email != null) {
            for (i in 0 until merror.email.size) {
                message += merror.email[i]
                if (i > 0) message += ",\n"
            }
            view.deeplinkError(message)
        }

    }

    /**
     *
     */
    override fun onDeepLinkSuccess(message: String) {
        view.hideprogressbar()
        view.deeplinkSuccess(message)
    }

    /**
     * Presenter function for pass key to another activity
     */
    override fun forgetpass(key: String) {
        view.deeplinknavigate(key)
    }

    /**
     * Presenter function in case Network error or Server Error
     */
    override fun onNetworkError(message: String) {
        view.hideprogressbar()
        view.deeplinkError(message)
    }


}
package burullus.digitom.app.ui.deeplinks

import burullus.digitom.app.data.network.model.ErrorModelClass

@Suppress("SENSELESS_COMPARISON")
class DeepLinkPresenter(val view: DeeplinkMvpview) : DeeplinkMvpPresenter {
    private var interactor: DeepLinkMvpInteractor = DeepLinkInteractor(this)

    override fun activeAccount(key: String) {
        view.showprogressbar()
        interactor.activedeeplink(key)
    }

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

    override fun onDeepLinkSuccess(message: String) {
        view.hideprogressbar()
        view.deeplinkSuccess(message)
    }

    override fun forgetpass(key: String) {
        view.deeplinknavigate(key)
    }

    override fun onNetworkError(message: String) {
        view.hideprogressbar()
        view.deeplinkError(message)
    }


}
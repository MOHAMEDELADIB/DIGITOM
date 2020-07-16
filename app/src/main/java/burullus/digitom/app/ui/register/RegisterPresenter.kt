package burullus.digitom.app.ui.register

import burullus.digitom.app.data.network.model.ErrorModelClass

/**
 *
 */
@Suppress("SENSELESS_COMPARISON")
class RegisterPresenter(
    /**
     *
     */
    val view: RegisterMvpView
) : RegisterMvpPresenter {
    /**
     *
     */
    var interactor: RegisterMvpInteractor = RegisterInteractor(this)

    /**
     *
     */
    override fun signuppressed(email: String, password1: String, password2: String) {
        view.showprogressbar()
        if (password1 == password2) interactor.signup(email, password1, password2) else {
            view.onerror("The password doesn't match")
            view.hideprogressbar()
        }
    }

    /**
     *
     */
    override fun backpressed() {
        view.backActivity()
    }

    /**
     *
     */
    override fun onsuccess(message: String) {
        view.onsucess(message)
        view.hideprogressbar()
    }

    /**
     *
     */
    override fun onerror(merror: ErrorModelClass) {
        view.hideprogressbar()
        var message = ""

        view.hideprogressbar()
        if (merror.detail != null) message = merror.detail
        if (merror.non_field_errors != null) {
            if (message.isNotEmpty()) message += ",\n"
            for (i in 0 until merror.non_field_errors.size) {
                message += merror.non_field_errors[i]
                if (message.isNotEmpty()) message += ",\n"
            }

        }
        if (merror.email != null) {
            if (message.isNotEmpty()) message += ",\n"
            for (i in 0 until merror.email.size) {
                message += merror.email[i]
                if (i > 0) message += ",\n"
            }
        }
        if (merror.password1 != null) {
            if (message.isNotEmpty()) message += ",\n"
            for (i in 0 until merror.password1.size) {
                message += merror.password1[i]
                if (i > 0) message += ",\n"
            }
        }

        view.onerror(message)
    }

    /**
     *
     */
    override fun onNetworkError(message: String) {
        view.hideprogressbar()
        view.onerror(message)
    }


}

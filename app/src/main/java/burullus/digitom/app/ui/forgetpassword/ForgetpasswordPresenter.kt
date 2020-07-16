package burullus.digitom.app.ui.forgetpassword

import burullus.digitom.app.data.network.model.ErrorModelClass

/**
 *
 */
@Suppress("SENSELESS_COMPARISON")
class ForgetpasswordPresenter(
    /**
     *
     */
    val view: ForgetpasswordMvpView
) : ForgetpasswordMvpPresenter {
    /**
     *
     */
    var interactor: ForgetpasswordMvpInteractor = ForgetPasswordInteractor(this)

    /**
     *
     */
    override fun forgetpressed(email: String) {
        view.showprogressbar()
        interactor.forget(email)
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
        view.hideprogressbar()
        view.onsucess(message)
    }

    /**
     *
     */
    override fun onerror(message: String) {
        view.hideprogressbar()
        view.onerror(message)
    }

    /**
     *
     */
    override fun onError(mError: ErrorModelClass) {
        var message = ""
        view.hideprogressbar()
        if (mError.detail != null) view.onerror(mError.detail)
        if (mError.non_field_errors != null) {
            for (i in 0 until mError.non_field_errors.size) {
                message += mError.non_field_errors[i]
                if (i > 0) message += ",\n"
            }
            view.onerror(message)
        }
        if (mError.email != null) {
            for (i in 0 until mError.new_password1.size) {
                message += mError.new_password1[i]
                if (i > 0) message += ",\n"
                view.onerror(message)
            }
        }
    }

}

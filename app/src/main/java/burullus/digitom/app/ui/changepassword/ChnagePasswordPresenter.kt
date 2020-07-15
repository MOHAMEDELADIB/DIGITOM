package burullus.digitom.app.ui.changepassword

import burullus.digitom.app.data.network.model.ErrorModelClass


@Suppress("SENSELESS_COMPARISON")
class ChnagePasswordPresenter(val view: ChangePasswordMvpView) : ChangePasswordMvpPresenter {
    var interactor: ChangePasswordMvpInteractor = ChangePasswordInteractor(this)
    override fun authtoken(token: String, new_password1: String, new_password2: String) {
        view.showprogressbar()
        interactor.auth(token, new_password1, new_password2)
    }

    override fun backpressed() {
        view.backActivity()
    }

    override fun onsuccess(message: String) {
        view.hideprogressbar()
        view.onsucess(message)
    }

    override fun onerror(message: String) {
        view.hideprogressbar()
        view.onerror(message)
    }

    override fun onError(merror: ErrorModelClass) {
        var message = ""
        view.hideprogressbar()
        if (merror.detail != null) view.onerror(merror.detail)
        if (merror.non_field_errors != null) {
            for (i in 0 until merror.non_field_errors.size) {
                message += merror.non_field_errors[i]
                if (i > 0) message += ",\n"
            }
            view.onerror(message)
        }
        if (merror.new_password1 != null) {
            for (i in 0 until merror.new_password1.size) {
                message += merror.new_password1[i]
                if (i > 0) message += ",\n"
            }
            if (merror.new_password2 != null) {
                for (i in 0 until merror.new_password2.size) {
                    message += merror.new_password2[i]
                    if (i > 0) message += ",\n"
                }
                view.onerror(message)
            }
        }
    }

}

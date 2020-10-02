package burullus.digitom.app.ui.changepassword

import burullus.digitom.app.data.network.model.ErrorModelClass


/**
 * Class Presenter
 */
@Suppress("SENSELESS_COMPARISON")
class ChnagePassword2Presenter(
    /**
     * declare View Interface
     */
    val view : ChangePassword2MvpView
) : ChangePassword2MvpPresenter {
    /**
     * attach interactor
     */
    var interactor : ChangePassword2MvpInteractor = ChangePassword2Interactor(this)
    override fun authtoken(new_password1 : String, new_password2 : String) {
        view.showprogressbar()
        interactor.changePass(new_password1, new_password2)
    }

    override fun backpressed() {
        view.backActivity()
    }

    override fun onsuccess(message : String) {
        view.hideprogressbar()
        view.onsucess(message)
    }

    override fun onerror(message : String) {
        view.hideprogressbar()
        view.onerror(message)
    }

    override fun onError(merror : ErrorModelClass) {
        var message = ""
        view.hideprogressbar()
        if (merror.detail != null) message = merror.detail
        if (merror.non_field_errors != null) {
            for (i in 0 until merror.non_field_errors.size) {
                message += merror.non_field_errors[i]
                if (i > 0) message += ",\n"
            }
        }
        if (merror.new_password1 != null) {
            for (i in 0 until merror.new_password1.size) {
                message += merror.new_password1[i]
                if (i > 0) message += ",\n"
            }
        }
        if (merror.new_password2 != null) {
            for (i in 0 until merror.new_password2.size) {
                message += merror.new_password2[i]
                if (i > 0) message += ",\n"
            }
        }
        if (merror.token != null) {
            message += merror.token

            view.backActivity()
        }
        view.onerror(message)
    }

}

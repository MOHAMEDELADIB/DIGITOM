package burullus.digitom.app.ui.forgetpassword

class ForgetpasswordPresenter(val View: ForgetpasswordMvpView) : ForgetpasswordMvpPresenter {
    var interactor: ForgetpasswordMvpInteractor = ForgetPasswordInteractor(this)
    override fun forgetpressed(email: String) {
        View.showprogressbar()
        interactor.forget(email)
    }

    override fun backpressed() {
        View.backActivity()
    }

    override fun onsuccess(message: String) {
        View.hideprogressbar()
        View.onsucess(message)
    }

    override fun onerror(message: String) {
        View.hideprogressbar()
        View.onerror(message)
    }

}

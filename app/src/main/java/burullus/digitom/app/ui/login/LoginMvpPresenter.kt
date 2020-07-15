package burullus.digitom.app.ui.login

import burullus.digitom.app.data.network.model.ErrorModelClass


interface LoginMvpPresenter {
    fun signinpressed(email: String, password: String)
    fun forgetpressed()
    fun onsuccess()
    fun onerror(merror: ErrorModelClass)
    fun registerPressed()
    fun onNetworkError(message: String)
    fun encrypt(token: String)

}
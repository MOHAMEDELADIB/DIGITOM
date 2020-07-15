package burullus.digitom.app.ui.forgetpassword


interface ForgetpasswordMvpPresenter {
    fun forgetpressed(email: String)
    fun backpressed()
    fun onsuccess(message: String)
    fun onerror(message: String)
}
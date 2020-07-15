package burullus.digitom.app.ui.register

interface RegisterMvpInteractor {
    fun signup(email: String, password: String, confirmPassword: String)
}

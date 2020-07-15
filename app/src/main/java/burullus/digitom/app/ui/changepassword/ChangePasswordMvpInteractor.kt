package burullus.digitom.app.ui.changepassword

interface ChangePasswordMvpInteractor {

    fun auth(token: String, new_password1: String, new_password2: String)
}

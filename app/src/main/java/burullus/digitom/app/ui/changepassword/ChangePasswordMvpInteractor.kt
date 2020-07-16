package burullus.digitom.app.ui.changepassword

/**
 * change Password Interactor
 */
interface ChangePasswordMvpInteractor {

    /**
     * Interactor authentication fun
     */
    fun auth(token: String, new_password1: String, new_password2: String)
}

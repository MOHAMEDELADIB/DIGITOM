package burullus.digitom.app.ui.changepassword

import burullus.digitom.app.data.network.model.ErrorModelClass


interface ChangePasswordMvpPresenter {
    fun authtoken(token: String, new_password1: String, new_password2: String)
    fun backpressed()
    fun onsuccess(message: String)
    fun onerror(message: String)
    fun onError(merror: ErrorModelClass)
}
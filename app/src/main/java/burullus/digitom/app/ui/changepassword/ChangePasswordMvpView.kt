package burullus.digitom.app.ui.changepassword

interface ChangePasswordMvpView {
    fun onerror(message: String)
    fun onsucess(message: String)
    fun backActivity()
    fun showprogressbar()
    fun hideprogressbar()
}
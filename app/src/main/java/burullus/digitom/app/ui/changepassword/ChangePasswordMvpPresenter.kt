package burullus.digitom.app.ui.changepassword

import burullus.digitom.app.data.network.model.ErrorModelClass


/**
 * Change password presenter interface
 */
interface ChangePasswordMvpPresenter {
    /**
     * Presenter function
     */
    fun authtoken(token: String, new_password1: String, new_password2: String)

    /**
     * presenter back function
     */
    fun backpressed()

    /**
     * presenter messsage function
     */
    fun onsuccess(message: String)

    /**
     *  presenter messsage function
     */
    fun onerror(message: String)

    /**
     *  presenter messsage function
     */
    fun onError(merror: ErrorModelClass)
}
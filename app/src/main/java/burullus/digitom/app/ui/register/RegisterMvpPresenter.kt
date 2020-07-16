package burullus.digitom.app.ui.register

import burullus.digitom.app.data.network.model.ErrorModelClass


/**
 *
 */
interface RegisterMvpPresenter {
    /**
     *
     */
    fun signuppressed(email: String, password1: String, password2: String)

    /**
     *
     */
    fun backpressed()

    /**
     *
     */
    fun onsuccess(message: String)

    /**
     *
     */
    fun onerror(merror: ErrorModelClass)

    /**
     *
     */
    fun onNetworkError(message: String)

}
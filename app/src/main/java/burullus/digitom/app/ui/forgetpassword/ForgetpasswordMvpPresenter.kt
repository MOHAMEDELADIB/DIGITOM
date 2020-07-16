package burullus.digitom.app.ui.forgetpassword

import burullus.digitom.app.data.network.model.ErrorModelClass


/**
 *
 */
interface ForgetpasswordMvpPresenter {
    /**
     *
     */
    fun forgetpressed(email: String)

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
    fun onerror(message: String)

    /**
     *
     */
    fun onError(mError: ErrorModelClass)
}
package burullus.digitom.app.ui.ocrscreen.dialog

/**
 *
 */
interface DialogMvpPresenter {
    /**
     *
     */
    fun send(KKS: String, header: String)

    /**
     *
     */
    fun onsucess(kks: String)

    /**
     *
     */
    fun onerror(message: String)
}
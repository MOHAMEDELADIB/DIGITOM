package burullus.digitom.app.ui.ocrscreen.dialog

/**
 *
 */
interface DialogView {
    /**
     *
     */
    fun onsucess(KKS: String)

    /**
     *
     */
    fun onerror(message: String)

    /**
     *
     */
    fun close()
}
package burullus.digitom.app.ui.ocrscreen

/**
 *
 */
interface OcrMvpPresenter {
    /**
     *
     */
    fun backpressed()

    /**
     *
     */
    fun searchpressed()

    /**
     *
     */
    fun flashPressed()

    /**
     *
     */
    fun activityresume()

    /**
     *
     */
    fun detected(kks: String)

    /**
     *
     */
    fun activityDestroy()

    /**
     *
     */
    fun activityPause()

    /**
     *
     */
    fun kksdetected(kks: String, head: String)

    /**
     *
     */
    fun onSucess(KKS: String)

    /**
     *
     */
    fun onerror(message: String)

}
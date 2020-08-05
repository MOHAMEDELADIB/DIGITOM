package burullus.digitom.app.ui.ocrscreen.dialog

import burullus.digitom.app.data.network.model.responses.Paging

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
    fun onsucess(pagedata: Paging)

    /**
     *
     */
    fun onerror(message: String)
}
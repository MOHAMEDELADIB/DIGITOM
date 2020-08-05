package burullus.digitom.app.ui.ocrscreen.dialog

import burullus.digitom.app.data.network.model.responses.Paging

/**
 *
 */
interface DialogView {
    /**
     *
     */
    fun onsucess(pagedata: Paging)

    /**
     *
     */
    fun onerror(message: String)

    /**
     *
     */
    fun close()
}
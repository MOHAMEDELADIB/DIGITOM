package burullus.digitom.app.ui.paging

import burullus.digitom.app.data.network.model.responses.Paging

/**
 *
 */
interface PagingMvpPresenter {
    /**
     *
     */
    fun loaddata(detectedkks: String, head: String)

    /**
     *
     */
    fun onsucess(mpage: Paging)

    /**
     *
     */
    fun onerror(message: String)

    /**
     *
     */
    fun nextpage(nextpage: String)

    /**
     *
     */
    fun backpressed()


}
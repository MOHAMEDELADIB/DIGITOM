package burullus.digitom.app.ui.paging

/**
 *
 */
interface PagingMvpView {

    /**
     *
     */
    fun onerror(message: String)

    /**
     *
     */
    fun onsucess(mpage: burullus.digitom.app.data.network.model.responses.Paging)

    /**
     *
     */
    fun showprogrssbar()

    /**
     *
     */
    fun hideprogressbar()

    /**
     *
     */
    fun backActivity()

}
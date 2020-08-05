package burullus.digitom.app.ui.paging

/**
 *
 */
interface PagingMvpInteractor {
    /**
     *
     */
    fun loaddata(detectedkks: String, head: String)

    /**
     *
     */
    fun nextpage(nextpage: String)

    /**
     *
     */
    fun perviouspage(perviousPage: String)
}
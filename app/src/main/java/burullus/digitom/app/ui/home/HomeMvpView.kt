package burullus.digitom.app.ui.home

import burullus.digitom.app.data.network.model.ArticleData

/**
 *
 */
interface HomeMvpView {
    /**
     *
     */
    fun getnews()

    /**
     *
     */
    fun onsuccess(news: List<ArticleData>)

    /**
     *
     */
    fun onerror(message: String)

    /**
     *
     */
    fun lockDrawer()

    /**
     *
     */
    fun unlockDrawer()

    /**
     *
     */
    fun opendrawer()

    /**
     *
     */
    fun closedrawer()

    /**
     *
     */
    fun singout(message: String)

    /**
     *
     */
    fun openOperation()

    /**
     *
     */
    fun openMechanical()

    /**
     *
     */
    fun openIC()

    /**
     *
     */
    fun openElectrical()

    /**
     *
     */
    fun callNumber(number : String)

    /**
     *
     */
    fun closeSpeedDial()

    /**
     *
     */
    fun loadProfile(username : String, photo : String, job : String)

    /**
     *
     */
    fun userprofileActivity()
}
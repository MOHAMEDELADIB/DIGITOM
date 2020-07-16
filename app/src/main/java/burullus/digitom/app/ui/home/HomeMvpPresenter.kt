package burullus.digitom.app.ui.home

import burullus.digitom.app.data.network.model.ArticleData

/**
 *
 */
interface HomeMvpPresenter {
    /**
     *
     */
    fun getnews(url: String)

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
    fun onNavMenuCreated()

    /**
     *
     */
    fun onViewInitialized()

    /**
     *
     */
    fun drawerOperationPressed()

    /**
     *
     */
    fun drawerMechanicalPressed()

    /**
     *
     */
    fun drawerElectricalPressed()

    /**
     *
     */
    fun drawerICPressed()

    /**
     *
     */
    fun signOutPressed()

    /**
     *
     */
    fun menupressed()

    /**
     *
     */
    fun loggedout(message: String)

    /**
     *
     */
    fun contactpressed(id: Int)

    /**
     *
     */
    fun getNumber(number: String)
}
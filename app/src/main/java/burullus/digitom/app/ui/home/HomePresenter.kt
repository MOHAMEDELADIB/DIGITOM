package burullus.digitom.app.ui.home

import burullus.digitom.app.data.network.model.ArticleData
import burullus.digitom.app.data.network.model.ErrorModelClass

/**
 *
 */
@Suppress("SENSELESS_COMPARISON")
class HomePresenter(
    /**
     *
     */
    val view: HomeMvpView
) : HomeMvpPresenter {
    /**
     *
     */
    var interactor: HomeMvpInteractor = HomeInteractor(this)

    /**
     *
     */
    override fun getnews(url: String) {
        interactor.getnews(url)
        interactor.getuser()
    }

    /**
     *
     */
    override fun signOutPressed() {
        interactor.signout()
    }

    /**
     *
     */
    override fun onsuccess(news: List<ArticleData>) {
        view.onsuccess(news)
    }

    /**
     *
     */
    override fun onerror(message: String) {
        view.onerror(message)
    }

    /**
     *
     */
    override fun onNavMenuCreated() {

    }

    /**
     *
     */
    override fun onViewInitialized() {

    }

    /**
     *
     */
    override fun drawerOperationPressed() {
        view.openOperation()
        view.closedrawer()
    }

    /**
     *
     */
    override fun drawerMechanicalPressed() {
        view.openMechanical()
        view.closedrawer()
    }

    /**
     *
     */
    override fun drawerElectricalPressed() {
        view.openElectrical()
        view.closedrawer()
    }

    /**
     *
     */
    override fun drawerICPressed() {
        view.openIC()
        view.closedrawer()
    }


    /**
     *
     */
    override fun menupressed() {
        view.opendrawer()
    }

    /**
     *
     */
    override fun loggedout(message: String) {
        view.singout(message)
        view.closedrawer()
    }

    /**
     *
     */
    override fun contactpressed(id: Int) {
        interactor.getnumber(id)
    }

    /**
     *
     */
    override fun getNumber(number: String) {
        view.callNumber(number)
        view.closeSpeedDial()
    }

    /**
     *
     */
    override fun onError(merror: ErrorModelClass) {
        var message = ""
        if (merror.detail != null) view.onerror(merror.detail)
        if (merror.non_field_errors != null) {
            for (i in 0 until merror.non_field_errors.size) {
                message += merror.non_field_errors[i]
                if (i > 0) message += ",\n"
            }
            view.onerror(message)
        }
    }

    /**
     *
     */
    override fun getusername(username: String) {
        view.setusername(username)
    }
}

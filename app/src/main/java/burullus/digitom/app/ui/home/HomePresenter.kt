package burullus.digitom.app.ui.home

import burullus.digitom.app.data.network.model.ArticleData

class HomePresenter(val view: HomeMvpView) : HomeMvpPresenter {
    var interactor: HomeMvpInteractor = HomeInteractor(this)
    override fun getnews(url: String) {
        interactor.getnews(url)
    }

    override fun signOutPressed() {
        interactor.signout()
    }

    override fun onsuccess(news: List<ArticleData>) {
        view.onsuccess(news)
    }

    override fun onerror(message: String) {
        view.onerror(message)
    }

    override fun onNavMenuCreated() {

    }

    override fun onViewInitialized() {

    }

    override fun drawerOperationPressed() {
        view.openOperation()
    }

    override fun drawerMechanicalPressed() {
        view.openMechanical()
    }

    override fun drawerElectricalPressed() {
        view.openElectrical()
    }

    override fun drawerICPressed() {
        view.openIC()
    }


    override fun menupressed() {
        view.opendrawer()
    }

    override fun loggedout(message: String) {
        view.singout(message)
    }

    override fun contactpressed(id: Int) {
        interactor.getnumber(id)
    }

    override fun getNumber(number: String) {
        view.callNumber(number)
        view.closeSpeedDial()
    }


}
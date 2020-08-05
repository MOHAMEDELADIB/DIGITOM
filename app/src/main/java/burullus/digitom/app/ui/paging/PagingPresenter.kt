package burullus.digitom.app.ui.paging

import burullus.digitom.app.data.network.model.responses.Paging

/**
 *
 */
class PagingPresenter(
    /**
     *
     */
    val view: PagingMvpView
) : PagingMvpPresenter {
    /**
     *
     */
    var interactor: PagingMvpInteractor = PagingInteractor(this)

    /**
     *
     */
    override fun loaddata(detectedkks: String, head: String) {
        view.showprogrssbar()
        interactor.loaddata(detectedkks, head)
    }

    override fun onsucess(mpage: Paging) {
        view.onsucess(mpage)
        view.hideprogressbar()
    }

    override fun onerror(message: String) {
        view.onerror(message)
        view.hideprogressbar()
    }

    /**
     *
     */
    override fun nextpage(nextpage: String) {
        view.showprogrssbar()
        interactor.nextpage(nextpage)
    }

    /**
     *
     */
    override fun perviouspage(perviousPage: String) {
        view.showprogrssbar()
        interactor.perviouspage(perviousPage)
    }

    /**
     *
     */
    override fun backpressed() {
        view.backActivity()
    }

}
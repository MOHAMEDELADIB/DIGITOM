package burullus.digitom.app.ui.ocrscreen.dialog

import burullus.digitom.app.data.network.model.responses.Paging

/**
 *
 */
class DialogPresenter(
    /**
     *
     */
    val view: DialogView
) : DialogMvpPresenter {
    /**
     *
     */
    var interactor: DailogMvpInteractor = DialogInteractor(this)

    /**
     *
     */
    override fun send(KKS: String, header: String) {
        interactor.send(KKS, header)
    }

    /**
     *
     */
    override fun onsucess(pagedata: Paging) {
        view.close()
        if (pagedata.results.isNotEmpty()) view.onsucess(pagedata)
        else view.onerror("KKS not found")
    }

    /**
     *
     */
    override fun onerror(message: String) {
        view.onerror(message)
    }


}
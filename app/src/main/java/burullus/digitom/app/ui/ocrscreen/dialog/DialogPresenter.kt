package burullus.digitom.app.ui.ocrscreen.dialog

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
    override fun onsucess(kks: String) {
        view.close()
        view.onsucess(kks)
    }

    /**
     *
     */
    override fun onerror(message: String) {


        view.onerror(message)
    }


}
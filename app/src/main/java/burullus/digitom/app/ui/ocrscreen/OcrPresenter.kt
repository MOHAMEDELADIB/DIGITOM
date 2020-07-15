package burullus.digitom.app.ui.ocrscreen

class OcrPresenter(val view: OcrMvpView) : OcrMvpPresenter {
    var interactor: OcrMvpInteractor = OcrInteractor(this)

    override fun kksdetected(kks: String, head: String) {
        interactor.send(kks, head)
    }

    override fun onSucess(KKS: String) {
        view.onsucess(KKS)
    }

    override fun onerror(message: String) {
        view.onerror(message)
    }


    override fun backpressed() {
        view.backActivity()
    }

    override fun searchpressed() {
        view.dailog()
    }

    override fun flashPressed() {
        view.flasher()
    }

    override fun activityresume() {
        view.startCameraSource()
    }

    override fun detected(kks: String) {

    }

    override fun activityDestroy() {
        view.stopview()
    }

    override fun activityPause() {
        view.stopview()
    }


}
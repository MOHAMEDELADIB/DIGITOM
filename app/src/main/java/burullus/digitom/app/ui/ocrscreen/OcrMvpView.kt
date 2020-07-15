package burullus.digitom.app.ui.ocrscreen

interface OcrMvpView {
    fun backActivity()
    fun flasher()
    fun dailog()
    fun startCameraSource()
    fun onsucess(KKS: String)
    fun onerror(messsage: String)
    fun stopview()


}
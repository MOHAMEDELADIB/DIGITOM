package burullus.digitom.app.ui.mainActivity

import burullus.digitom.app.data.network.model.ElectricalData
import burullus.digitom.app.data.network.model.ICData
import burullus.digitom.app.data.network.model.MechenicalData
import burullus.digitom.app.data.network.model.OperationData

class MainPresenter(private val view: MainMvpview) : MainMvpPresenter {
    var interactor: MainMvpInteractor = MainInteractor(this)
    override fun getdata(kks: String, header: String) {
        view.showProgress()
        interactor.getdata(kks, header)
    }

    override fun backActivity() {
        view.back()
    }

    override fun homeActivity() {
        view.homePage()
    }

    override fun operationData(data: OperationData) {
        view.operationData(data)
        view.hideProgress()
    }

    override fun mechanicalData(data: MechenicalData) {
        view.mechanicalData(data)
        view.hideProgress()
    }

    override fun electricalData(data: ElectricalData) {
        view.electricalData(data)
        view.hideProgress()
    }

    override fun icData(data: ICData) {
        view.icData(data)
        view.hideProgress()
    }


    override fun onerror(message: String) {
        view.onerror(message)
    }
}
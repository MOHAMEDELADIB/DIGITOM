package burullus.digitom.app.ui.mainActivity

import burullus.digitom.app.data.network.model.ElectricalData
import burullus.digitom.app.data.network.model.ICData
import burullus.digitom.app.data.network.model.MechenicalData
import burullus.digitom.app.data.network.model.OperationData

interface MainMvpPresenter {
    fun getdata(kks: String, header: String)
    fun backActivity()
    fun homeActivity()
    fun operationData(data: OperationData)
    fun mechanicalData(data: MechenicalData)
    fun electricalData(data: ElectricalData)
    fun icData(data: ICData)
    fun onerror(message: String)
}


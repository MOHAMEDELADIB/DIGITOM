package burullus.digitom.app.data.network.model

data class MechenicalData(
    var kks: String,
    var description: String,
    var system: GeneralSystem,
    var dataSheet: List<DataSheet>,
    var voltage: Volt,
    var location: Location,
    var site: Location,
    var drawing: List<DataSheet>,
    var mdata: List<DataSheet>

)
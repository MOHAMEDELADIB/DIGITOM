package burullus.digitom.app.data.network.model

data class ICData(
    var kks: String,
    var description: String,
    var system: GeneralSystem,
    var datasheet: List<DataSheet>,
    var pcc: Location,
    var site: Location,
    var slot_no: String,
    val cabient_name: String,
    var interconnection: List<DataSheet>,
    var faults: List<DataSheet>,
    var location: Location
)

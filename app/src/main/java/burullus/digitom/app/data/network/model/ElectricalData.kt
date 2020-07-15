package burullus.digitom.app.data.network.model

data class ElectricalData(
    var kks: String,
    var description: String,
    var system: GeneralSystem,
    var dataSheet: List<DataSheet>,
    var trouble: List<DataSheet>,
    var wiring: List<DataSheet>,
    var pcc: Location,
    var site: Location,
    var voltage: Volt,
    var feeder_location: String,
    var location: Location
)

package burullus.digitom.app.data.network.model

/**
 *
 */
data class System(
    /**
     *
     */
    var kks: String,
    /**
     *
     */
    var description: String
    ,
    /**
     *
     */
    var trouble: List<DataSheet>,
    /**
     *
     */
    var pid: List<DataSheet>
)

package burullus.digitom.app.data.network.model.responses

/**
 *
 */
data class Paging(
    /**
     *
     */
    var count: String,
    /**
     *
     */
    var next: String,
    /**
     *
     */
    var previous: String,
    /**
     *
     */
    var results: List<SearchResult>
)

/**
 *
 */
data class SearchResult(
    /**
     *
     */
    var kks: String,
    /**
     *
     */
    var description: String

)

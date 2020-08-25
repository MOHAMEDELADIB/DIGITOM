package burullus.digitom.app.data.network.model.responses

/**
 *
 */
data class LoginResponse(
    /**
     *
     */
    var access_token: String,
    /**
     *
     */
    var refresh_token: String,

    var non_field_errors: String
)


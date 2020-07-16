package burullus.digitom.app.data.network.model.requests

/**
 * data class for change password
 */
data class ForgetAuth(
    /**
     * token Key
     */
    var token: String,
    /**
     * Password
     */
    var new_password1: String,
    /**
     * Confirm Password
     */
    var new_password2: String

)
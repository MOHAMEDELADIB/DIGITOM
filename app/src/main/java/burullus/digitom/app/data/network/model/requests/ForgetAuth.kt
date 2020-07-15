package burullus.digitom.app.data.network.model.requests

data class ForgetAuth(
    var token: String,
    var new_password1: String,
    var new_password2: String

)
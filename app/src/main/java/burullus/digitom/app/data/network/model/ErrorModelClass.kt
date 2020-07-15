package burullus.digitom.app.data.network.model

import java.util.*

class ErrorModelClass(
    var email: ArrayList<String>,
    var password1: ArrayList<String>,
    var non_field_errors: ArrayList<String>,
    var new_password1: ArrayList<String>,
    var new_password2: ArrayList<String>,
    var detail: String
)
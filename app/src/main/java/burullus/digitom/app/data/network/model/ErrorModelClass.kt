package burullus.digitom.app.data.network.model

import java.util.*

/**
 *
 */
class ErrorModelClass(
    /**
     *
     */
    var email : ArrayList<String>,
    /**
     *
     */
    var photo : ArrayList<String>,

    var phone_number : ArrayList<String>,

    var first_name : ArrayList<String>,
    var last_name : ArrayList<String>,
    var job : ArrayList<String>,
    var site : ArrayList<String>,

    var password1 : ArrayList<String>,
    /**
     *
     */
    var non_field_errors : ArrayList<String>,
    /**
     *
     */
    var new_password1 : ArrayList<String>,
    /**
     *
     */
    var new_password2 : ArrayList<String>,
    /**
     *
     */
    var token : String,
    /**
     *
     */
    var detail : String,

    /**
     *
     */
    var code : String
)
package burullus.digitom.app.data.network.api

/**
 *  Deep link  url
 */
const val Active_email: String = "confirm-email"

/**
 *
 */
const val Active_email_Seg: String = "android-email-verify-key"

/**
 * Host Url
 */
//const val URLA: String = "https://emd.digitom.mosaic-dev.siemens.cloud/user/api/"
const val URLA: String = "http://178.128.28.204/user/api/"

/**
 * SHARED_PREFERENCE_FILE
 */
const val SHARED_PREFERENCE_FILE: String = "digitom"

/**
 * Token in SHARED_PREFERENCE_FILE
 */
const val SP_TOKEN: String = "token"
const val RF_TOKEN: String = "refresh"

/**
 *
 */
const val Ref_token: String = "Refresh_Token"

/**
 * Operation URL
 */
const val operationURL: String =
    "http://178.128.28.204/operation/apiv1/detial/?q="

/**
 *
 */
const val pagingurl: String =
    "http://178.128.28.204/operation/apiv1/list/?q="

/**
 * ُElectrical URL
 */
const val ElectURL: String =
    "http://178.128.28.204/electrical/apiv1/detial/?q="

/**
 * Mechenical URL
 */
const val MechURL: String =
    "http://178.128.28.204/mechanical/apiv1/detial/?q="

/**
 * IC URL
 */
const val ICURL: String = "http://178.128.28.204/ic/apiv1/detial/?q="

/**
 * TOKEN CHECK URL
 */
const val TokenCheck: String =
    "http://178.128.28.204/operation/apiv1/detial/?q=41MBV21AP001"

/**
 * News URL
 */
const val NewsUrl: String = "http://178.128.28.204/updates/apiv1/articles/"

/**
 * Non Authenticate Message
 */
const val noauthenticate: String = "You are not logged in or registered"

/**
 * CCR Number
 */
const val CCRNumber: String = "+201284545599"

/**
 * Safety number
 */
const val SaetyNumber: String = "01283524496"

/**
 * OperationNumber
 */
const val OpertionNumber: String = "+201228880953"

/**
 * Station Manager Number
 */
const val StationNumber: String = "+201206468060"

/**
 * KKS Pattern
 */
const val pattern1: String = "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}"

/**
 *
 */
const val Patteren2: String =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}\\|{2}[a-zA-Z]{2}[0-9]{2}"

/**
 *  KKS Pattern
 */
const val Patteren3: String =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{1}\\|{2}[a-zA-Z]{2}[0-9]{2}"

/**
 *
 */
const val Patteren4: String = "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}\\|{2}[a-zA-Z]{2}[0-9]{2}"

/**
 *
 */
const val Patteren5: String = "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{1}\\|{2}[a-zA-Z]{2}[0-9]{2}"

/**
 *
 */
const val Patteren6: String =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}\\.[a-zA-z]{1}\\|{2}[a-zA-Z]{2}[0-9]{2}"

/**
 *
 */
const val Patteren7: String =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}\\.[0-9]{1}\\|{2}[a-zA-Z]{2}[0-9]{2}"

/**
 *
 */
const val Patteren8: String =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}\\.[a-zA-Z]{3}\\|{2}[a-zA-Z]{2}[0-9]{2}"

/**
 *
 */
const val Patteren9: String =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}" +
            "[a-zA-Z]{1}\\.[a-zA-Z]{2}[0-9]{3}\\|{2}[a-zA-Z]{2}[0-9]{2}"

/**
 *
 */
const val Patteren10: String =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{2}\\|{2}[a-zA-Z]{4}"

/**
 *
 */
val KKSPattern: Array<String> = arrayOf(
    Patteren9,
    Patteren8,
    Patteren7,
    Patteren6,
    Patteren10,
    Patteren3,
    Patteren2,
    Patteren4,
    Patteren5,
    pattern1
)

/**
 *
 */
val KKSPatternlength: Array<Int> = arrayOf(25, 22, 20, 20, 20, 19, 18, 15, 14, 12)


/**
 *
 */
val OperationHeaders: Array<String> =
    arrayOf("Operations tips", "Fault description", "PID", "General information")

/**
 *
 */
val MechenicalHeader: Array<String> =
    arrayOf("Drawing & Partlist", "Datasheet", "Mehanical documentation", "General information")

/**
 *
 */
val ElectricalHeader: Array<String> =
    arrayOf("Wiring digram", "Datasheet", "Troubleshooting", "General information")

/**
 *
 */
val ICHeader: Array<String> =
    arrayOf("Interconnection", "Datasheet", "Fault descriptions", "General information")

/**
 *
 */
const val Network_Message: String = "Timeout unable to connect to the server"

/**
 *
 */
const val Server_error: String = "Internal Server Error"

/**
 *
 */
const val welcome_Message: String = "Welcome To DIGITOM"

/**
 *
 */
const val Verify: String = "Please Press the link to Verify your Email"

/**
 *
 */
const val Rest_key: String = "android-password-reset-key"

/**
 *
 */
const val userURL: String = "http://178.128.28.204/user/profile/"

/*
*
*/
const val refresh = "/user/api/token/refresh/"
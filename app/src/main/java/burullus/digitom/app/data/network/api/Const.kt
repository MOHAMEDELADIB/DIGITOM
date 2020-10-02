package burullus.digitom.app.data.network.api

/**
 *  Deep link  url
 */
const val Active_email: String = "confirm-email"

/**
 *
 */
const val Active_email_Seg : String = "digitom-confirm-email"

/**
 * Host Url
 */
//const val URLA: String = "https://emd.digitom.mosaic-dev.siemens.cloud/user/api/"
const val URLA : String = "https://emd.digitom.mosaic.siemens-energy.cloud/"

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
    "https://emd.digitom.mosaic.siemens-energy.cloud/operation/apiv1/detial/?q="

/**
 *
 */
const val pagingurl: String =
    "https://emd.digitom.mosaic.siemens-energy.cloud/operation/apiv1/list/?q="

/**
 * ُElectrical URL
 */
const val ElectURL: String =
    "https://emd.digitom.mosaic.siemens-energy.cloud/electrical/apiv1/detial/?q="

/**
 * Mechenical URL
 */
const val MechURL: String =
    "https://emd.digitom.mosaic.siemens-energy.cloud/mechanical/apiv1/detial/?q="

/**
 * IC URL
 */
const val ICURL : String = "https://emd.digitom.mosaic.siemens-energy.cloud/ic/apiv1/detial/?q="

/**
 * TOKEN CHECK URL
 */
const val TokenCheck: String =
    "https://emd.digitom.mosaic.siemens-energy.cloud/operation/apiv1/detial/?q=41MBV21AP001"

/**
 * News URL
 */
const val NewsUrl : String =
    "https://emd.digitom.mosaic.siemens-energy.cloud/updates/apiv1/articles/"

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
    arrayOf("Drawings & Partlist", "Datasheet", "Mehanical documentation", "General information")

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
const val Rest_key : String = "digitom-password-reset"

/**
 *
 */
const val userURL : String = "httpS://emd.digitom.mosaic.siemens-energy.cloud/user/profile/"

/*
*
*/
const val refresh = "/user/api/token/refresh/"

/**
 *
 */
const val Check : String = "Please Login to renew your session"
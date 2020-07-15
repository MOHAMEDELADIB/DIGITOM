package burullus.digitom.app.data.network.api

const val Active_email = "confirm-email"
const val URLA: String = "https://emd.digitom.mosaic-dev.siemens.cloud/user/api/"
const val SHARED_PREFERENCE_FILE = "digitom"
const val SP_TOKEN = "token"
const val operationURL = "https://emd.digitom.mosaic-dev.siemens.cloud/operation/apiv1/detial/?q="
const val ElectURL = "https://emd.digitom.mosaic-dev.siemens.cloud/electrical/apiv1/detial/?q="
const val MechURL = "https://emd.digitom.mosaic-dev.siemens.cloud/mechanical/apiv1/detial/?q="
const val ICURL = "https://emd.digitom.mosaic-dev.siemens.cloud/ic/apiv1/detial/?q="
const val TokenCheck =
    "https://emd.digitom.mosaic-dev.siemens.cloud/operation/apiv1/detial/?q=41MBV21AP001"
const val NewsUrl = "https://emd.digitom.mosaic-dev.siemens.cloud/updates/apiv1/articles/"
const val noauthenticate = "You are not logged in or registered"
const val CCRNumber = "+201099793228"
const val SaetyNumber = "01283524496"
const val OpertionNumber = "+201228880953"
const val StationNumber = "+201206468060"
const val pattern1 = "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}"
const val Patteren2 = "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}\\|{2}[a-zA-Z]{2}[0-9]{2}"
const val Patteren3 =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{1}\\|{2}[a-zA-Z]{2}[0-9]{2}"
const val Patteren4 = "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}\\|{2}[a-zA-Z]{2}[0-9]{2}"
const val Patteren5 = "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{1}\\|{2}[a-zA-Z]{2}[0-9]{2}"
const val Patteren6 =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}\\.[a-zA-z]{1}\\|{2}[a-zA-Z]{2}[0-9]{2}"
const val Patteren7 =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}\\.[0-9]{1}\\|{2}[a-zA-Z]{2}[0-9]{2}"
const val Patteren8 =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}\\.[a-zA-Z]{3}\\|{2}[a-zA-Z]{2}[0-9]{2}"
const val Patteren9 =
    "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{1}\\.[a-zA-Z]{2}[0-9]{3}\\|{2}[a-zA-Z]{2}[0-9]{2}"
const val Patteren10 = "[0-9]{2}[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{2}\\|{2}[a-zA-Z]{4}"
val KKSPattern = arrayOf(
    pattern1, Patteren2, Patteren3, Patteren4,
    Patteren5, Patteren6, Patteren7, Patteren8, Patteren9, Patteren10
)

val KKSPatternlength = arrayOf(12, 18, 19, 15, 14, 20, 20, 22, 25, 20)
val OperationHeaders =
    arrayOf("Operation&troubleshooting", "Fault Description", "PID", "General Information")
val MechenicalHeader =
    arrayOf("Drawing&PartList", "DataSheet", "Mehanical Documentation", "General Information")
val ElectricalHeader =
    arrayOf("Wiring Digram", "DataSheet", "TroubleShooting", "General Information")
val ICHeader = arrayOf("InterConnection", "DataSheet", "FaultDescriptions", "General Information")
const val Network_Message = "timeout Unable to connect to the server"
const val Server_error = "Internal Server Error"
const val welcome_Message = "Welcome To DIGITOM"
const val Verify = "Please Press the link to Verify your Email"
const val Rest_key = "android-password-reset-key"
package burullus.digitom.app.ui.deeplinks

interface DeeplinkMvpview {
    fun hideprogressbar()
    fun showprogressbar()
    fun deeplinkSuccess(message: String)
    fun deeplinkError(message: String)
    fun deeplinknavigate(key: String)

}
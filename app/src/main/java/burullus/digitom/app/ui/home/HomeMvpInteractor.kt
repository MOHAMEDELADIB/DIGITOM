package burullus.digitom.app.ui.home

/**
 *
 */
interface HomeMvpInteractor {
    /**
     *
     */
    fun getnews(url: String)

    /**
     *
     */
    fun signout()

    /**
     *
     */
    fun getnumber(id: Int)

    /**
     *
     */
    fun getuser()
}
package burullus.digitom.app.ui.deeplinks

import android.annotation.SuppressLint
import burullus.digitom.app.data.network.api.Network_Message
import burullus.digitom.app.data.network.api.Server_error
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 * The interactor Class for check the activation token
 */
class DeepLinkInteractor(private val presenter: DeeplinkMvpPresenter) : DeepLinkMvpInteractor {
    /**
     * the function for post the token to show the token validity
     */
    @SuppressLint("CheckResult")
    override fun activedeeplink(key: String) {
        Repository.activate(key)
            .subscribe({ (detail) ->
                presenter.onDeepLinkSuccess(detail)
            })
            { error ->

                if (error is HttpException) {
                    if (error.code() != 500) {
                        val gson = GsonBuilder().create()
                        val mError: ErrorModelClass
                        val responseBody: ResponseBody? =
                            error.response()?.errorBody()
                        mError = gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                        presenter.onDeeplinkError(mError)
                    }
                } else presenter.onNetworkError(Server_error)
                if (error is IOException) {
                    presenter.onNetworkError(Network_Message)
                }

            }
    }
}
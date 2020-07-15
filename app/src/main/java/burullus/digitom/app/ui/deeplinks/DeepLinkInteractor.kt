package burullus.digitom.app.ui.deeplinks

import android.annotation.SuppressLint
import burullus.digitom.app.data.network.api.Network_Message
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

class DeepLinkInteractor(private val presenter: DeeplinkMvpPresenter) : DeepLinkMvpInteractor {
    @SuppressLint("CheckResult")
    override fun activedeeplink(key: String) {
        Repository.activate(key)
            .subscribe({ result ->
                presenter.onDeepLinkSuccess(result.detail)
            })
            { error ->

                if (error is HttpException) {
                    val gson = GsonBuilder().create()
                    val mError: ErrorModelClass
                    val responseBody: ResponseBody? =
                        error.response()?.errorBody()
                    mError = gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                    presenter.onDeeplinkError(mError)
                }
                if (error is IOException) {
                    presenter.onNetworkError(Network_Message)
                }

            }
    }
}
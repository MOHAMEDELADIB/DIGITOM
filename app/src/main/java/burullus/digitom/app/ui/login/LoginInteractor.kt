package burullus.digitom.app.ui.login

import android.annotation.SuppressLint
import burullus.digitom.app.data.network.api.Server_error
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 *
 */
@SuppressLint("CheckResult")
class LoginInteractor(private val presenter: LoginMvpPresenter) : LoginMvpInteractor {
    /**
     *
     */
    override fun signin(email: String, password: String) {
        Repository.login(email, password)
            .subscribe({ (access_token, refresh_token) ->
                presenter.encrypt(access_token, refresh_token)
                presenter.onsuccess()
            }, { error ->
                if (error is HttpException) {
                    if (error.code() != 401) {
                        if (error.code() != 500 && error.code() != 405) {
                            val gson = GsonBuilder().create()
                            val mError : ErrorModelClass
                            val responseBody : ResponseBody? = error.response()?.errorBody()
                            mError =
                                gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                            presenter.onerror(mError)
                        } else presenter.onNetworkError(Server_error)
                    }
                }
                if (error is IOException) {
                    presenter.onNetworkError(error.message ?: return@subscribe)
                }
            })

    }


}
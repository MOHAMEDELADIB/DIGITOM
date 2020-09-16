package burullus.digitom.app.ui.forgetpassword

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
 *
 */
class ForgetPasswordInteractor(private val presenter: ForgetpasswordMvpPresenter) :
    ForgetpasswordMvpInteractor {
    /**
     *
     */
    @SuppressLint("CheckResult")
    override fun forget(email: String) {
        Repository.restpassword(email)
            .subscribe({ (detail) ->
                presenter.onsuccess(detail)
            }, { error ->

                if (error is HttpException) {
                    if (error.code() != 401) {
                        if (error.code() != 500 && error.code() != 405) {
                            val gson = GsonBuilder().create()
                            val mError : ErrorModelClass
                            val responseBody : ResponseBody? =
                                error.response()?.errorBody()
                            mError =
                                gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                            presenter.onError(mError)
                        } else presenter.onerror(Server_error)
                    }
                }
                if (error is IOException) {
                    presenter.onerror(Network_Message)
                }

            })
    }
}


package burullus.digitom.app.ui.register

import android.annotation.SuppressLint
import burullus.digitom.app.data.network.api.Network_Message
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
class RegisterInteractor(private val presenter: RegisterMvpPresenter) : RegisterMvpInteractor {

    /**
     *
     */
    override fun signup(email: String, password: String, confirmPassword: String) {
        Repository.signup(email, password, confirmPassword)
            .subscribe({ (detail) ->
                presenter.onsuccess(detail)
            })
            { error ->

                if (error is HttpException) {
                    val gson = GsonBuilder().create()
                    val mError: ErrorModelClass
                    val responseBody: ResponseBody? =
                        error.response()?.errorBody()
                    if (error.code() != 500) {
                        mError = gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                        presenter.onerror(mError)
                    } else {
                        presenter.onNetworkError(Network_Message)
                    }
                }
                if (error is IOException) {
                    presenter.onNetworkError(Network_Message)
                }

            }
    }


}
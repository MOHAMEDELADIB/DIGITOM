package burullus.digitom.app.ui.changepassword

import android.annotation.SuppressLint
import burullus.digitom.app.data.network.api.Network_Message
import burullus.digitom.app.data.network.api.Server_error
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

class ChangePasswordInteractor(private val presenter: ChangePasswordMvpPresenter) :
    ChangePasswordMvpInteractor {
    @SuppressLint("CheckResult")

    override fun auth(token: String, new_password1: String, new_password2: String) {
        Repository.forgetauth(token, new_password1, new_password2)
            .subscribe({ result ->
                presenter.onsuccess(result.detail)
            }, { error ->

                if (error is HttpException) {
                    if (error.code() != 500) {
                        val gson = GsonBuilder().create()
                        val mError: ErrorModelClass
                        val responseBody: ResponseBody? =
                            error.response()?.errorBody()
                        mError =
                            gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                        presenter.onError(mError)
                    } else presenter.onerror(Server_error)
                    if (error is IOException) {
                        presenter.onerror(Network_Message)
                    }
                }
            })
    }


}
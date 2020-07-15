package burullus.digitom.app.ui.forgetpassword

import android.annotation.SuppressLint
import burullus.digitom.app.data.network.api.noauthenticate
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException


class ForgetPasswordInteractor(private val presenter: ForgetpasswordMvpPresenter) :
    ForgetpasswordMvpInteractor {
    @SuppressLint("CheckResult")
    override fun forget(email: String) {
        Repository.restpassword(email)
            .subscribe({ result ->
                presenter.onsuccess(result.detail)
            }, { error ->
                try {
                    if (error is HttpException) {
                        val gson = GsonBuilder().create()
                        val mError: ErrorModelClass
                        val responseBody: ResponseBody? =
                            error.response()?.errorBody()
                        mError = gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                        if (error.code() == 401) presenter.onerror(noauthenticate) else presenter.onerror(
                            mError.detail
                        )
                    }
                    if (error is IOException) {
                        presenter.onerror(error.message!!)
                    }
                } catch (e: java.lang.Exception) {
                    presenter.onerror(error.message!!)
                }
            })
    }
}


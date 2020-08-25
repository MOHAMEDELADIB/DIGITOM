package burullus.digitom.app.ui.splash

import android.annotation.SuppressLint
import burullus.digitom.app.data.network.api.welcome_Message
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 *
 */
class SplashInteractor(private val presenter: SplashMvpPresenter) : SplashMvpInteractor {

    /**
     *
     */
    @SuppressLint("CheckResult")
    override fun isAuthen(kks: String) {
        Repository.getOperation(kks)
            .subscribe({
                presenter.onsucess()
                presenter.loginMessage(welcome_Message)
            }, { error ->
                if (error is HttpException) {
                    val gson = GsonBuilder().create()
                    val mError: ErrorModelClass
                    val responseBody: ResponseBody? = error.response()?.errorBody()
                    if (error.code() != 500) {
                        mError = gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                        presenter.onerror()
                    } else {
                        presenter.onerror()
                    }
                }
                if (error is IOException) {
                    presenter.onerror()
                }
            })

    }

}
package burullus.digitom.app.ui.ocrscreen

import android.annotation.SuppressLint
import burullus.digitom.app.data.network.api.*
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 *
 */
class OcrInteractor(private val presenter: OcrPresenter) : OcrMvpInteractor {
    /**
     *
     */
    @SuppressLint("CheckResult")
    override fun send(kks: String, head: String) {
        when (head) {
            "Operations " -> {
                Repository.getOperation(operationURL + kks)
                    .subscribe({
                        presenter.onSucess(kks)
                    })
                    { error ->
                        if (error is HttpException) {
                            if (error.code() != 401) {
                                if (error.code() != 500 && error.code() != 405) {
                                    val gson = GsonBuilder().create()
                                    val mError : ErrorModelClass
                                    val responseBody : ResponseBody? =
                                        error.response()?.errorBody()
                                    mError = gson.fromJson(
                                        responseBody?.string(),
                                        ErrorModelClass::class.java
                                    )
                                    presenter.onerror(mError.detail)
                                }
                            } else presenter.onerror(Server_error)
                            if (error is IOException) {
                                presenter.onerror(Network_Message)
                            }

                        }
                    }
            }
            "Mechanical " -> {
                Repository.getMechenical(MechURL + kks)
                    .subscribe({
                        presenter.onSucess(kks)
                    })
                    { error ->

                        if (error is HttpException) {
                            if (error.code() != 401) {
                                if (error.code() != 500 && error.code() != 405) {
                                    val gson = GsonBuilder().create()
                                    val mError : ErrorModelClass
                                    val responseBody : ResponseBody? =
                                        error.response()?.errorBody()
                                    mError = gson.fromJson(
                                        responseBody?.string(),
                                        ErrorModelClass::class.java
                                    )
                                    presenter.onerror(mError.detail)
                                }
                            } else presenter.onerror(Server_error)
                            if (error is IOException) {
                                presenter.onerror(Network_Message)
                            }
                        }
                    }
            }
            "Electrical " -> {
                Repository.getElectrical(ElectURL + kks)
                    .subscribe({
                        presenter.onSucess(kks)
                    })
                    { error ->

                        if (error is HttpException) {
                            if (error.code() != 401) {
                                if (error.code() != 500 && error.code() != 405) {
                                    val gson = GsonBuilder().create()
                                    val mError : ErrorModelClass
                                    val responseBody : ResponseBody? =
                                        error.response()?.errorBody()
                                    mError = gson.fromJson(
                                        responseBody?.string(),
                                        ErrorModelClass::class.java
                                    )
                                    presenter.onerror(mError.detail)
                                }
                            } else presenter.onerror(Server_error)
                            if (error is IOException) {
                                presenter.onerror(Network_Message)
                            }
                        }
                    }

            }
            "I&C " -> {
                Repository.getIC(ElectURL + kks)
                    .subscribe({
                        presenter.onSucess(kks)
                    })
                    { error ->

                        if (error is HttpException) {
                            if (error.code() != 401) {
                                if (error.code() != 500 && error.code() != 405) {
                                    val gson = GsonBuilder().create()
                                    val mError : ErrorModelClass
                                    val responseBody : ResponseBody? =
                                        error.response()?.errorBody()
                                    mError = gson.fromJson(
                                        responseBody?.string(),
                                        ErrorModelClass::class.java
                                    )
                                    presenter.onerror(mError.detail)
                                } else presenter.onerror(Server_error)
                            }
                        }
                        if (error is IOException) {
                            presenter.onerror(Network_Message)
                        }

                    }
            }

        }
    }
}

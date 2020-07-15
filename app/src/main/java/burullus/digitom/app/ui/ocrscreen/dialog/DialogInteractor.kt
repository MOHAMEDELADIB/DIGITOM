package burullus.digitom.app.ui.ocrscreen.dialog

import burullus.digitom.app.data.network.api.*
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

class DialogInteractor(private val presenter: DialogPresenter) : DailogMvpInteractor {
    override fun send(KKS: String, header: String) {
        when (header) {
            "Operations " -> {
                Repository.getOperation(operationURL + KKS)
                    .subscribe({
                        presenter.onsucess(KKS)
                    })
                    { error ->

                        if (error is HttpException) {
                            val gson = GsonBuilder().create()
                            val mError: ErrorModelClass
                            val responseBody: ResponseBody? =
                                error.response()?.errorBody()
                            mError =
                                gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                            if (error.code() == 401) presenter.onerror(noauthenticate) else presenter.onerror(
                                mError.detail
                            )
                        }
                        if (error is IOException) {
                            presenter.onerror(error.message!!)
                        }

                    }
            }
            "Mechanical " -> {
                Repository.getMechenical(MechURL + KKS)
                    .subscribe({
                        presenter.onsucess(KKS)
                    })
                    { error ->

                        if (error is HttpException) {
                            val gson = GsonBuilder().create()
                            val mError: ErrorModelClass
                            val responseBody: ResponseBody? =
                                error.response()?.errorBody()
                            mError =
                                gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                            if (error.code() == 401) presenter.onerror(noauthenticate) else presenter.onerror(
                                mError.detail
                            )
                        }
                        if (error is IOException) {
                            presenter.onerror(error.message!!)
                        }

                    }
            }
            "Electrical " -> {
                Repository.getElectrical(ElectURL + KKS)
                    .subscribe({
                        presenter.onsucess(KKS)
                    })
                    { error ->

                        if (error is HttpException) {
                            val gson = GsonBuilder().create()
                            val mError: ErrorModelClass
                            val responseBody: ResponseBody? =
                                error.response()?.errorBody()
                            mError =
                                gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                            if (error.code() == 401) presenter.onerror(noauthenticate) else presenter.onerror(
                                mError.detail
                            )
                        }
                        if (error is IOException) {
                            presenter.onerror(error.message!!)
                        }

                    }

            }
            "I&C " -> {
                Repository.getIC(ICURL + KKS)
                    .subscribe({
                        presenter.onsucess(KKS)
                    })
                    { error ->

                        if (error is HttpException) {
                            val gson = GsonBuilder().create()
                            val mError: ErrorModelClass
                            val responseBody: ResponseBody? =
                                error.response()?.errorBody()
                            mError =
                                gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                            if (error.code() == 401) presenter.onerror(noauthenticate) else presenter.onerror(
                                mError.detail
                            )
                        }
                        if (error is IOException) {
                            presenter.onerror(error.message!!)
                        }

                    }
            }

        }
    }

}
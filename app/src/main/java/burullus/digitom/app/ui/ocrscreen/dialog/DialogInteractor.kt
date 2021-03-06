package burullus.digitom.app.ui.ocrscreen.dialog

import burullus.digitom.app.data.network.api.Network_Message
import burullus.digitom.app.data.network.api.Server_error
import burullus.digitom.app.data.network.api.pagingurl
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 *
 */
class DialogInteractor(private val presenter: DialogPresenter) : DailogMvpInteractor {
    /**
     *
     */
    override fun send(KKS: String, header: String) {
        when (header) {
            "Operations " -> {
                Repository.getPaging(pagingurl + KKS)
                    .subscribe({ result ->
                        presenter.onsucess(result)
                    })
                    { error ->

                        if (error is HttpException) {
                            if (error.code() != 401) {
                                if (error.code() != 500 && error.code() != 405) {
                                    val gson = GsonBuilder().create()
                                    val mError : ErrorModelClass
                                    val responseBody : ResponseBody? =
                                        error.response()?.errorBody()
                                    mError =
                                        gson.fromJson(
                                            responseBody?.string(),
                                            ErrorModelClass::class.java
                                        )

                                    presenter.onerror(mError.detail)
                                } else {
                                    presenter.onerror(Server_error)
                                }
                            }
                        }
                        if (error is IOException) {
                            presenter.onerror(Network_Message)
                        }

                    }
            }
            /*"Mechanical " -> {
                Repository.getMechenical(MechURL + KKS)
                    .subscribe({result ->
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
                            if (error.code() == 401) presenter.onerror(noauthenticate)
                            else presenter.onerror(
                                mError.detail
                            )
                        }
                        if (error is IOException) {
                            presenter.onerror(error.message ?: return@subscribe)
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
                            presenter.onerror(mError.detail)
                        }
                        if (error is IOException) {
                            presenter.onerror(error.message ?: return@subscribe)
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
                            presenter.onerror(mError.detail)
                        }
                        if (error is IOException) {
                            presenter.onerror(error.message ?: return@subscribe)
                        }

                    }
            }*/

        }
    }

}
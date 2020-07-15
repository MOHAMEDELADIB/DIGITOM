package burullus.digitom.app.ui.mainActivity

import burullus.digitom.app.data.network.api.*
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

class MainInteractor(private val presenter: MainMvpPresenter) : MainMvpInteractor {
    override fun getdata(kks: String, header: String) {
        when (header) {
            "Operations " -> {
                Repository.getOperation(operationURL + kks)
                    .subscribe({ result ->
                        presenter.operationData(result)
                    })
                    { error ->

                        if (error is HttpException) {
                            if (error.code() != 500) {
                                val gson = GsonBuilder().create()
                                val mError: ErrorModelClass
                                val responseBody: ResponseBody? =
                                    error.response()?.errorBody()
                                mError = gson.fromJson(
                                    responseBody?.string(),
                                    ErrorModelClass::class.java
                                )
                                if (error.code() == 401) presenter.onerror(noauthenticate) else presenter.onerror(
                                    mError.detail
                                )
                            } else presenter.onerror(Server_error)
                        }
                        if (error is IOException) {
                            presenter.onerror(error.message!!)
                        }

                    }

            }
            "Mechanical " -> {
                Repository.getMechenical(MechURL + kks)
                    .subscribe({ result ->
                        presenter.mechanicalData(result)
                    })
                    { error ->
                        try {
                            if (error is HttpException) {
                                val gson = GsonBuilder().create()
                                val mError: ErrorModelClass
                                val responseBody: ResponseBody? =
                                    error.response()?.errorBody()
                                mError = gson.fromJson(
                                    responseBody?.string(),
                                    ErrorModelClass::class.java
                                )
                                if (error.code() == 401) presenter.onerror(noauthenticate) else presenter.onerror(
                                    mError.detail
                                )
                            }
                            if (error is IOException) {
                                presenter.onerror(error.message!!)
                            }
                        } catch (e: java.lang.Exception) {
                        }
                    }
            }
            "Electrical " -> {
                Repository.getElectrical(ElectURL + kks)
                    .subscribe({ result ->
                        presenter.electricalData(result)
                    })
                    { error ->
                        try {
                            if (error is HttpException) {
                                val gson = GsonBuilder().create()
                                val mError: ErrorModelClass
                                val responseBody: ResponseBody? =
                                    error.response()?.errorBody()
                                mError = gson.fromJson(
                                    responseBody?.string(),
                                    ErrorModelClass::class.java
                                )
                                if (error.code() == 401) presenter.onerror(noauthenticate) else presenter.onerror(
                                    mError.detail
                                )
                            }
                            if (error is IOException) {
                                presenter.onerror(error.message!!)
                            }
                        } catch (e: java.lang.Exception) {
                        }
                    }

            }
            "I&C " -> {
                Repository.getIC(ICURL + kks)
                    .subscribe({ result ->
                        presenter.icData(result)
                    })
                    { error ->
                        try {
                            if (error is HttpException) {
                                val gson = GsonBuilder().create()
                                val mError: ErrorModelClass
                                val responseBody: ResponseBody? =
                                    error.response()?.errorBody()
                                mError = gson.fromJson(
                                    responseBody?.string(),
                                    ErrorModelClass::class.java
                                )
                                if (error.code() == 401) presenter.onerror(noauthenticate) else presenter.onerror(
                                    mError.detail
                                )
                            }
                            if (error is IOException) {
                                presenter.onerror(error.message!!)
                            }
                        } catch (e: java.lang.Exception) {
                        }
                    }
            }

        }
    }
}
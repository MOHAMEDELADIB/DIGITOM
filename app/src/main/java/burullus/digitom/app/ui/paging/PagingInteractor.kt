package burullus.digitom.app.ui.paging

import burullus.digitom.app.data.network.api.Network_Message
import burullus.digitom.app.data.network.api.Server_error
import burullus.digitom.app.data.network.api.pagingurl
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import burullus.digitom.app.ui.ocrscreen.OcrCaptureActivity.Companion.head
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException


/**
 *
 */
class PagingInteractor(private val presenter: PagingPresenter) : PagingMvpInteractor {


    override fun loaddata(detectedkks: String, head: String) {
        when (head) {
            "Operations " -> {
                Repository.getPaging(pagingurl + detectedkks)
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
                                }
                            } else {
                                presenter.onerror(Server_error)
                            }
                        }
                        if (error is IOException) {
                            presenter.onerror(Network_Message)
                        }

                    }
            }
        }
    }

    /**
     *
     */
    override fun nextpage(nextpage: String) {
        when (head) {
            "Operations " -> {
                Repository.getPaging(nextpage)
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
                                }
                            } else {
                                presenter.onerror(Server_error)
                            }
                        }
                        if (error is IOException) {
                            presenter.onerror(Network_Message)
                        }

                    }
            }
        }
    }

    /**
     *
     */
    override fun perviouspage(perviousPage: String) {
        when (head) {
            "Operations " -> {
                Repository.getPaging(perviousPage)
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
                            if (error is IOException) {
                                presenter.onerror(Network_Message)
                            }
                        }
                    }
            }
        }
    }

}
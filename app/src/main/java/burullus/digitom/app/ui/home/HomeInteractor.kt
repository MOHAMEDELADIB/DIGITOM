package burullus.digitom.app.ui.home

import android.annotation.SuppressLint
import burullus.digitom.app.data.network.api.*
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import burullus.digitom.app.utils.MySharedPreferences
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 *
 */
class HomeInteractor(private val presenter: HomeMvpPresenter) : HomeMvpInteractor {
    /**
     *
     */
    @SuppressLint("CheckResult")
    override fun getnews(url: String) {
        Repository.getArticle(url)
            .subscribe({ result ->
                presenter.onsuccess(result)
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

    /**
     *
     */
    @SuppressLint("CheckResult")
    override fun signout() {
        Repository.logout()
            .subscribe({ (detail) ->
                presenter.loggedout(detail)
                MySharedPreferences.clearToken()
            }, { error ->

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
                            presenter.onerror(
                                mError.detail
                            )
                        }
                    }
                }
                if (error is IOException) {
                    presenter.onerror(error.message ?: return@subscribe)
                } else presenter.onerror(Server_error)

            })
    }

    /**
     *
     */
    override fun getnumber(id: Int) {
        var number = ""
        if (id == 1) number = CCRNumber
        if (id == 2) number = SaetyNumber
        if (id == 3) number = OpertionNumber
        if (id == 4) number = StationNumber
        presenter.getNumber(number)
    }

    /**
     *
     */
    @SuppressLint("CheckResult")
    override fun getuser() {

        Repository.getProfile()
            .subscribe({ userProfile ->
                presenter.getusername(userProfile)
            },
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
                                presenter.onError(mError)
                            } else presenter.onerror(Server_error)
                        }
                        if (error is IOException) {
                            presenter.onerror(Network_Message)
                        }
                    }

                })
    }

}
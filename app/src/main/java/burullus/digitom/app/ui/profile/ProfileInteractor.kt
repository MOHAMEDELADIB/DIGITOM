package burullus.digitom.app.ui.profile

import android.annotation.SuppressLint
import burullus.digitom.app.data.network.api.ApiService
import burullus.digitom.app.data.network.api.Network_Message
import burullus.digitom.app.data.network.api.Server_error
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import burullus.digitom.app.utils.MySharedPreferences
import com.google.gson.GsonBuilder
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.*

/**
 *
 */
class ProfileInteractor(private val presenter : ProfileMvpPresenter) : ProfileMvpInteractor {
    /**
     *
     */
    @SuppressLint("CheckResult")
    override fun loaddata() {
        Repository.getProfile()
            .subscribe({ userProfile ->
                presenter.loadprofile(userProfile)
            },
                { error ->
                    if (error is HttpException) {
                        if (error.code() != 401) {
                            if (error.code() != 500) {
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
    override fun savedata(map : HashMap<String, RequestBody>, body : MultipartBody.Part?) {
        val call : Call<ResponseBody?>? = ApiService.getApiService().UpdateData(map, body)
        call?.enqueue(object : retrofit2.Callback<ResponseBody?> {
            override fun onResponse(
                call : Call<ResponseBody?>,
                response : Response<ResponseBody?>
            ) {
                presenter.saved()
            }

            override fun onFailure(call : Call<ResponseBody?>, t : Throwable) {
                presenter.onerror(Server_error)
            }
        })

    }

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
}
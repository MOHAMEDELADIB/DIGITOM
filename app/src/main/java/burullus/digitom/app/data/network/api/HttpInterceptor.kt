@file:Suppress("NAME_SHADOWING")

package burullus.digitom.app.data.network.api

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Base64
import android.widget.Toast
import burullus.digitom.app.DIGITOM
import burullus.digitom.app.data.network.api.ApiService.Companion.client
import burullus.digitom.app.data.network.model.ErrorModelClass
import burullus.digitom.app.data.repository.Repository
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.ui.splash.SplashActivity.Companion.accesstoken
import burullus.digitom.app.ui.splash.SplashActivity.Companion.refresh_Token
import burullus.digitom.app.utils.MySharedPreferences
import burullus.digitom.app.utils.keystore.Encrypt
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 *
 */
class HttpInterceptor : Interceptor {
    /**
     *
     */
    @Throws(IOException::class)
    override fun intercept(chain : Interceptor.Chain) : Response {
        var request : Request = chain.request()
        //Build new request
        val builder : Request.Builder = request.newBuilder()
        val token : String = accesstoken //save token of this request for future
        setAuthHeader(builder, token) //write current token to request
        request = builder.build() //overwrite old request
        val response : Response =
            chain.proceed(request) //perform request, here original request will be executed
        //if unauthorized
        if (response.code == 401 && accesstoken.isNotEmpty()) {
            //if unauthorized
            accesstoken = ""
            synchronized(client) {
                response.close()
                refreshToken(refresh_Token)
                return chain.proceed(request)
            }
        }
        return response
    }

    private fun setAuthHeader(builder : Request.Builder, token : String?) {
        if (accesstoken != "")
            builder.header("Authorization", String.format("Bearer %s", token))
    }

    @SuppressLint("CheckResult")
    private fun refreshToken(refresh : String) {
        val mEncrypt = Encrypt()
        Repository.getrefresh(refresh)
            ?.subscribe({ (access) ->
                accesstoken = ""
                accesstoken = access
                val encryptedText =
                    mEncrypt.encryptaccesstoken("ALIAS", accesstoken)
                val text = Base64.encodeToString(encryptedText, Base64.NO_WRAP)
                val encryptedText2 =
                    mEncrypt.encryptRefreshToken("ALIAS2", refresh_Token)
                val text2 =
                    Base64.encodeToString(encryptedText2, Base64.NO_WRAP)
                MySharedPreferences.saveToken(text)
                MySharedPreferences.saveRefToken(text2)
                Repository.getrefresh(refresh)?.subscribe()?.dispose()
            }, { error ->
                if (error is HttpException) {
                    val gson = GsonBuilder().create()
                    val mError : ErrorModelClass
                    val responseBody : ResponseBody? = error.response()?.errorBody()
                    mError = gson.fromJson(responseBody?.string(), ErrorModelClass::class.java)
                    if (mError != null) {
                        val msg = mError.detail
                        if (!msg.isNullOrEmpty()) Toast.makeText(
                            DIGITOM.applicationContext(),
                            msg,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    if (error is IOException) {
                        Toast.makeText(
                            DIGITOM.applicationContext(),
                            Network_Message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                loginscreen()
            })
    }


    private fun loginscreen() {
        accesstoken = ""
        refresh_Token = ""
        val intent = Intent(DIGITOM.applicationContext(), Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Intent.FLAG_ACTIVITY_CLEAR_TASK
        DIGITOM.applicationContext().startActivity(intent)
    }


}



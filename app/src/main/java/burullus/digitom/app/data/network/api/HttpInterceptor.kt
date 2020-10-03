@file:Suppress("NAME_SHADOWING")

package burullus.digitom.app.data.network.api

import android.content.Intent
import android.util.Base64
import burullus.digitom.app.DIGITOM
import burullus.digitom.app.data.network.model.RefreshResponse
import burullus.digitom.app.data.repository.Repository
import burullus.digitom.app.ui.login.Login
import burullus.digitom.app.ui.splash.SplashActivity.Companion.accesstoken
import burullus.digitom.app.ui.splash.SplashActivity.Companion.refresh_Token
import burullus.digitom.app.utils.MySharedPreferences
import burullus.digitom.app.utils.keystore.Encrypt
import com.google.gson.Gson
import okhttp3.*


/**
 *
 */
class HttpInterceptor : Interceptor {
    /**
     *
     */
    val mEncrypt = Encrypt()

    /**
     *
     */
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
        if (response.code == 401 && accesstoken.isNotEmpty() && token == accesstoken) {
            //if unauthorized
            response.close()
            val body : RequestBody = FormBody.Builder()
                .add("refresh", refresh_Token)
                .build()

            val refreshTokenRequest =
                request
                    .newBuilder()
                    .post(body)
                    .url(URLA + "user/api/token/refresh/")
                    .build()
            val response2 : Response = chain.proceed(refreshTokenRequest)
            if (response2.code / 100 == 2) {

                val gson = Gson()
                val entity : RefreshResponse =
                    gson.fromJson(response2.body?.string(), RefreshResponse::class.java)
                accesstoken = entity.access
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
                response2.close()
                val builder : Request.Builder = request.newBuilder()
                setAuthHeader(builder, accesstoken)
                request = builder.build()
                return chain.proceed(request)
            } else {
                loginScreen()
            }
        }
        return response
    }

    private fun setAuthHeader(builder : Request.Builder, token : String?) {
        if (accesstoken != "")
            builder.header("Authorization", String.format("Bearer %s", token))
    }


    private fun loginScreen() {
        accesstoken = ""
        refresh_Token = ""
        val intent = Intent(DIGITOM.applicationContext(), Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Intent.FLAG_ACTIVITY_CLEAR_TASK
        DIGITOM.applicationContext().startActivity(intent)
    }



}





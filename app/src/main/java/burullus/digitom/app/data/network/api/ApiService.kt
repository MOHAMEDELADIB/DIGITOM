package burullus.digitom.app.data.network.api

import burullus.digitom.app.data.network.model.*
import burullus.digitom.app.data.network.model.requests.*
import burullus.digitom.app.data.network.model.responses.*
import burullus.digitom.app.ui.splash.SplashActivity.Companion.accesstoken
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {
    @POST("signup/")
    @Headers("Accept: application/json", "No-Authentication: true")
    fun register(@Body signUp: SignUP): Observable<SignUpResponse>

    @Headers("Accept: application/json", "No-Authentication: true")
    @POST("login/")
    fun login(@Body login: Login): Observable<LoginResponse>

    @POST("password/reset/")
    @Headers("Accept: application/json", "No-Authentication: true")
    fun reset(@Body reset: ResetPassword): Observable<ResetResponse>

    @GET
    fun getMEData(@Url url: String): Observable<MechenicalData>

    @GET
    fun getOPData(@Url url: String): Observable<OperationData>

    @GET
    fun getNew(@Url url: String): Observable<List<ArticleData>>

    @GET
    fun gerELData(@Url url: String): Observable<ElectricalData>

    @GET
    fun getICData(@Url url: String): Observable<ICData>

    @POST("password/reset/confirm/")
    @Headers("Accept: application/json", "No-Authentication: true")
    fun forgetAuth(@Body forgetAuth: ForgetAuth): Observable<ForgetAuthResponse>

    @POST("logout/")
    fun signOut(): Observable<SignoutResponse>

    @POST("verify-email/")
    @Headers("Accept: application/json", "No-Authentication: true")
    fun activate(@Body active: ActivateRequest): Observable<ActivateResponse>

    companion object {
        fun getApiService(): ApiService {
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor {
                    val header = it.request().header("No-Authentication")
                    val token = accesstoken
                    val newRequest =
                        if (header == null) {
                            it.request().newBuilder()
                                .addHeader("Authorization", "Bearer $token")
                                .build()
                        } else {
                            it.request()
                        }
                    it.proceed(newRequest)
                }.build()
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(URLA)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}
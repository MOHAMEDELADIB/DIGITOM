@file:Suppress("Annotator")

package burullus.digitom.app.data.network.api

import burullus.digitom.app.data.network.model.*
import burullus.digitom.app.data.network.model.requests.*
import burullus.digitom.app.data.network.model.responses.*
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import burullus.digitom.app.data.network.api.HttpInterceptor as HttpInterceptor1

/**
 * API Interface
 */
interface ApiService {
    /**
     * Register function
     */
    @POST("signup/")
    @Headers("Accept: application/json", "No-Authentication: true")
    fun register(@Body signUp: SignUP): Observable<SignUpResponse>

    /**
     * Login Function
     */
    @Headers("Accept: application/json", "No-Authentication: true")
    @POST("login/")
    fun login(@Body login: Login): Observable<LoginResponse>

    /**
     * Reset Password Function
     */
    @POST("password/reset/")
    @Headers("Accept: application/json", "No-Authentication: true")
    fun reset(@Body reset: ResetPassword): Observable<ResetResponse>

    /**
     * Get Data
     */
    @GET
    fun getMEData(@Url url: String): Observable<MechenicalData>

    /**
     * Get Data
     */
    @GET
    fun getOPData(@Url url: String): Observable<OperationData>

    /**
     *
     */
    @GET
    fun getPaging(@Url url: String): Observable<Paging>

    /**
     * Get News Feed
     */
    @GET
    fun getNew(@Url url: String): Observable<List<ArticleData>>

    /**
     * Get Data
     */
    @GET
    fun gerELData(@Url url: String): Observable<ElectricalData>

    /**
     * Get Data
     */
    @GET
    fun getICData(@Url url: String): Observable<ICData>

    /**
     * Forgetpassword Function
     */
    @POST("password/reset/confirm/")
    @Headers("Accept: application/json", "No-Authentication: true")
    fun forgetAuth(@Body forgetAuth: ForgetAuth): Observable<ForgetAuthResponse>

    /**
     * SignOut Function
     */
    @POST("logout/")
    fun signOut(): Observable<SignoutResponse>

    /**
     * Active account
     */
    @POST("verify-email/")
    @Headers("Accept: application/json", "No-Authentication: true")
    fun activate(@Body active: ActivateRequest): Observable<ActivateResponse>

    /**
     *
     */
    @GET("contacts/apiv1/list/")
    fun getContact(): Observable<List<ContactList>>

    /**
     *
     */
    @GET
    fun userProfile(@Url url: String): Observable<UserProfile>

    /**
     *
     */
    @GET
    fun getResult(@Url url: String): Observable<List<Paging>>

    /**
     *
     */
    @POST("token/refresh/")
    @Headers("Accept: application/json", "No-Authentication: true")
    fun getRefresh(@Body refresh: Refresh): Observable<RefreshResponse>

    companion object {
        /**
         * Retrofit client
         */
        fun getApiService(): ApiService {
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpInterceptor1())
                .build()
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
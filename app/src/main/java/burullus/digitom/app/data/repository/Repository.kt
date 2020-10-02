package burullus.digitom.app.data.repository

import burullus.digitom.app.data.network.api.ApiService
import burullus.digitom.app.data.network.api.userURL
import burullus.digitom.app.data.network.model.*
import burullus.digitom.app.data.network.model.requests.*
import burullus.digitom.app.data.network.model.responses.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import burullus.digitom.app.data.network.model.requests.UserProfile as UserProfile1

/**
 *
 */
object Repository {
    /**
     *
     */
    fun login(username: String, password: String): Observable<LoginResponse> {
        val data = Login(username, password)
        return ApiService.getApiService().login(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun signup(email: String, password1: String, password2: String): Observable<SignUpResponse> {
        val data = SignUP(email, password1, password2)
        return ApiService.getApiService().register(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun restpassword(email: String): Observable<ResetResponse> {
        val data = ResetPassword(email)
        return ApiService.getApiService().reset(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun getArticle(url: String): Observable<List<ArticleData>> {
        return ApiService.getApiService().getNew(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun getOperation(url: String): Observable<OperationData> {
        return ApiService.getApiService().getOPData(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun getPaging(url: String): Observable<Paging> {
        return ApiService.getApiService().getPaging(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun getMechenical(url: String): Observable<MechenicalData> {
        return ApiService.getApiService().getMEData(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun getElectrical(url: String): Observable<ElectricalData> {
        return ApiService.getApiService().gerELData(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun getIC(url: String): Observable<ICData> {
        return ApiService.getApiService().getICData(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun forgetauth(
        token : String,
        new_password1 : String,
        new_password2 : String
    ) : Observable<ForgetAuthResponse> {
        val data = ForgetAuth(token, new_password1, new_password2)
        return ApiService.getApiService().forgetAuth(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun ChangePass(
        new_password1 : String,
        new_password2 : String
    ) : Observable<ForgetAuthResponse> {
        val data = chnagePassword(new_password1, new_password2)
        return ApiService.getApiService().changePassword(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun logout() : Observable<SignoutResponse> {

        return ApiService.getApiService().signOut()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun activate(key: String): Observable<ActivateResponse> {
        val data = ActivateRequest(key)
        return ApiService.getApiService().activate(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * get contact list from back end
     */
    fun getContact(): Observable<List<ContactList>> {
        return ApiService.getApiService().getContact()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * get user Profile
     */
    fun getProfile() : Observable<UserProfile1> {
        return ApiService.getApiService().userProfile(userURL)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */
    fun getrefresh(refresh : String) : Observable<RefreshResponse>? {
        val data = Refresh(refresh)
        return ApiService.getApiService2().getRefresh(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     *
     */

}


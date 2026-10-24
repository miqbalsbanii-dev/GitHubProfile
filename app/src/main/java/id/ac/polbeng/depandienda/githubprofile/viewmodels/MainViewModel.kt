package id.ac.polbeng.depandienda.githubprofile.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.polbeng.depandienda.githubprofile.helpers.Config
import id.ac.polbeng.depandienda.githubprofile.models.GithubUser
import id.ac.polbeng.depandienda.githubprofile.services.GithubUserService
import id.ac.polbeng.depandienda.githubprofile.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _githubUser = MutableLiveData<GithubUser?>()
    val githubUser: LiveData<GithubUser?> get() = _githubUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        searchUser(Config.DEFAULT_USER_LOGIN)
    }

    fun searchUser(query: String) {
        _isLoading.value = true
        val githubUserService = ServiceBuilder.buildService(GithubUserService::class.java)
        val requestCall = githubUserService.loginUser(Config.PERSONAL_ACCESS_TOKEN, query)

        requestCall.enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                _isLoading.postValue(false)
                if (response.isSuccessful) {
                    _githubUser.postValue(response.body())
                } else {
                    _githubUser.postValue(null)
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                _isLoading.postValue(false)
                _githubUser.postValue(null)
            }
        })
    }
}
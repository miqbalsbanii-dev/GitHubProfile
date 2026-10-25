package id.ac.polbeng.depandienda.githubprofile.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.polbeng.depandienda.githubprofile.helpers.Config
import id.ac.polbeng.depandienda.githubprofile.models.UserResponse
import id.ac.polbeng.depandienda.githubprofile.services.GithubUserService
import id.ac.polbeng.depandienda.githubprofile.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _githubUser = MutableLiveData<UserResponse?>()
    val githubUser: LiveData<UserResponse?> get() = _githubUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        searchUser(Config.DEFAULT_USER_LOGIN)
    }

    fun searchUser(query: String) {
        _isLoading.postValue(true)
        val githubUserService = ServiceBuilder.buildService(GithubUserService::class.java)

        // Panggil endpoint tanpa memaksa menyertakan token jika token tidak diset
        val requestCall = githubUserService.getUser(query)

        requestCall.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.postValue(false)
                if (response.isSuccessful && response.body() != null) {
                    _githubUser.postValue(response.body())
                } else {
                    _githubUser.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.postValue(false)
                _githubUser.postValue(null)
            }
        })
    }
}
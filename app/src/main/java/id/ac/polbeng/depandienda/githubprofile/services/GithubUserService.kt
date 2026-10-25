package id.ac.polbeng.depandienda.githubprofile.services

import id.ac.polbeng.depandienda.githubprofile.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubUserService {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<UserResponse>
}
package id.ac.polbeng.depandienda.githubprofile.models

import com.google.gson.annotations.SerializedName

data class GithubUser(
    @SerializedName("login")
    val login: String,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("following")
    val following: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("company")
    val company: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("email")
    val email: String?
) {
    override fun toString(): String {
        return "Name: $name\nBio: $bio\nCompany: $company\nLocation: $location\nEmail: $email\nPublic Repos: $publicRepos\nFollowers: $followers\nFollowing: $following"
    }
}
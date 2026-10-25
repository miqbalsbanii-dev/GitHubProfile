package id.ac.polbeng.depandienda.githubprofile.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("login")
    val login: String = "",
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("html_url")
    val htmlUrl: String = "",
    @SerializedName("blog")
    val blog: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("company")
    val company: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("public_repos")
    val publicRepos: Int = 0,
    @SerializedName("followers")
    val followers: Int = 0,
    @SerializedName("following")
    val following: Int = 0,
    @SerializedName("created_at")
    val createdAt: String = ""
) {
    val displayName: String
        get() = if (!name.isNullOrBlank()) name else login

    override fun toString(): String {
        return "Name: $displayName\n" +
                "Bio: ${bio ?: "Not Available"}\n" +
                "Company: ${company ?: "Not Available"}\n" +
                "Location: ${location ?: "Not Available"}\n" +
                "Blog: ${blog ?: "Not Available"}\n" +
                "Email: ${email ?: "Not Available"}\n" +
                "Public Repos: $publicRepos\n" +
                "Followers: $followers\n" +
                "Following: $following"
    }
}
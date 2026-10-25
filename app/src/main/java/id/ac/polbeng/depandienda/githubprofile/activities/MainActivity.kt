package id.ac.polbeng.depandienda.githubprofile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import id.ac.polbeng.depandienda.githubprofile.GlideApp
import id.ac.polbeng.depandienda.githubprofile.R
import id.ac.polbeng.depandienda.githubprofile.databinding.ActivityMainBinding
import id.ac.polbeng.depandienda.githubprofile.models.UserResponse
import id.ac.polbeng.depandienda.githubprofile.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.githubUser.observe(this) { userResponse ->
            if (userResponse != null) {
                setUserData(userResponse)
            } else {
                binding.tvUser.text = getString(R.string.user_not_found)
                GlideApp.with(this)
                    .load(R.drawable.ic_baseline_broken_image_24)
                    .into(binding.imgUser)
            }
        }

        mainViewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        binding.btnSearch.setOnClickListener {
            val query = binding.etUserLogin.text.toString()
            if (query.isNotEmpty()) {
                mainViewModel.searchUser(query)
            }
        }
    }

    private fun setUserData(userResponse: UserResponse) {
        binding.tvUser.text = userResponse.toString()
        GlideApp.with(this)
            .load(userResponse.avatarUrl)
            .into(binding.imgUser)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
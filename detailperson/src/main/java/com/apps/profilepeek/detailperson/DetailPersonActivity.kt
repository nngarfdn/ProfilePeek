package com.apps.profilepeek.detailperson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.apps.profilepeek.detailperson.databinding.ActivityDetailPersonBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPersonActivity : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivityDetailPersonBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the ViewBinding
        binding = ActivityDetailPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enable edge-to-edge display
        enableEdgeToEdge()
        // Get the id from the Intent
        val personId = intent.getStringExtra("id") ?: ""
        // Set up the view or fetch the person details using the personId
        setupPersonDetails(personId)
    }

    private fun setupPersonDetails(id: String) {
        with(binding){
            detailViewModel.getDetailPerson(id)
            detailViewModel.detail.observe(this@DetailPersonActivity) {
                it?.let {
                    nameTextView.text = it.name
                    cityTextView.text = it.city
                    Glide.with(this@DetailPersonActivity)
                        .load(it.avatar)
                        .into(avatarImageView)
                }
            }
        }
    }
}

package com.apps.profilepeek.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.profilepeek.adapter.PersonAdapter
import com.apps.profilepeek.core.data.Resource
import com.apps.profilepeek.databinding.ActivityMainBinding
import com.apps.profilepeek.detailperson.DetailPersonActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var personAdapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the adapter and RecyclerView
        personAdapter = PersonAdapter { person ->
            val intent = Intent(this, DetailPersonActivity::class.java)
            intent.putExtra("id", person.id)
            startActivity(intent)
        }

        binding.recyclerView.apply {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        // Set up SwipeRefreshLayout listener
        binding.swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.getPersonList() // Refresh data
        }

        // Observe the ViewModel's LiveData for person list
        mainViewModel.getPersonList()
        mainViewModel.personList.observe(this) { resource ->
            when (resource) {
                is Resource.Success -> {
                    // Hide progress bar and swipe refresh loader
                    binding.progressBar.visibility = View.GONE
                    binding.swipeRefreshLayout.isRefreshing = false

                    val data = resource.data ?: emptyList()
                    personAdapter.submitList(data)

                    // Hide error message and show RecyclerView
                    binding.errorTextView.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    // Hide progress bar and swipe refresh loader
                    binding.progressBar.visibility = View.GONE
                    binding.swipeRefreshLayout.isRefreshing = false

                    val message = resource.message ?: "Unknown error"
                    Log.e("Person", message)

                    // Show error message and hide RecyclerView
                    binding.errorTextView.text = message
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                is Resource.Loading -> {
                    // Show the progress bar and hide the RecyclerView
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorTextView.visibility = View.GONE
                }
            }
        }
    }
}


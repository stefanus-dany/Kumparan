package com.project.kumparan.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.kumparan.adapter.MainAdapter
import com.project.kumparan.databinding.ActivityMainBinding
import com.project.kumparan.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]

        getAllPosts()

    }

    private fun getAllPosts() {
        //get all posts
        viewModel.getAllPosts().observe(this) { data ->
            //get user's post
            viewModel.getAllUsers().observe(this) { user ->
                adapter = MainAdapter(this@MainActivity)
                with(binding.rvPosts) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = this@MainActivity.adapter
                }
                adapter.setData(data, user)
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
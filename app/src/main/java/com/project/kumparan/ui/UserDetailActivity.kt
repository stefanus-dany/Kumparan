package com.project.kumparan.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.kumparan.Constants
import com.project.kumparan.R
import com.project.kumparan.adapter.UserDetailAdapter
import com.project.kumparan.databinding.ActivityUserDetailBinding
import com.project.kumparan.model.UserModel
import com.project.kumparan.viewmodel.UserDetailViewModel

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var adapter: UserDetailAdapter
    private lateinit var viewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "User Detail Page"

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[UserDetailViewModel::class.java]

        val user = intent.getParcelableExtra<UserModel>(Constants.EXTRA_USER)
        with(binding) {
            if (user != null) {
                with(user) {
                    userDetailName.text = name
                    userDetailEmail.text = email
                    userDetailAddress.text = resources.getString(
                        R.string.address,
                        address.street,
                        address.suite,
                        address.city,
                        address.zipcode
                    )
                    userDetailCompany.text = company.name
                }
                //get user albums
                getAlbums(user.id)
            }
        }
    }

    private fun getAlbums(idUser: Int) {
        viewModel.getUserAlbums(idUser).observe(this) {
            adapter = UserDetailAdapter(this@UserDetailActivity)
            with(binding.rvAlbums) {
                layoutManager = LinearLayoutManager(context)
                adapter = this@UserDetailActivity.adapter
            }
            adapter.setData(it)
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}
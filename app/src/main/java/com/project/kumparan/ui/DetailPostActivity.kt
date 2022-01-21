package com.project.kumparan.ui

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.kumparan.Constants
import com.project.kumparan.adapter.DetailPostAdapter
import com.project.kumparan.databinding.ActivityDetailPostBinding
import com.project.kumparan.model.UserModel
import com.project.kumparan.viewmodel.DetailPostViewModel

class DetailPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding
    private lateinit var adapter: DetailPostAdapter
    private lateinit var viewModel: DetailPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Post Page"

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailPostViewModel::class.java]

        val getPostId = intent.getIntExtra(Constants.EXTRA_POST_ID, 0)
        val getPostUserName = intent.getStringExtra(Constants.EXTRA_POST_USER_NAME)
        val getPostUserTitle = intent.getStringExtra(Constants.EXTRA_POST_TITLE)
        val getPostUserBody = intent.getStringExtra(Constants.EXTRA_POST_BODY)
        val getUser = intent.getParcelableExtra<UserModel>(Constants.EXTRA_USER)

        with(binding) {
            detailPostUsername.text = getPostUserName
            detailPostTitle.text = getPostUserTitle
            detailPostBody.text = getPostUserBody

            detailPostUsername.setOnClickListener {
                val intent = Intent(this@DetailPostActivity, UserDetailActivity::class.java)
                intent.putExtra(Constants.EXTRA_USER, getUser)
                startActivity(intent)
            }
        }
        //get all post comments
        getComments(getPostId)
    }

    private fun getComments(idPost: Int) {
        viewModel.getAllComments(idPost).observe(this) {
            adapter = DetailPostAdapter()
            with(binding.rvComment) {
                layoutManager = LinearLayoutManager(context)
                adapter = this@DetailPostActivity.adapter
            }
            adapter.setData(it)
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> finish()
        }
        return true
    }

}
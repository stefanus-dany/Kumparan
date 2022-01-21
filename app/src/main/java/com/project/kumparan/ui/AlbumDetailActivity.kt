package com.project.kumparan.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.kumparan.Constants
import com.project.kumparan.R
import com.project.kumparan.adapter.AlbumDetailAdapter
import com.project.kumparan.databinding.ActivityAlbumDetailBinding
import com.project.kumparan.model.AlbumModel
import com.project.kumparan.viewmodel.PhotoDetailViewModel

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumDetailBinding
    private lateinit var adapter: AlbumDetailAdapter
    private lateinit var viewModel: PhotoDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Album Page"

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[PhotoDetailViewModel::class.java]

        val getAlbum = intent.getParcelableExtra<AlbumModel>(Constants.EXTRA_ALBUM)

        with(binding) {
            if (getAlbum != null) {
                albumName.text = resources.getString(R.string.album_name, getAlbum.title)
                //get all post album's photos
                getAlbumPhotos(getAlbum.id)
            }
        }
    }

    private fun getAlbumPhotos(idAlbum: Int) {
        viewModel.getAllPhotos(idAlbum).observe(this) {
            adapter = AlbumDetailAdapter(this)
            with(binding.rvPhotos) {
                layoutManager = StaggeredGridLayoutManager(
                    2, // span count
                    StaggeredGridLayoutManager.VERTICAL // orientation
                )
                adapter = this@AlbumDetailActivity.adapter
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
package com.project.kumparan.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.ortiz.touchview.TouchImageView
import com.project.kumparan.Constants
import com.project.kumparan.R
import com.project.kumparan.databinding.ActivityPhotoDetailBinding
import com.project.kumparan.model.PhotoModel

class PhotoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Photo Page"
        val getPhoto = intent.getParcelableExtra<PhotoModel>(Constants.EXTRA_PHOTO)

        if (getPhoto != null) {
            binding.photoDetailName.text = getPhoto.title
            binding.tivPhotoDetail.loadImage(getPhoto.url)
        }

    }

    private fun TouchImageView.loadImage(url: String?) {
        val show = GlideUrl(
            url, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )
        Glide.with(this.context)
            .load(show)
            .apply(RequestOptions().override(100, 100))
            .centerCrop()
            .placeholder(R.drawable.ic_loading)
            .into(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}
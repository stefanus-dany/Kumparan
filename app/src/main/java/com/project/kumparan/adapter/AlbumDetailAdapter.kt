package com.project.kumparan.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.kumparan.R
import com.project.kumparan.databinding.ListPhotoBinding
import com.project.kumparan.model.PhotoModel
import com.bumptech.glide.load.model.LazyHeaders

import com.bumptech.glide.load.model.GlideUrl
import com.project.kumparan.Constants
import com.project.kumparan.ui.PhotoDetailActivity


class AlbumDetailAdapter(private val context: Context) :
    RecyclerView.Adapter<AlbumDetailAdapter.ViewHolder>() {
    private var data = ArrayList<PhotoModel>()

    fun setData(data: List<PhotoModel>?) {
        if (data == null) return
        this.data.clear()
        this.data.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item =
            ListPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data, context)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(
        private val binding: ListPhotoBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PhotoModel, context: Context) {
            with(binding) {
                ivPhoto.loadImage(data.thumbnailUrl)
            }
            itemView.setOnClickListener {
                val intent = Intent(context, PhotoDetailActivity::class.java)
                intent.putExtra(Constants.EXTRA_PHOTO, data)
                context.startActivity(intent)
            }
        }

        private fun ImageView.loadImage(url: String?) {
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
    }
}
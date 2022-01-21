package com.project.kumparan.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.kumparan.Constants
import com.project.kumparan.databinding.ListAlbumBinding
import com.project.kumparan.model.AlbumModel
import com.project.kumparan.ui.AlbumDetailActivity

class UserDetailAdapter(private val context: Context) :
    RecyclerView.Adapter<UserDetailAdapter.ViewHolder>() {
    private var data = ArrayList<AlbumModel>()

    fun setData(data: List<AlbumModel>?) {
        if (data == null) return
        this.data.clear()
        this.data.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item =
            ListAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data, context)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(
        private val binding: ListAlbumBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: AlbumModel, context: Context) {
            with(binding) {
                albumName.text = data.title
            }
            itemView.setOnClickListener {
                val intent = Intent(context, AlbumDetailActivity::class.java)
                intent.putExtra(Constants.EXTRA_ALBUM, data)
                context.startActivity(intent)
            }
        }
    }
}
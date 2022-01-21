package com.project.kumparan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.kumparan.databinding.ListCommentBinding
import com.project.kumparan.model.PostCommentModel

class DetailPostAdapter :
    RecyclerView.Adapter<DetailPostAdapter.ViewHolder>() {
    private var data = ArrayList<PostCommentModel>()

    fun setData(data: List<PostCommentModel>?) {
        if (data == null) return
        this.data.clear()
        this.data.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item =
            ListCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(
        private val binding: ListCommentBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PostCommentModel) {
            with(binding) {
                commentAuthorName.text = data.name
                commentBody.text = data.body
            }
        }
    }
}
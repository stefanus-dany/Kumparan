package com.project.kumparan.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.kumparan.Constants
import com.project.kumparan.databinding.ListPostBinding
import com.project.kumparan.model.PostModel
import com.project.kumparan.model.UserModel
import com.project.kumparan.ui.DetailPostActivity

class MainAdapter(private val context: Context) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var data = ArrayList<PostModel>()
    private var user = ArrayList<UserModel>()

    fun setData(data: List<PostModel>?, user: List<UserModel>?) {
        if (data == null) return
        this.data.clear()
        this.data.addAll(data)
        if (user == null) return
        this.user.clear()
        this.user.addAll(user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item =
            ListPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data, user)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(
        private val binding: ListPostBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PostModel, user: ArrayList<UserModel>) {
            val userId = data.userId
            var getName = ""
            var getCompany = ""
            lateinit var getUserModel: UserModel
            for (i in user.indices){
                if (user[i].id == userId){
                    getName = user[i].name
                    getCompany = user[i].company.name
                    getUserModel = user[i]
                }

            }
            with(binding) {
                userName.text = getName
                userCompanyName.text = getCompany
                postTitle.text = data.title
                postBody.text = data.body
            }

            itemView.setOnClickListener {
                val intent = Intent(context, DetailPostActivity::class.java)
                intent.putExtra(Constants.EXTRA_POST_ID, data.id)
                intent.putExtra(Constants.EXTRA_POST_USER_NAME, getName)
                intent.putExtra(Constants.EXTRA_POST_TITLE, data.title)
                intent.putExtra(Constants.EXTRA_POST_BODY, data.body)
                intent.putExtra(Constants.EXTRA_POST_BODY, data.body)
                intent.putExtra(Constants.EXTRA_USER, getUserModel)
                context.startActivity(intent)
            }
        }
    }
}
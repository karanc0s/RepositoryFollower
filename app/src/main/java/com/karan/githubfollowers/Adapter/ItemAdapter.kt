package com.karan.githubfollowers.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.karan.githubfollowers.Interfaces.EventHandler
import com.karan.githubfollowers.Model.RepoItem
import com.karan.githubfollowers.R

class ItemAdapter(
    private val applicationContext: Context,
    private var list: ArrayList<RepoItem>,
    private val listener : EventHandler ) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvRepoName : AppCompatTextView = itemView.findViewById(R.id.tv_repo_name)
        val tvOwnerName : AppCompatTextView = itemView.findViewById(R.id.tv_owner_name)
        val tvRepoDescription : AppCompatTextView = itemView.findViewById(R.id.tv_repo_description)
        val ibShare : AppCompatImageButton = itemView.findViewById(R.id.ib_share)

        val ivOwnerIcon : AppCompatImageView = itemView.findViewById(R.id.iv_profile_icon)

        init {
            itemView.setOnClickListener{
                listener.onClickEvent(adapterPosition)
            }
            ibShare.setOnClickListener{
                listener.onShare(adapterPosition)
            }

        }

    }


    fun setData(repoItems: ArrayList<RepoItem>) {
        this.list = repoItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(applicationContext).inflate(R.layout.single_item , parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = list[position]
        holder.tvOwnerName.text = repo.repoOwner.toString()
        holder.tvRepoName.text = repo.repoName.toString()
        holder.tvRepoDescription.text = repo.repoDescription.toString()
    }

}
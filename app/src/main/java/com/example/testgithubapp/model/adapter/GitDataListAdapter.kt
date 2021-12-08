package com.example.testgithubapp.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testgithubapp.R
import com.example.testgithubapp.databinding.RowItemBinding
import com.example.testgithubapp.model.GitHubRepositoryItem

class GitDataListAdapter : RecyclerView.Adapter<GitDataListAdapter.ViewHolder>() {

     val gitLists = mutableListOf<GitHubRepositoryItem>()

    fun updateList(gitList: List<GitHubRepositoryItem>){
        gitLists.clear()
        gitLists.addAll(gitList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageView
        val name = binding.name
        val repositoryName = binding.repositoryName
        val totalStars = binding.totalStars
        val selectedImage = binding.selectedImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = gitLists[position]

        if(data.checked)
            holder.selectedImage.setImageResource(R.drawable.ic_baseline_check_circle)
        else holder.selectedImage.setImageResource(0)


        Glide.with(holder.itemView).load(data.builtBy?.get(0)?.avatar).into(holder.image)
        holder.name.text = "User name : " + data.username
        holder.repositoryName.text = "Repo name : " + data.repositoryName
        holder.totalStars.text = "Stars today : " + data.starsSince.toString()

        holder.itemView.setOnClickListener {
            gitLists.forEach { it.checked = false }
            data.checked = true
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return gitLists.size
    }

}
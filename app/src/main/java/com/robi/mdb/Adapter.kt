package com.robi.mdb

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robi.mdb.databinding.ItemCardContentBinding
import com.robi.mdb.models.Result

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {
    var list = listOf<Result?>()
    var actionListener: OnActionListener? = null

    interface OnActionListener {
        fun onAction(result: Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCardContentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val m = list[position]
        if (m==null) {
            holder.bindItem.cardItem.visibility = View.GONE
            holder.bindItem.cardShimmer.visibility = View.VISIBLE
            holder.bindItem.shimmer.startShimmer()
        } else {
            holder.bindItem.shimmer.stopShimmer()
            holder.bindItem.cardShimmer.visibility = View.GONE
            holder.bindItem.cardItem.visibility = View.VISIBLE
            Glide.with(holder.itemView.context)
                .asDrawable()
                .load("https://image.tmdb.org/t/p/w185${m.posterPath}")
                .into(holder.bindItem.ivCover)
            holder.bindItem.tvTitle.text = m.title
            holder.bindItem.tvDescription.text = m.overview
            holder.bindItem.tvDate.text = m.releaseDate
            holder.itemView.setOnClickListener {
                actionListener?.onAction(m)
            }
        }
    }

    class ViewHolder(val bindItem: ItemCardContentBinding): RecyclerView.ViewHolder(bindItem.root)
}
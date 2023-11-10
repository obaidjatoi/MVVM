package com.obaid.mvvm.views.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import coil.load
import com.obaid.mvvm.R
import com.obaid.mvvm.data.models.responses.Hit
import com.obaid.mvvm.databinding.ItemHomeListingBinding
import com.obaid.mvvm.utils.RecyclerItemClickListener
import com.obaid.mvvm.views.adapters.diffutils.HomeListingDiffUtilCallback

class ListingAdapter(private var clickListener: RecyclerItemClickListener? = null) :
    RecyclerView.Adapter<ListingAdapter.ListingViewHolder>() {

    private lateinit var binding: ItemHomeListingBinding
    private var oldList = emptyList<Hit>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListingAdapter.ListingViewHolder {
        binding = ItemHomeListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ListingViewHolder()
        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != NO_POSITION) {
                clickListener?.onClick(holder.adapterPosition)
            }
        }
        context = parent.context
        return holder
    }

    override fun onBindViewHolder(p0: ListingAdapter.ListingViewHolder, p1: Int) {
        p0.setData(oldList[p1])
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    inner class ListingViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: Hit) {
            binding.apply {
                binding.title.text = data.user
                    ?: if (::context.isInitialized) context.getString(R.string.no_data) else "--"
                binding.image.load(data.previewURL ?: "")
            }
        }
    }

    fun setData(newList: List<Hit>) {
        val diffUtil = HomeListingDiffUtilCallback(oldList, newList)
        val diffRes = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffRes.dispatchUpdatesTo(this)
    }
}
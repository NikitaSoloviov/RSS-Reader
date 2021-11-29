package com.dts.retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dts.retrofit.databinding.ViewItemNewsBinding
import com.dts.retrofit.presentation.model.NewsModel
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val items = mutableListOf<NewsModel>()  //Create list of items type NewsModel
    private val date = SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US) //date format   5/20/2010 10:30
    private var itemClickListener: ((NewsModel) -> Unit)? = null

    fun setOnClickListener(listener: ((NewsModel) -> Unit)){
        itemClickListener = listener
    }

    fun addNews(items: List<NewsModel>){ //  clear old list, add items to new list
        val diff = DiffUtil.calculateDiff(DiffUtilUpdate(this.items, items))
        this.items.clear()
        this.items.addAll(items)
        diff.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ViewItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: NewsModel, onItemClickListener: ((NewsModel) -> Unit)?) {
            binding.tvDate.text = date.format(news.createAt)
            binding.tvAuthor.text = news.author
            binding.tvNewsMsg.text = news.title
            binding.root.setOnClickListener { onItemClickListener?.invoke(news) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(binding = ViewItemNewsBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], itemClickListener )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class DiffUtilUpdate(private val oldList: List<NewsModel>, private val newList: List<NewsModel>):DiffUtil.Callback(){

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
           return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}

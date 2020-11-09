package com.example.redcarpet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news.view.*
import com.example.redcarpet.Article1
import java.text.ParseException
import java.util.*

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var news:List<Article1> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
       return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = news[position]
        holder.bind(article)
    }
    fun setNews(news:List<Article1>){
        this.news = news
        notifyDataSetChanged()

    }
    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(article: Article1){
            itemView.apply {
                GlideApp.with(ImageView)
                    .load(article.urlToImage)
                    .into(ImageView)

                itemView.Title.text = article.title

                if (article.publishedAt != null) {
                    var date: Date? = null
                    try {
                        date = ISO8601Parse.parse(article.publishedAt)
                    } catch (ex: ParseException) {
                        ex.printStackTrace()
                    }
                    if (date != null) {
                        itemView.time1.setReferenceTime(date.time)
                    }
                }
                if(article.author != null) {
                    itemView.Author1.text = article.author
                }
                else if(article.source1.name != null){
                    itemView.Author1.text = article.source1.name
                }
                else{
                    itemView.Author1.text = "unknown"
                }
                itemView.card.setOnClickListener {
                    findNavController().navigate(LandingFragmentDirections.actionLandingFragmentToNewsDetailFragment(article.title!!,
                        article.author!!,article.content1!!,article.source1.name!!,article.urlToImage!!,article.url!!,article.publishedAt!!,
                        article.description!!,"unknown"))
                }
            }
        }


        companion object{
            fun from(parent: ViewGroup):NewsViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_news,parent,false)
                return NewsViewHolder(itemView)
            }
        }
    }
}
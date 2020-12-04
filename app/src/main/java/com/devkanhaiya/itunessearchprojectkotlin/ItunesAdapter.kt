package com.devkanhaiya.itunessearchprojectkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devkanhaiya.itunessearchprojectkotlin.DataBase.OffData
import java.util.ArrayList

class ItunesAdapter: RecyclerView.Adapter<ItunesAdapter.ItuneViewHolder>() {

    private val allDatas = ArrayList<OffData>()

    inner class ItuneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.track_name)
        val trackImage: ImageView = itemView.findViewById(R.id.track_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItuneViewHolder {
        val viewHolder = ItuneViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_itunes,parent,false))
        return viewHolder
    }

    fun updateList(newList: List<OffData>) {
        allDatas.clear()
        allDatas.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ItuneViewHolder, position: Int) {
        val dataCurrent = allDatas[position]
        holder.titleText.text = dataCurrent.nameTrack

        Glide.with(holder.trackImage.context)
            .load(dataCurrent.image)
            .into(holder.trackImage)

    }

    override fun getItemCount(): Int {
        return allDatas.size
    }
}



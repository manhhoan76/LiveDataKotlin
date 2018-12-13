package com.lapurema.manhhoan.project1

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.lapurema.manhhoan.project1.Modal.ItemTag

class ItemTagAdapter(var arrTag: ArrayList<ItemTag>) :
    RecyclerView.Adapter<ItemTagAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.item_tag, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrTag.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val itemTag: ItemTag = arrTag[p1]
        p0?.buttonTag?.text = itemTag.tagName
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var buttonTag: Button

        init {
            buttonTag = row.findViewById(R.id.buttonTag) as Button
        }
    }

}
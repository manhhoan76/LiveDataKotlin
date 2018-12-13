package com.lapurema.manhhoan.project1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.lapurema.manhhoan.project1.Modal.ItemGrid

class ItemGridAdapter(var context: Context, var arrItem: ArrayList<ItemGrid>) : BaseAdapter() {
    class ViewHolder(row: View) {
        var textViewItem: TextView
        var imageItem: ImageView

        init {
            textViewItem = row.findViewById(R.id.txtItemGrid) as TextView
            imageItem = row.findViewById(R.id.imgItemGrid) as ImageView
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if (convertView == null) {
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_grid, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder

        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        var itemgrid: ItemGrid = getItem(position) as ItemGrid
        viewHolder.textViewItem.text = itemgrid.ten
        viewHolder.imageItem.setImageResource(itemgrid.hinhanh)
        return view as View
    }

    override fun getItem(position: Int): Any {
        return arrItem.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrItem.size
    }
}
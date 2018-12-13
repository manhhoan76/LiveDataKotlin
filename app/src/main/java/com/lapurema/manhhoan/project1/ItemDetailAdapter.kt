package com.lapurema.manhhoan.project1

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lapurema.manhhoan.project1.Modal.ItemDetail

class ItemDetailAdapter(var arrDetail: ArrayList<ItemDetail>) :
    RecyclerView.Adapter<ItemDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.item_detail, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrDetail.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val itemDetail: ItemDetail = arrDetail[p1]
        p0?.imagHinh?.setImageResource(itemDetail.hinhanh)
        p0?.txtName?.text = itemDetail.ten
        p0?.txtAddress?.text = itemDetail.diachi
        p0?.txtLike?.text = itemDetail.like.toString()+" like"
        p0?.txtComment?.text = itemDetail.binhluan.toString()+"bình luận"
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var imagHinh: ImageView = row.findViewById(R.id.imgItemDetail) as ImageView
        var txtName: TextView
        var txtAddress: TextView
        var txtLike: TextView
        var txtComment: TextView

        init {
            txtName = row.findViewById(R.id.txtItemName) as TextView
            txtAddress = row.findViewById(R.id.txtItemAddress) as TextView
            txtLike = row.findViewById(R.id.textViewLike) as TextView
            txtComment = row.findViewById(R.id.textViewComment) as TextView

        }
    }

}
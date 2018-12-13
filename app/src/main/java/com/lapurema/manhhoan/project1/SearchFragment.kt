package com.lapurema.manhhoan.project1

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import com.lapurema.manhhoan.project1.Modal.ItemGrid
import com.lapurema.manhhoan.project1.Modal.ItemTag
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainActivity = activity as MainActivity?
        if (mainActivity != null) {
            mainActivity.showMessage(0)
            mainActivity.switchIconNavigation(2)
        }
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var arrItemGrid: ArrayList<ItemGrid> = ArrayList()
        arrItemGrid.add(ItemGrid("Khuyến mãi", R.mipmap.sale))
        arrItemGrid.add(ItemGrid("Khám bệnh", R.mipmap.love))
        arrItemGrid.add(ItemGrid("Khu vui chơi", R.mipmap.smart1))
        arrItemGrid.add(ItemGrid("Sự kiện", R.mipmap.calendar_pink_2))
        arrItemGrid.add(ItemGrid("Mua sắm", R.mipmap.bag))
        arrItemGrid.add(ItemGrid("Năng khiếu", R.mipmap.talent))
        arrItemGrid.add(ItemGrid("Ngoại ngữ", R.mipmap.smart))
        arrItemGrid.add(ItemGrid("Nhà trẻ", R.mipmap.shop))
        arrItemGrid.add(ItemGrid("Sinh nhật", R.mipmap.cake_birdthday))
        grv_Search.adapter = ItemGridAdapter(this.requireContext(), arrItemGrid)
        var fagmentManager: FragmentManager
        var transaction = fragmentManager!!.beginTransaction()
        var detailFragment = DetailFragment()
        grv_Search.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            transaction
                .add(R.id.content, detailFragment, detailFragment.javaClass.simpleName)
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .addToBackStack(detailFragment.javaClass.simpleName)
                .commit()
            Log.d("AAA", detailFragment.javaClass.simpleName)
        }

        var arrItemTag: ArrayList<ItemTag> = ArrayList()
        arrItemTag.add(ItemTag("Khu vui chơi"))
        arrItemTag.add(ItemTag("Khuyến mãi"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        arrItemTag.add(ItemTag("Nhà trẻ"))
        recyclerViewTag.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayout.HORIZONTAL, false)
        recyclerViewTag.adapter = ItemTagAdapter(arrItemTag)
    }
}
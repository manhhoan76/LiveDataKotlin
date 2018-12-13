package com.lapurema.manhhoan.project1

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toolbar
import com.lapurema.manhhoan.project1.Modal.ItemDetail
import com.lapurema.manhhoan.project1.ViewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this!!.activity!!).get(HomeViewModel::class.java)
        homeViewModel.getCategory().observe(this, Observer { t ->
            txtDetail!!.text = t.toString()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainActivity = activity as MainActivity?
        if (mainActivity != null) {
            mainActivity.showMessage(1)
        }
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var arrItemDetail: ArrayList<ItemDetail> = ArrayList()
        arrItemDetail.add(
            ItemDetail(
                R.drawable.gai4,
                "Công viên đầm sen1",
                "123, Quận Hải Châu, Đà Nẵng",
                123,
                113,
                235
            )
        )
        arrItemDetail.add(
            ItemDetail(
                R.drawable.gai1,
                "Công viên đầm sen2",
                "123, Quận Hải Châu, Đà Nẵng",
                123,
                113,
                13
            )
        )
        arrItemDetail.add(
            ItemDetail(
                R.drawable.gai2,
                "Công viên đầm sen3",
                "123, Quận Hải Châu, Đà Nẵng",
                123,
                113,
                823
            )
        )
        arrItemDetail.add(
            ItemDetail(
                R.drawable.gai3,
                "Công viên đầm sen4",
                "123, Quận Hải Châu, Đà Nẵng",
                123,
                113,
                129
            )
        )
        arrItemDetail.add(
            ItemDetail(
                R.drawable.gai4,
                "Công viên đầm sen5",
                "123, Quận Hải Châu, Đà Nẵng",
                123,
                113,
                83
            )
        )
        arrItemDetail.add(
            ItemDetail(
                R.drawable.gai1,
                "Công viên đầm sen6",
                "123, Quận Hải Châu, Đà Nẵng",
                123,
                113,
                983
            )
        )
        rcvItemDetail.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayout.VERTICAL, false)
        rcvItemDetail.adapter = ItemDetailAdapter(arrItemDetail)
        activity!!.setActionBar(Toolbar(requireContext()))
    }

    private fun showActionBar() {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false)

        }
    }

    private fun showDrawerButton() {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false)
            val toggle = ActionBarDrawerToggle(
                this.requireContext() as Activity?,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            drawer_layout.addDrawerListener(toggle)
            toggle.syncState()
        }
    }
}

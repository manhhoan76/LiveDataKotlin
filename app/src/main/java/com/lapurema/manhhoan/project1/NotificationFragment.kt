package com.lapurema.manhhoan.project1

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NotificationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainActivity = activity as MainActivity?
        if (mainActivity != null) {
            mainActivity.showMessage(0)
            mainActivity.switchIconNavigation(4)
        }
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }
}
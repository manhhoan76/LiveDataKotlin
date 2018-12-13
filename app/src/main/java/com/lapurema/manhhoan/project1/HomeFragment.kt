package com.lapurema.manhhoan.project1

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lapurema.manhhoan.project1.Common.WikiApi
import com.lapurema.manhhoan.project1.Modal.ObjLogin
import com.lapurema.manhhoan.project1.ViewModel.HomeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var disposable: Disposable? = null

    private val wikiApi by lazy {
        WikiApi.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this.activity!!).get(HomeViewModel::class.java)
        displayText()
    }

    private fun beginSearch(auth: String) {
        disposable = wikiApi.loginWallet(auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it -> handleResponse(it) },
                { error -> handleError(error) }
            )
    }

    @SuppressLint("SetTextI18n")
    private fun handleResponse(res: ObjLogin.ResultLogin) {
        homeViewModel.id.value = res.email
        Toast.makeText(requireContext(), "Đã lấy được dữ liệu", Toast.LENGTH_SHORT).show()
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun displayText() {
        homeViewModel.getCategory().observe(this, Observer { t ->
            txtHome!!.text = t.toString()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val mainActivity = activity as MainActivity?

        if (mainActivity != null) {
            mainActivity.showMessage(0)
            mainActivity.switchIconNavigation(1)
        }
        displayText()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSearchAPI.setOnClickListener {
            var user = "9a73401c98bfd92ca2232e497f89817d:nguyen1"
            var encode: String = Base64.encodeToString(user.toByteArray(), Base64.DEFAULT)
            beginSearch("OWE3MzQwMWM5OGJmZDkyY2EyMjMyZTQ5N2Y4OTgxN2Q6bmd1eWVuMQ==")
            displayText()
        }
    }

}

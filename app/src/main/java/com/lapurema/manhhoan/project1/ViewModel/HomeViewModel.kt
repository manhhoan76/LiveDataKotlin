package com.lapurema.manhhoan.project1.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
     var id = MutableLiveData<String>()
     fun getCategory(): LiveData<String> {
        return id
    }
    init {
        id.value = "Chào bạn, hãy bấm nút đi nào"
    }
}
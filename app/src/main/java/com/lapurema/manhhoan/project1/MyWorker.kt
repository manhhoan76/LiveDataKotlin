package com.lapurema.manhhoan.project1

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    companion object {
        private const val TAG = "MyWorker"
    }

    override fun doWork(): Result =
        try {
            val a = 1
            val b = 1
            Log.i(TAG, "${a.plus(b)}")
            Result.SUCCESS
        } catch (exception: Exception) {
            Log.i(TAG, "Fail")
            Result.FAILURE
        }

}
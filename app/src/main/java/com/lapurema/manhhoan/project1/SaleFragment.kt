package com.lapurema.manhhoan.project1

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.work.*
import kotlinx.android.synthetic.main.fragment_sale.*
import java.util.*
import java.util.concurrent.TimeUnit

class SaleFragment : Fragment() {
    private lateinit var workManager: WorkManager
    private lateinit var constraints: Constraints
    private lateinit var workId: UUID
    private lateinit var workIdPeriodic: UUID
    private lateinit var compressionWork: OneTimeWorkRequest
    private lateinit var compressionWorkPeriodic: PeriodicWorkRequest

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainActivity = activity as MainActivity?
        if (mainActivity != null) {
            mainActivity.showMessage(0)
            mainActivity.switchIconNavigation(3)
        }
        workManager = WorkManager.getInstance()

        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDoWork.setOnClickListener {
            doWorker()
        }
        btnDoWithConstrain.setOnClickListener {
            doWorkerWithConstraint()

        }
        btnCancelWork.setOnClickListener {
            cancelWork()
        }

        if (compressionWorkPeriodic.id != null) {
            workManager.getWorkInfoByIdLiveData(compressionWorkPeriodic.id)
                .observe(viewLifecycleOwner, android.arch.lifecycle.Observer {
                    val data = it!!.outputData
                    val detail = data.getString("dataInput")
                    Toast.makeText(requireContext(), detail, Toast.LENGTH_SHORT).show()
                    if (it.state.isFinished) {
                        Toast.makeText(requireContext(), "Finish", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }

    private fun doWorker() {
        compressionWork = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .build()
        workManager.enqueue(compressionWork)
    }

    private fun doWorkerWithConstraint() {
        val inputData = Data.Builder()
            .putString("dataInput", "data gửi qua worker")
            .build()
        constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        //Work với chu kỳ 3 giây
        compressionWorkPeriodic = PeriodicWorkRequest.Builder(MyWorker::class.java, 3, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()
        //Work xảy ra với 1 lần
//        compressionWork = OneTimeWorkRequest.Builder(MyWorker::class.java)
//            .setConstraints(constraints)
//            .build()

        workManager.enqueue(compressionWorkPeriodic)
    }

    private fun cancelWork() {
        workId = compressionWork.id
        workManager.cancelWorkById(workId)
        workIdPeriodic = compressionWorkPeriodic.id
        workManager.cancelWorkById(workIdPeriodic)
    }

}

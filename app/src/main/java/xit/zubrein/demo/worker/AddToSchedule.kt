package xit.zubrein.demo.worker

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class AddToSchedule @Inject constructor(activity: Activity){

    var workManager: WorkManager = WorkManager.getInstance(activity)
    lateinit var workRequest: WorkRequest

    fun addToQueue(dateTimeInMillis:Long, packageName:String){

        val currentTime = System.currentTimeMillis()
        val scheduleTime = dateTimeInMillis - currentTime

        val workRequest = OneTimeWorkRequest.Builder(AppScheduler::class.java)
        workRequest.setInitialDelay(scheduleTime, TimeUnit.MILLISECONDS)
        workRequest.addTag("$dateTimeInMillis")
        val data = Data.Builder()
        data.putString("package", packageName)
        workRequest.setInputData(data.build())


        workManager.enqueue(workRequest.build())
        Log.d("SchedulerClass", "addSchedule: $dateTimeInMillis")
    }

    fun cancelSchedule(tag:String){
        Log.d("SchedulerClass", "cancelSchedule: $tag")
        workManager.cancelAllWorkByTag(tag)
    }



}
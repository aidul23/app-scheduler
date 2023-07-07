package xit.zubrein.demo.worker

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class AppScheduler(context: Context, workerParams: WorkerParameters,) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        val packageName =  inputData.getString("package")

        var handler = Handler(Looper.getMainLooper());
        handler.postDelayed({
            val intent = applicationContext.packageManager.getLaunchIntentForPackage(packageName.toString())
            intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent?.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
            applicationContext.startActivity(intent)
        }, 1000)

        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
    }
}
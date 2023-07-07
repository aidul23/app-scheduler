package xit.zubrein.demo.ui.scheduler

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_list.view.*
import xit.zubrein.demo.R
import xit.zubrein.demo.adapter.AppListAdapter
import xit.zubrein.demo.base.BaseActivity
import xit.zubrein.demo.databinding.ActivityScedulerBinding
import xit.zubrein.demo.listener.SchedulerCallBack
import xit.zubrein.demo.model.ModelAppData
import java.util.*

@AndroidEntryPoint
class SchedulerActivity : BaseActivity<ActivityScedulerBinding>(),
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener,
    SchedulerCallBack {


    lateinit var builder: AlertDialog.Builder
    lateinit var dialog: AlertDialog



    override fun getView() = R.layout.activity_sceduler

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        binding.addSchedule.setOnClickListener {
            val appList = installedApps()
            builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.app_list, null)
            view.recyclerView.setHasFixedSize(true)
            builder.setView(view)
            builder.setCancelable(true)
            dialog = builder.create()
            val adapter = AppListAdapter(this, dialog, this)
            view.recyclerView.adapter = adapter
            adapter.addItems(appList)
            dialog.show()
        }

    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun installedApps(): MutableList<ModelAppData> {

        var appList = mutableListOf<ModelAppData>();

        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        for (app in apps) {
            if (app.flags and (ApplicationInfo.FLAG_UPDATED_SYSTEM_APP or ApplicationInfo.FLAG_SYSTEM) > 0) {
                // It is a system app
            } else {
                // It is installed by the user
                try {
                    val appName = packageManager.getApplicationLabel(
                        packageManager.getApplicationInfo(
                            app.packageName,
                            PackageManager.GET_META_DATA
                        )
                    )
                    appList.add(ModelAppData(1, appName as String, app.packageName))
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }

            }
        }

        return appList
    }

    override fun passData(data: ModelAppData) {
        appData = data
        showDatePicker(false)
    }
}
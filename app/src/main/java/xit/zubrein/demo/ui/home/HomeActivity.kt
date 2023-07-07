package xit.zubrein.demo.ui.home

import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_list.view.*
import xit.zubrein.demo.R
import xit.zubrein.demo.Utils.Navigator
import xit.zubrein.demo.adapter.AppListAdapter
import xit.zubrein.demo.adapter.SchedulerAdapter
import xit.zubrein.demo.base.BaseActivity
import xit.zubrein.demo.databinding.ActivityMainBinding
import xit.zubrein.demo.listener.SchedulerDeleteCallBack
import xit.zubrein.demo.model.ModelAppData
import xit.zubrein.demo.model.ModelSchedule
import xit.zubrein.demo.ui.scheduler.SchedulerActivity


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityMainBinding>(),
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener,
    SchedulerDeleteCallBack {

    var ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469

    lateinit var builder: AlertDialog.Builder
    lateinit var alert: AlertDialog

    override fun getView() = R.layout.activity_main

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (!Settings.canDrawOverlays(this)) {
                permissionDialog()
            }
        }


        homeViewModel.getScheduleData.observe(this) {
            val adapter = SchedulerAdapter(this, this)
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.adapter = adapter
            adapter.addItems(it)
        }

        binding.addSchedule.setOnClickListener {
            Navigator.sharedInstance.navigate(this, SchedulerActivity::class.java)
        }

    }

    override fun deleteData(schedule: ModelSchedule) {
        addToSchedule.cancelSchedule(schedule.scheduleTime.toString())
        schedulerViewModel.delete(schedule)
    }

    override fun updateData(schedule: ModelSchedule) {
        previosScheduleTime = schedule.scheduleTime
        appData = ModelAppData(schedule.id,schedule.appName,schedule.packageName)
        showDatePicker(true)
    }

    override fun inactiveSchedule(schedule: ModelSchedule) {
        schedule.status = false
        addToSchedule.cancelSchedule(schedule.scheduleTime.toString())
        schedulerViewModel.update(schedule)
    }

    override fun activeSchedule(schedule: ModelSchedule) {
        schedule.status = true
        addToSchedule.addToQueue(schedule.scheduleTime, schedule.packageName)
        schedulerViewModel.update(schedule)
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                // You don't have permission
                checkPermission()
            }
        }
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE)
            }
        }
    }

    private fun permissionDialog(){
        builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder
            .setTitle("Permission Alert !!")
            .setMessage("Allow Display over other apps permission to work on Android 10 or higher")
            .setPositiveButton("Grant") { dialog, id ->
                checkPermission()
            }
            .setNegativeButton("Not Now") { dialog, id ->
                dialog.cancel()
            }

        alert = builder.create()
        alert.show()
    }
}
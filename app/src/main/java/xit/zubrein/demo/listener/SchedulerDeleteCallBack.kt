package xit.zubrein.demo.listener

import xit.zubrein.demo.model.ModelSchedule

interface SchedulerDeleteCallBack {
    fun deleteData(schedule : ModelSchedule)

    fun updateData(schedule : ModelSchedule)

    fun inactiveSchedule(schedule : ModelSchedule)

    fun activeSchedule(schedule : ModelSchedule)
}
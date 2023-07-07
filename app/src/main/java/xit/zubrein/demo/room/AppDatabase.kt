package xit.zubrein.gogo.room

import androidx.room.Database
import androidx.room.RoomDatabase
import xit.zubrein.demo.model.ModelSchedule
import xit.zubrein.gogo.room.dao.ScheduleDao


@Database(entities = [ModelSchedule::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun scheduleDao() : ScheduleDao
}
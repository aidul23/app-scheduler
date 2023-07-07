package xit.zubrein.gogo.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xit.zubrein.demo.ui.scheduler.SchedulerRepository
import xit.zubrein.gogo.room.dao.ScheduleDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun providesScheduleDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "ScheduleDatabase").build()

    @Provides
    fun providesScheduleDao(appDatabase: AppDatabase): ScheduleDao = appDatabase.scheduleDao()

    @Provides
    fun providesScheduleRepository(scheduleDao: ScheduleDao): SchedulerRepository =
        SchedulerRepository(scheduleDao)


}
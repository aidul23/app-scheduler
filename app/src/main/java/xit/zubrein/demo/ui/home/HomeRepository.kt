package xit.zubrein.demo.ui.home

import kotlinx.coroutines.flow.Flow
import xit.zubrein.demo.model.ModelSchedule
import xit.zubrein.demo.network.apis.DemoApiService
import xit.zubrein.gogo.room.dao.ScheduleDao
import javax.inject.Inject

class HomeRepository
@Inject
constructor(private val scheduleDao: ScheduleDao) {

    val getScheduleData: Flow<List<ModelSchedule>> = scheduleDao.getScheduleList()

}
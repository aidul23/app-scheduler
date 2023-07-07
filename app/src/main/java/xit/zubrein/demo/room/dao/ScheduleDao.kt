package xit.zubrein.gogo.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xit.zubrein.demo.model.ModelSchedule

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: ModelSchedule)

    @Query("SELECT * FROM schedule ORDER BY id ASC")
    fun getScheduleList(): Flow<List<ModelSchedule>>

    @Query("SELECT EXISTS(SELECT * FROM schedule WHERE scheduleTimeInWord = :time)")
    suspend fun doesDataExist(time: String): Boolean

    @Delete
    fun deleteSchedule(model: ModelSchedule)

    @Query("UPDATE schedule SET scheduleTime = :time,scheduleTimeInWord = :timeInWord,status = :status  WHERE id = :id")
    fun update(time: Long, timeInWord: String, id: Int, status: Boolean)

    @Query("DELETE FROM schedule")
    suspend fun clearScheduleTable()

}
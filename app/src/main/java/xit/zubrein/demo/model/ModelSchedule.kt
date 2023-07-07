package xit.zubrein.demo.model

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ModelSchedule(

    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var scheduleTime:Long,
    var scheduleTimeInWord:String,
    var appName:String,
    var packageName:String,
    var status:Boolean
)

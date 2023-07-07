package xit.zubrein.demo.Utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateFormatUtils {

    companion object {
        val shared = DateFormatUtils()
    }

    private val monthName = SimpleDateFormat("MMMM", Locale.getDefault())
    private val dayName = SimpleDateFormat("EEE", Locale.getDefault())
    private val dayToday = SimpleDateFormat("dd MMMM", Locale.getDefault())
    private val preFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private var dateFormat = "dd MMM, yyyy hh:mm a"
    private val dateTimeFormatter = SimpleDateFormat(dateFormat)

    fun getFormattedDateTime(dateStr: String): Array<String> {
        val newFormatTime = SimpleDateFormat("hh:mm a", Locale.getDefault())  // 12:00:00 pm
        val newFormatDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()) // Format 31 Jan, 2018
        val DATE: Date
        var date = ""
        var time = ""
        try {
            DATE = preFormat.parse(dateStr)
            date = newFormatDate.format(DATE)
            time = newFormatTime.format(DATE)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return arrayOf(date, time)
    }



    fun getTime(dateStr: String): Array<String> {
        val newFormatHour = SimpleDateFormat("hh", Locale.getDefault())
        val newFormatMin = SimpleDateFormat("mm", Locale.getDefault())
        val newFormatA = SimpleDateFormat("a", Locale.getDefault())
        var hour = ""
        var min = ""
        var ampm = ""
        try {
            hour = newFormatHour.format(preFormat.parse(dateStr))
            min = newFormatMin.format(preFormat.parse(dateStr))
            ampm = newFormatA.format(preFormat.parse(dateStr))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return arrayOf(hour,min,ampm)
    }

    fun getDate(dateStr: String): Array<String> {
        val newFormatYear = SimpleDateFormat("yyyy", Locale.getDefault())
        val newFormatMonth = SimpleDateFormat("MM", Locale.getDefault())
        val newFormatDay = SimpleDateFormat("dd", Locale.getDefault())
        var year = ""
        var month = ""
        var day = ""
        try {
            year = newFormatYear.format(preFormat.parse(dateStr))
            month = newFormatMonth.format(preFormat.parse(dateStr))
            day = newFormatDay.format(preFormat.parse(dateStr))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return arrayOf(year, month, day)
    }

    fun getDateWithMonthName(dateStr: String): String{
        val newFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()) // Format 31 Jan, 2018
        val preFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Format 2018-11-11
        var date = ""
        try {
            date = newFormat.format(preFormat.parse(dateStr))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    fun convertMilliSecondsToTime(milliSeconds: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        val ssFormatter = SimpleDateFormat("hh:mm")  // 12:00:00 pm
        ssFormatter.timeZone = TimeZone.getTimeZone("UTC")
        return ssFormatter.format(calendar.timeInMillis)
    }

    fun convertUnixToTime(unixSeconds: Long): String {
        val date = Date(unixSeconds * 1000)
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())  // 12:00:00 pm
        // sdf.timeZone = TimeZone.getTimeZone("GMT+06:00")
        return sdf.format(date)
    }

    fun convertMilisToTime(milis: Long): String {
        val sdf = SimpleDateFormat("dd MMM, yyyy hh:mm:ss a", Locale.getDefault())  // 12:00:00 pm
        // sdf.timeZone = TimeZone.getTimeZone("GMT+06:00")
        return sdf.format(milis)
    }

    fun getDayToday(): String {
        val cal = Calendar.getInstance()
        return dayToday.format(cal.time)
    }

    fun getDayName(dateToGetDayName: String): String {
        return dayName.format(preFormat.parse(dateToGetDayName))
    }

    fun getThisMonthName(): String {
        val cal = Calendar.getInstance()
        return monthName.format(cal.time)
    }
}
package xit.zubrein.demo.Utils.pref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor (@ApplicationContext context: Context) {

    private val mPref: SharedPreferences = context.getSharedPreferences(
        context.applicationContext.packageName,
        Context.MODE_PRIVATE
    )

    private var mEditor: SharedPreferences.Editor? = null

    fun putString(key: String, value: String) {
        doEdit()
        mEditor!!.putString(key, value)
        doCommit()
    }

    fun getString(key: String): String {
        return mPref.getString(key, "") ?: ""
    }

    fun getString(key: String, defaultValue: String): String? {
        return mPref.getString(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        doEdit()
        mEditor!!.putInt(key, value)
        doCommit()
    }

    fun getInt(key: String): Int {
        return mPref.getInt(key, 0)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return mPref.getInt(key, defaultValue)
    }

    fun putDouble(key: String, value: Double) {
        doEdit()
        mEditor!!.putString(key, value.toString())
        doCommit()
    }

    fun getDouble(key: String, defaultValue: Double): Double {
        val result = mPref.getString(key, "")
        return java.lang.Double.parseDouble(result.toString())
    }

    fun putBoolean(key: String, value: Boolean) {
        doEdit()
        mEditor!!.putBoolean(key, value)
        doCommit()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mPref.getBoolean(key, defaultValue)
    }

    fun putStringArrayList(key: String, arrayList: ArrayList<String>) {
        doEdit()
        val gson = Gson()
        val json = gson.toJson(arrayList)
        mEditor!!.putString(key, json)
        doCommit()
    }

    fun getStringArrayList(key: String): ArrayList<String>? {
        val gson = Gson()
        val json = mPref.getString(key, null)
        val type = object : TypeToken<ArrayList<String>>() {

        }.type
        return gson.fromJson<ArrayList<String>>(json, type)
    }

    fun putIntegerArrayList(key: String, arrayList: ArrayList<Int>) {
        doEdit()
        val gson = Gson()
        val json = gson.toJson(arrayList)
        mEditor!!.putString(key, json)
        doCommit()
    }

    fun getIntegerArrayList(key: String): ArrayList<Int>? {
        val gson = Gson()
        val json = mPref.getString(key, null)
        val type = object : TypeToken<ArrayList<Int>>() {

        }.type
        return gson.fromJson<ArrayList<Int>>(json, type)
    }

    fun putStringHashMap(key: String, map: HashMap<String, String>) {
        doEdit()
        val gson = Gson()
        val json = gson.toJson(map)
        mEditor!!.putString(key, json)
        doCommit()
    }

    fun getStringHashMap(key: String): HashMap<String, String>? {
        val gson = Gson()
        val json = mPref.getString(key, null)
        val type = object : TypeToken<HashMap<String, String>>() {

        }.type
        return gson.fromJson<HashMap<String, String>>(json, type)
    }

    fun remove(key: String) {
        doEdit()
        mEditor!!.remove(key)
        doCommit()
    }

    fun firstTimeAskingPermission(permission: String, isFirstTime: Boolean) {
        doEdit()
        mEditor!!.putBoolean(permission, isFirstTime)
        doCommit()
    }

    fun isFirstTimeAskingPermission(permission: String): Boolean {
        return mPref.getBoolean(permission, true)
    }

    private fun doEdit() {
        if (mEditor == null) {
            mEditor = mPref.edit()
        }
    }

    private fun doCommit() {
        mEditor?.let {
            mEditor!!.apply()
            mEditor = null
        }
    }

    fun clearPreference() {
        doEdit()
        mEditor?.let { mEditor!!.clear() }
        doCommit()
    }
}

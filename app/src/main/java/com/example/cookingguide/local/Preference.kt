package com.example.cookingguide.local
//Lop Preference dung de quan li cac gia tri luu tru trong SharedPreferences
//SharedPreferences la co che luu tru du lieu dang Key-Value
//Lop Preference nay su dung de quan ly viec luu tru va truy xuat cac gia tri cau hinh
// -> trong SharedPreferences
import android.content.Context
import android.content.SharedPreferences

class Preference(context: Context) {
    //Khai bao cac thuoc tinh
    private val BEARER_HEADER = "Bearer0 "
    private val sharedPreferences: SharedPreferences
    private val PREFS_ACCOUNT = "PREFS_ACCOUNT"
    private val KEY_TYPE_ONE = "KEY_TYPE_ONE"
    private val KEY_TOTAL_COIN = "KEY_TOTAL_COIN" // coin
    private val KEY_FIRST_INSTALL = "KEY_FIRST_INSTALL" // coin
    private val INT_ZERO = 0 // coin

    //Phuong thuc khoi tao init
    init {
        sharedPreferences = context.getSharedPreferences(PREFS_ACCOUNT, Context.MODE_PRIVATE)
    }
    //Phuong thuc setValueTypeOne dung de luu tru gia tri vao SharedPreferences
    fun setValueTypeOne(value: String?) {
        sharedPreferences.edit().putString(KEY_TYPE_ONE, value).apply()
    }
    //Thuộc tính firstInstall là một thuộc tính Boolean có thể đọc và ghi
    var firstInstall: Boolean
        get() = sharedPreferences.getBoolean(KEY_FIRST_INSTALL, false)
        set(value) {
            sharedPreferences.edit().putBoolean(KEY_FIRST_INSTALL, value).apply()
        }
    //Phương thức setValueCoin và getValueCoin cho phép đặt và lấy giá trị cho KEY_TOTAL_COIN
    fun setValueCoin(value: Int) {
        sharedPreferences.edit().putInt(KEY_TOTAL_COIN, value).apply()
    }

    fun getValueCoin(): Int {
        return sharedPreferences.getInt(KEY_TOTAL_COIN, INT_ZERO)
    }


    companion object {
        var instance: Preference? = null
        fun buildInstance(context: Context): Preference? {
            if (instance == null) {
                instance = Preference(context)
            }
            return instance
        }
    }
}
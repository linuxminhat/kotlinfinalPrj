package com.example.cookingguide.base.recycleview
//Dinh nghia lop truu tuong dung de hien thi du lieu tren mot RecyclerView

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

//Chap nhan mot loai tham so kieu DATA
//DATA la loai du lieu ma ViewHolder se hien thi
abstract class BaseViewHolderMore<DATA>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bindViewHolder(data: DATA)
}
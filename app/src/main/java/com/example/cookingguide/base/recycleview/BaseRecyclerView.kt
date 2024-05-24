package com.example.cookingguide.base.recycleview
//Dinh nghia lop truu tuong BaseRecyclerViewAdapter dung de hien thi du lieu len mot RecyclerView
//Lop nay ke thua tu RecyclerView.Adapter

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingguide.R

abstract class BaseRecyclerViewAdapter<T : Any, VH : BaseViewHolderMore<T>>() :
    RecyclerView.Adapter<VH>() {
    //Dinh nghia mot Interface
    interface OnClickItem {
        //Phuong thuc isClickItem co ba tham so
        fun isClickItem(view: View, position: Int, isCheck: Boolean)
    }
    //yeu cau trien khai phuong thuc truu tuong o lop BaseRecyclerViewAdapter
    abstract fun submitList(mList: ArrayList<T>)
    abstract fun getListItem(): MutableList<T>

    //Hieu ung hoat hinh
    open fun setAnimation(context: Context,viewToAnimate: View, anim: Int) {
        viewToAnimate.startAnimation(AnimationUtils.loadAnimation(context, anim))
    }
}
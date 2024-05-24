package com.example.cookingguide.base.recycleview
//Dinh nghia lop truu tuong BaseViewHolder dung de hien thi du lieu len mot ViewHolder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
//BaseViewHolder chap nhan hai loai tham so kieu T va VB
//T : loai du lieu ma ViewHolder se hien thi
//VB  : loai cua ViewBinding se duoc su dung


open class BaseViewHolder<T : Any, VB : ViewBinding>(
    private val binding: VB, val itemClickListener: ((T) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    //Khai bao mot bien itemData kieu T
    //Bien nay co mot getter itemData de truy cap du lieu cua ViewHolder

    private var _itemData: T? = null
    val itemData get() = _itemData

    //Ham khoi tao init de thiet lap su kien click cho itemView
    //Khi itemView duoc click, no se goi itemClickListener voi du lieu cua item
    init {

        itemView.setOnClickListener {
            itemData?.let { it1 -> itemClickListener?.invoke(it1) }
        }
    }

    //Phuong thuc bind giup lien ket du lieu voi ViewHolder
    //Phuong thuc nay nhan du lieu cua item duoi dang tham so va luu tru no
    //Luu tru o itemData
    open fun bind(itemData: T) {
        this._itemData = itemData
    }

}

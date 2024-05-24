package com.example.cookingguide.base.recycleview
//Thu muc co so chua cac lop co ban de hien thi du lieu tren RecyclerView
//Thu muc co so chua cac lop co so ma cac lop khac trong ung dung se ke thua tu do
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

//Day la mot lop co so cho ViewHolder va RecyclerAdapter
//BaseRecyclerAdapter la mot lop truu tuong ke thua tu ListAdapter
//ListAdapter la mot lop giup hien thi du lieu tren RecyclerView

@Retention(AnnotationRetention.SOURCE)
annotation class RecyclerType
//Chap nhan ba loai tham so
//Khai bao ba loai tham so kieu T : kieu du lieu Adapter hien thi
//Khai bao ba loai tham so kieu VB : kieu du lieu ViewBinding
//Khai bao ba loai tham so kieu VH : kieu du lieu ViewHolder

//ListAdapter su dung diffUtil de tinh toan su khac biet giua hai danh sach du lieu
abstract class BaseRecyclerAdapter<T : Any, VB : ViewBinding, VH : BaseViewHolder<T, VB>>(
    diffUtilCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffUtilCallback) {

    //Ghi de phuong thuc onBindViewHolder tu ListAdapter de lien ket du lieu voi ViewHolder
    override fun onBindViewHolder(holder: VH, position: Int) {
        //Goi phuong thuc bind cua ViewHolder de gan du lieu vao ViewHolder
        holder.bind(getItem(position))
    }
    //Phuong thuc submitList de cap nhat du lieu hien thi tren RecyclerView
    //Neu danh sach du lieu la null thi se hien thi danh sach rong vao submitList
    override fun submitList(list: List<T>?) {
        super.submitList(list ?: emptyList())
    }

    abstract fun getViewHolderDataBinding(parent: ViewGroup, viewType: Int): VB
}

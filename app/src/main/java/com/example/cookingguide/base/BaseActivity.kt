package com.example.cookingguide.base
//Dinh nghia lop truu tuong BaseActivity dung lam lop co so cho cac hoat dong ben trong ung dung
//Lop nay ke thua tu AppCompatActivity -> hoat dong tuong thich phien ban Android khac nhau


import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

//Chap nhan mot loai tham so kieu VB : kieu du lieu ViewBinding

abstract class BaseActivity<VB : ViewBinding>(
    private val bindingFactory: (LayoutInflater) -> VB,
) : AppCompatActivity() {

    //Khai bao bien _biding de luu tru ViewBinding cua hoat dong
    //Bien nay co mot getter binding de truy cap ViewBinding cua hoat dong

    private var _binding: VB? = null
    val binding :VB
        get() = _binding as VB

    //Ghi de phuong thuc onCreate tu AppCompatActivity
    // -> Thiet lap ViewBinding cho hoat dong va goi phuong thuc initAction
    // -> Goi bidingFactory voi layoutInflater de tao ViewBinding
    // -> Thiet lap no nhu View cho hoat dong

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory.invoke(layoutInflater)
        setContentView(binding.root)
        initAction()
    }

    abstract fun initAction()
}
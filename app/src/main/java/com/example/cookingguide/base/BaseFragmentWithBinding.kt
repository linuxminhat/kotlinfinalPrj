package com.example.cookingguide.base
//Lop Fragment la lop co so trong Android SDK dung de tao ra giao dien cho nguoi dung
//Lop truu tuong BaseFragmentWithBinding ke thua tu Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

//Lop ao BaseFragmentWithBinding co mot tham so kieu ViewBinding

abstract class BaseFragmentWithBinding<VB : ViewBinding>(
    private val bindingInflate: (inflater: LayoutInflater) -> VB
) : Fragment() {

    //Khai bao bien _binding de luu tru ViewBinding cua Fragment
    //Bien nay co mot getter binding de truy cap ViewBinding cua Fragment
    private var _binding: VB? = null

    val binding: VB
        get() = _binding as VB

    //Ghi de phuong thuc onCreateView tu Fragment
    //Trong phuong thuc nay, no goi bidingInflate voi inflater de tao ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflate.invoke(inflater)
        //Tra ve root cua ViewBinding nhu la view cho Fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
        initData()
    }

    abstract fun initAction()

    abstract fun initData()

    override fun onDestroyView() {
        _binding == null
        super.onDestroyView()
    }
}
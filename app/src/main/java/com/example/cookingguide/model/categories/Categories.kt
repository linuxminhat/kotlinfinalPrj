package com.example.cookingguide.model.categories
//Lop Model trong thu muc model dung de dinh nghia cac doi tuong du lieu

//Lop du lieu Categories dung de luu tru thong tin cua cac danh muc
//Lop nay su dung phan tich cu phap du lieu JSON tu API va chuyen doi thanh doi tuong Kotlin
//Anotation để đặc tả dữ liệu cho một đối tượng

import com.google.gson.annotations.SerializedName

data class Categories(
    //Chi dinh ten cua truong JSON tuong ung
    @SerializedName("idCategory")
    //Thuoc tinh dai dien id cua danh muc
    val idCategory: String,
    //Thuoc tinh dai dien cho ten cua danh muc
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strCategoryDescription")
    //Thuoc tinh dai dien cho mo ta cua danh muc
    val strCategoryDescription: String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String
)
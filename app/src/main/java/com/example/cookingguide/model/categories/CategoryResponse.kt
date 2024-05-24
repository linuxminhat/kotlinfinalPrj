package com.example.cookingguide.model.categories
//Lop du lieu CategoryResponse dung de dai dien phan hoi tu mot API tra ve danh sach cac danh muc

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    //Annotation chi dinh ten cua truong JSON tuong ung thuoc tinh trong lop
    @SerializedName("categories")
    //Thuoc tinh nay la mot ArrayList cua lop Categories
    val categories: ArrayList<Categories>
)
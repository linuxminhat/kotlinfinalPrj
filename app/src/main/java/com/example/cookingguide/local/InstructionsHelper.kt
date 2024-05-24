package com.example.cookingguide.local
//Lop InstructionsHelper ke thua tu SQLiteOpenHelper
//Lop nay duoc su dung de tao va quan ly co so du lieu SQLite

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.cookingguide.model.FavouriteModel
import com.example.cookingguide.model.SeenModel
import com.example.cookingguide.model.filter.CategoryDetails

//The local directory contains files related to the database
//Khoi tao co so du lieu voi ten la "cookguide.db" va phien ban la 1
//Khoi tao bang "instructions" va "favourite"

class InstructionsHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "cookguide.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_INSTRUCTION = "instructions"
        private const val TABLE_FAVOURITE = "favourite"
    }

//Trong phuong thuc onCreate tao ra hai bang "instructions" va "favourite"
//Bang "instructions" co cac cot id, idMeal, strMeal, strMealThumb, strCategoryThumb
//Bang "favourite" co cac cot id, idMeal, strMeal, strMealThumb, strCategoryThumb, strCheck

    override fun onCreate(p0: SQLiteDatabase?) {
        val querySeen =
            "CREATE TABLE $TABLE_INSTRUCTION ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idMeal VARCHAR (500) NOT NULL," +
                    "strMeal VARCHAR(500) NOT NULL," +
                    "strMealThumb VARCHAR (50) NOT NULL," +
                    "strCategoryThumb VARCHAR(50) NOT NULL)"
        val queryFavourite =
            "CREATE TABLE $TABLE_FAVOURITE ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idMeal VARCHAR (500) NOT NULL," +
                    "strMeal VARCHAR(500) NOT NULL," +
                    "strMealThumb VARCHAR (50) NOT NULL," +
                    "strCategoryThumb VARCHAR(50) NOT NULL," +
                    "strCheck VARCHAR(10) NOT NULL)"
        p0?.execSQL(querySeen)
        p0?.execSQL(queryFavourite)
    }
//Trong phuong thuc onUpgrade xoa hai bang "instructions" va "favourite"
//Sau do goi lai phuong thuc onCreate de tao lai hai bang neu muon
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_INSTRUCTION")
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_FAVOURITE")
        onCreate(p0)
    }

//Phuong thuc de them du lieu vao hai bang nay
//Kiem tra xem mot ban ghi co ton tai trong bang hay khong

    //Kiem tra du lieu cua categorySeen co ton tai trong bang "instructions" hay khong
    //Neu khong ton tai thi them du lieu vao bang "instructions" -> insertSeen
    fun insertSeenIfNotExists(categorySeen: CategoryDetails) {
        if (!isDataExists(categorySeen.idMeal, TABLE_INSTRUCTION)) {
            insertSeen(categorySeen)
        }
    }

    //Kiem tra du lieu cua favourite co ton tai trong bang "favourite" hay khong
    //Neu khong ton tai thi them du lieu vao bang "favourite" -> insertFavourite
    fun insertFavouriteNotExists(favourite: FavouriteModel) {
        if (!isDataExists(favourite.idMeal, TABLE_FAVOURITE)) {
            insertFavourite(favourite)
        }
    }
    //Phương thức insertSeen sử dụng  chèn  đối tượng CategoryDetails vào  TABLE_INSTRUCTION trong CSDL SQLite.
    //Phuong thuc insertSeen de them du lieu vao bang "instructions"
    //Phuong thuc nay nhan mot doi tuong CategoryDetails lam tham so va chen vao TABLE_INSTRUCTION

    //Doi tuong CategoryDetails dai dien mot muc ma nguoi dung da "seen" trong ung dung
    private fun insertSeen(seenModel: CategoryDetails) {
        //Khoi tao mot doi tuong SQLiteDatabase de ghi du lieu
        val mDatabase = writableDatabase
        mDatabase.execSQL(
            //Thuc thi mot cau lenh SQL INSERT INTO de chen du lieu vao bang "instructions"
            "INSERT INTO '$TABLE_INSTRUCTION' (idMeal, strMeal, strMealThumb, strCategoryThumb) VALUES (?,?,?,?)",
            arrayOf(
                //Cac gia tri duoc chen vao bang
                seenModel.idMeal,
                seenModel.strMeal,
                seenModel.strMealThumb,
                seenModel.strCategoryThumb
            )
        )
    }

    //Chen doi tuong FavouriteModel vao bang "TABLE_FAVOURITE"
    private fun insertFavourite(favourite: FavouriteModel) {
        //Khoi tao doi tuong SQLiteDatabase co the ghi duoc bang cach goi phuong thuc
        //Phuong thuc writableDatabase
        val mDatabase = writableDatabase
        mDatabase.execSQL(
            "INSERT INTO '$TABLE_FAVOURITE' (idMeal, strMeal, strMealThumb, strCategoryThumb, strCheck) VALUES (?,?,?,?,?)",
            arrayOf(
                favourite.idMeal,
                favourite.strMeal,
                favourite.strMealThumb,
                favourite.strCategoryThumb,
                favourite.strCheck
            )
        )
    }

    //Phuong thuc isDataExists de kiem tra xem mot ban ghi co ton tai
    private fun isDataExists(idMeals: String, table: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $table WHERE idMeal = ?"
        //Ket qua cau truy van luu trong doi tuong Cursor
        val cursor = db.rawQuery(query, arrayOf(idMeals))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    //Phương thức deleteDataFavourite được sử dụng để xóa một bản ghi từ bảng TABLE_FAVOURITE trong cơ sở dữ liệu SQLite dựa trên idMeal.
    fun deleteDataFavourite(idMeal: String) {
        //Khoi tao mot doi tuong SQLiteDatabase co the ghi duoc bang cach goi
        //writableDatabase
        val db = writableDatabase
        //Thuc thi cau lenh SQL DELETE FROM de xoa bản ghi có idMeal tương ứng từ bảng TABLE_FAVOURITE.
        db.execSQL("DELETE FROM '$TABLE_FAVOURITE' where idMeal = ?", arrayOf(idMeal))
    }


    //Phuong thuc getCategorySeen truy van CSDL va tra ve danh sach cac doi tuong SeenModel
    @SuppressLint("Range")
    //Khoi tao mot danh sach rong luu tru cac doi tuong SeenModel
    fun getCategorySeen(): ArrayList<SeenModel> {
        val categorySend = arrayListOf<SeenModel>()
        //Tao mot cau truy van SQL de lay tat ca ban gi tu bang "TABLE_INSTRUCTION"
        //Sap xep chung theo thu tu giam dan cua id
        val selectQuery = "SELECT * FROM $TABLE_INSTRUCTION ORDER BY id DESC"
        val db = this.readableDatabase
        //Phuong thuc rawQuery() duoc su dung de thuc thi cau truy van SQL
        //Ket qua cau truy van duoc luu trong mot doi tuong Cursor
        val cursor = db.rawQuery(selectQuery, null)
        //Neu cursor chua it nhat mot ban ghi
        if (cursor.moveToFirst()) {
            //Phuong thuc lap qua cac ban ghi trong Cursor
            do {
                val idMeal = cursor.getString(cursor.getColumnIndex("idMeal"))
                val strMeal = cursor.getString(cursor.getColumnIndex("strMeal"))
                val strMealThumb = cursor.getString(cursor.getColumnIndex("strMealThumb"))
                val strCategoryThumb = cursor.getString(cursor.getColumnIndex("strCategoryThumb"))
                categorySend.add(SeenModel(idMeal, strMeal, strMealThumb, strCategoryThumb))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categorySend
    }

    //Phuong thuc getDataFavourite truy van CSDL va tra ve danh sach cac doi tuong FavouriteModel
    @SuppressLint("Range")
    fun getDataFavourite(): ArrayList<FavouriteModel> {
        //Khoi tao mot danh sach rong favourite de luu tru cac doi tuong FavouriteModel
        val favourite = arrayListOf<FavouriteModel>()
        //No tao mot cau truy van SQL de lay tat ca ban ghi va sap xep chung giam dan
        //Theo thu tu ID
        val selectQuery = "SELECT * FROM $TABLE_FAVOURITE ORDER BY id DESC"
        val db = this.readableDatabase
        //Phuong thuc rawQuery() duoc su dung de thuc thi cau truy van SQL
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val idMeal = cursor.getString(cursor.getColumnIndex("idMeal"))
                val strMeal = cursor.getString(cursor.getColumnIndex("strMeal"))
                val strMealThumb = cursor.getString(cursor.getColumnIndex("strMealThumb"))
                val strCategoryThumb = cursor.getString(cursor.getColumnIndex("strCategoryThumb"))
                val strCheck = cursor.getString(cursor.getColumnIndex("strCheck"))
                favourite.add(
                    FavouriteModel(
                        idMeal,
                        strMeal,
                        strMealThumb,
                        strCategoryThumb,
                        strCheck
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return favourite
    }

    //Phuong thuc getDataByIdMeals dung de truy van csdl
    //Tra ve gia tri cua cot strCheck tu bang TABLE_FAVOURITE dua tren idMeal

    @SuppressLint("Range")
    fun getDataByIdMeals(idMeal: String): String {
        //Khoi tao mot bien listCheck voi gia tri ban dau la null
        var listCheck : String ?= null
        //Khoi tao mot doi tuong SQLiteDatabase co the doc duoc bang cach goi readableDatabase

        val db = this.readableDatabase
        //Phuong thuc rawQuery duoc su dung de thuc thi cau truy van SQL
        //Ket qua cau lenh truy van duoc luu trong Cursor
        val cursor =
            db.rawQuery(
                "SELECT strCheck FROM '$TABLE_FAVOURITE' WHERE idMeal = ?", arrayOf(
                    idMeal
                )
            )
        //Phuong thuc sau do lap qua tat ca cac ban ghi trong Cursor

        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            listCheck = cursor.getString(0)
            cursor.moveToNext()
        }
        //Cursor duoc dong lai de giai phong tai nguyen
        cursor.close()
        return listCheck.toString()
    }
}
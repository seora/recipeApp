package com.seora.recipeapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "categoryitems")
data class CategoryItems(

    @PrimaryKey(autoGenerate = true)
    var id : Int,


    @ColumnInfo(name = "idCategory")
    @Expose
    @SerializedName("idCategory")
    val idcategory: String,

    @ColumnInfo(name = "strCategory")
    @Expose
    @SerializedName("strCategory")
    val strcategory: String,

    @ColumnInfo(name = "strCategoryThumb")
    @Expose
    @SerializedName("strCategoryThumb")
    val strcategorythumb: String,

    @ColumnInfo(name = "strCategoryDescription")
    @Expose
    @SerializedName("strCategoryDescription")
    val strcategorydescription: String
)

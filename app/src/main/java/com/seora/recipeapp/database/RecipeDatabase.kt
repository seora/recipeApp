package com.seora.recipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.seora.recipeapp.dao.RecipeDao
import com.seora.recipeapp.entities.Category
import com.seora.recipeapp.entities.CategoryItem
import com.seora.recipeapp.entities.Recipes
import com.seora.recipeapp.entities.converter.CategoryListConverter


@Database(entities = [Recipes::class, Category::class, CategoryItem::class, CategoryListConverter::class], version = 1, exportSchema = false)
abstract class RecipeDatabase:RoomDatabase() {

    companion object{
        var recipesDatabase:RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase{
            if(recipesDatabase != null){
                recipesDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao():RecipeDao
}
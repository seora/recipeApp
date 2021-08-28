package com.seora.recipeapp

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.seora.recipeapp.adapter.MainCategoryAdapter
import com.seora.recipeapp.adapter.SubCategoryAdapter
import com.seora.recipeapp.database.RecipeDatabase
import com.seora.recipeapp.entities.CategoryItems
import com.seora.recipeapp.entities.MealItems
import com.seora.recipeapp.entities.Recipes
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch


class HomeActivity : BaseActivity() {
    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        getDataFromDb()

        mainCategoryAdapter.setClickListener(onCLicked)
        subCategoryAdapter.setClickListener(onCLickedSubItem)

        //temporary data
//        arrMainCategory.add(Recipes(1, "Beef"))
//        arrMainCategory.add(Recipes(2, "Chicken"))
//        arrMainCategory.add(Recipes(3, "Dessert"))
//        arrMainCategory.add(Recipes(4, "Lamb"))
//
//        mainCategoryAdapter.setData(arrMainCategory)

//        arrSubCategory.add(Recipes(1, "Beef and mustard pie"))
//        arrSubCategory.add(Recipes(2, "Chicken and mushroom"))
//        arrSubCategory.add(Recipes(3, "Icecream"))
//        arrSubCategory.add(Recipes(4, "kapsalon"))
//
//        subCategoryAdapter.setData(arrSubCategory)

//        rv_main_category.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        rv_main_category.adapter = mainCategoryAdapter

//        rv_sub_category.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        rv_sub_category.adapter = subCategoryAdapter


    }

    private val onCLicked  = object : MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onCLickedSubItem  = object : SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity,DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun getDataFromDb(){
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()

                getMealDataFromDb(arrMainCategory[0].strcategory)
                mainCategoryAdapter.setData(arrMainCategory)
                rv_main_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_main_category.adapter = mainCategoryAdapter
            }


        }
    }

    private fun getMealDataFromDb(categoryName:String){
        tvCategory.text = "$categoryName Category"
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealItems>
                subCategoryAdapter.setData(arrSubCategory)
                rv_sub_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_sub_category.adapter = subCategoryAdapter
            }


        }
    }
}

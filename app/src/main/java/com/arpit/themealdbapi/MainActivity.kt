package com.arpit.themealdbapi

import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arpit.newsapp3.mealService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var mealadapter : MyAdapter
    private var mealSearchList = mutableListOf<Meal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buSearch.setOnClickListener {
            getMealList()
            progressBar.visibility = VISIBLE
        }

        Toast.makeText(applicationContext , "Activity Loaded" , Toast.LENGTH_LONG).show()

        mealadapter = MyAdapter(this, mealSearchList)
        rvMeal.adapter = mealadapter
        val layoutMAnager = LinearLayoutManager(this)
        rvMeal.layoutManager = layoutMAnager

        checkConnection()

    }

    private fun checkConnection() {
        var connectivityManager = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.getActiveNetworkInfo()

        if(networkInfo == null){
            AlertDialog.Builder(this)
                .setTitle("RETRY")
                .setMessage("NO INTERNET CONNECTION")
                .setPositiveButton("Retry",{ dialogInterface: DialogInterface, i: Int -> checkConnection() })
                .show()
        }
    }

    private fun getMealList(){

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = mealService.apiService.getMeal(etSearch.text.toString())
                if (response.isSuccessful) {
                    val meallList= response.body()
                    withContext(Dispatchers.Main) {
                        if (meallList!= null) {
                            mealSearchList.clear()
                            mealSearchList.addAll(meallList.meals!!)
                            mealadapter.notifyDataSetChanged()
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
            catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext, "Cannot Load Data" , Toast.LENGTH_LONG).show()
                }
            }

        }

    }
}
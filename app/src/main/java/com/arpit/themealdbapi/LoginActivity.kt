package com.arpit.themealdbapi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPref : SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPref = applicationContext.getSharedPreferences("myPref" , Context.MODE_PRIVATE)
        editor = sharedPref.edit()

        buSignup.setOnClickListener {
            val usernameS = etUsername.text.toString()
            val passwordS = etPassword.text.toString()

            editor.apply{

                putString("username" , usernameS)
                putString("password" , passwordS)
                apply()
            }

            Toast.makeText(applicationContext , "Signed Up" , Toast.LENGTH_LONG).show()
        }

        buLogin.setOnClickListener {
            val usernameL = etUsername.text.toString()
            val passwordL = etPassword.text.toString()

            val username = sharedPref.getString("username" , null)
            val password = sharedPref.getString("password" , null)

            if (username == usernameL){
                if (password == passwordL){
                    val intent = Intent(applicationContext, MainActivity::class.java)
                   startActivity(intent)
                }
                else{
                    Toast.makeText(applicationContext , "Wrong Password" , Toast.LENGTH_LONG).show()
                }
            } else{
                Toast.makeText(applicationContext , "Wrong UserName" , Toast.LENGTH_LONG).show()
            }

        }
    }
}
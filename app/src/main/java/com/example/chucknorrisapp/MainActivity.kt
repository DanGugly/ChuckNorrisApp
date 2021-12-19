package com.example.chucknorrisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount != 0){
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }
    }
}
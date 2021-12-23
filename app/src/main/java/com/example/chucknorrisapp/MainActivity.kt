package com.example.chucknorrisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chucknorrisapp.view.ButtonFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, ButtonFragment.newInstance())
            .commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount != 0){
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }
    }
}
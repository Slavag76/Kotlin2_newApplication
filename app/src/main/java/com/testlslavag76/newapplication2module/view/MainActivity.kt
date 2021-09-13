package com.testlslavag76.newapplication2module.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.testlslavag76.newapplication2module.R
import com.testlslavag76.newapplication2module.view.picture.PODFragment



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container, PODFragment.newInstance()).commit()
        }
    }
}
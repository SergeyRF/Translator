package com.atilladroid.translator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        if(supportFragmentManager.findFragmentById(R.id.containerMain)==null){
            val fragment = TranslateFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerMain,fragment)
                    .commit()
        }
    }

    fun startLaguageFragment(){
        val fragment = LanguageFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.containerMain,fragment)
                .addToBackStack(null)
                .commit()
    }
}

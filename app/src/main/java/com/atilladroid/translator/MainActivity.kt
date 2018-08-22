package com.atilladroid.translator

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.koin.android.architecture.ext.viewModel

class MainActivity : AppCompatActivity() {

    val viewModule: ViewModuleTranslate by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModule.startFragmendLiveData.observe(this, Observer {it->
            when(it){
                ConstantFragment.SELECT->startLaguageFragment()
                ConstantFragment.TRANSLATE->onBackPressed()
            }
        })

    }

    override fun onStart() {
        super.onStart()
        if (supportFragmentManager.findFragmentById(R.id.containerMain) == null) {
            val fragment = TranslateFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerMain, fragment)
                    .commit()
        }
    }

    fun startLaguageFragment() {
        val fragment = LanguageFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.containerMain, fragment)
                .addToBackStack(null)
                .commit()
    }


}

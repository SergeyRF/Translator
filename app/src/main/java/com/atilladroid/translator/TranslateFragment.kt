package com.atilladroid.translator


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_translate.*
import org.koin.android.architecture.ext.sharedViewModel


class TranslateFragment : Fragment() {

    val viewModule: ViewModuleTranslate by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModule.translateTextLiveData.observe(this, Observer { it->
            tv_translate.text = it
        })

        bt_translate.setOnClickListener {
            if(et_text.text.isNotEmpty()){
                viewModule.translate(et_text.text.toString())
            }
        }
    }


}

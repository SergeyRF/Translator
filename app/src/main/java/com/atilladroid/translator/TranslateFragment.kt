package com.atilladroid.translator


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.atilladroid.translator.RetroFit.Const
import com.atilladroid.translator.RetroFit.YandexApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_translate.*
import timber.log.Timber


class TranslateFragment : Fragment() {

   lateinit var  api: YandexApi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         api= YandexApi.create()

        bt_translate.setOnClickListener {

            if (et_text.text.isNotEmpty()){
                val text = et_text.text.toString()
                api.getTranslate(Const.key,"ru-en",text)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { var text=""
                            it.text.forEach { text+=it }
                            tv_translate.text =text }
            }else langs()

        }



    }

    fun langs(){
     api.getLangs(Const.key,"en")
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe { Timber.d( "MY ${it.langs}") }


    }


}

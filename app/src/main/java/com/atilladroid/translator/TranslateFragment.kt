package com.atilladroid.translator


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.atilladroid.translator.RetroFit.Atributes
import com.atilladroid.translator.RetroFit.ObjectAtribut
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

        viewModule.translateTextLiveData.observe(this, Observer { it ->
            tv_translate.text = it
        })
        viewModule.languageAfterLD.observe(this, Observer { after ->
            tv_languageAfter.text = after!!
        })

        viewModule.laguageBeforeLD.observe(this, Observer { before ->
            tv_languageBefore.text = before!!
        })
        viewModule.dictionaryTextLD.observe(this, Observer { dictionary ->
            var def: List<ObjectAtribut>
            if (dictionary != null && dictionary.def.isNotEmpty()) {
                def = dictionary.def
                var text = ""
                def.forEach {

                    text += it.text
                    if (it.pos != "null") {
                        text += ",\t" + it.pos
                    }
                    if (it.gen != null) {
                        text += ",\t" + it.gen
                    }
                    if (it.num != 0) {
                        text += ",\t ${it.num}"
                    }

                    text += "\n" + addText(it.tr) + "\n"

                    /*if (it.syn.toString() != "null") {
                        text += addText(it.syn)
                    }
                    if (it.mean.toString() != "null") {
                        text += addText(it.mean)
                    }
                    if (it.ex.toString() != null) {
                        text += addText(it.ex)
                    }
*/
                }


                tv_dictionary.text = text
            }

        })

        bt_translate.setOnClickListener {
            if (et_text.text.isNotEmpty()) {
                viewModule.translate(et_text.text.toString())
            }
        }

        tv_languageBefore.setOnClickListener {
            viewModule.setLangugeBefore()
        }
        tv_languageAfter.setOnClickListener {
            viewModule.setLanguageAfter()
        }
        iv_repear.setOnClickListener {
            viewModule.repear()
        }

        bt_more.setOnClickListener {
            if (et_text.text.isNotEmpty()) {
                viewModule.downloatMoreTranslate(et_text.text.toString())
            }

        }


    }

    fun addText(list: List<Atributes>): String {
        var text = ""
        list.forEach {

            text += it.text + "\n"
            /* if (it.pos != "null") {
                 text += ",\t" + it.pos
             }
             if (it.gen != "null") {
                 text += ",\t" + it.gen
             }
             if (it.num != 0) {
                 text += ",\t ${it.num}" + "\n"
             }*/
        }
        return text
    }
}

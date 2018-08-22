package com.atilladroid.translator


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_language.*
import org.koin.android.architecture.ext.sharedViewModel


class LanguageFragment : Fragment() {

    val viewModule:ViewModuleTranslate by sharedViewModel()
    lateinit var adapter: RvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_language, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RvAdapter(viewModule.getNowLanguage())

        rv_select.layoutManager=LinearLayoutManager(context)
        rv_select.adapter = adapter
        //viewModule.downloadLangs()
        viewModule.mapLanguageLiveData.observe(this, Observer { it->
            it?.let { adapter.setLanguages(it) }
        })

        adapter.listener = {
            viewModule.setLanguage(it)

        }

    }


}

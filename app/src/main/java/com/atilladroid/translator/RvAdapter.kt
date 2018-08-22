package com.atilladroid.translator

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import timber.log.Timber

class RvAdapter(val nowLang: String) : RecyclerView.Adapter<RvAdapter.Holder>() {

    var listener: (String) -> Unit = {}

    var mapLanguages: Map<String, String>? = null
    var listPairLang: List<Pair<String, String>>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.holder_select, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return if (listPairLang != null) {
            listPairLang!!.size
        } else 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listPairLang!![position])
    }

    fun setLanguages(list: Map<String, String>) {

        listPairLang = list.map { Pair(it.key, it.value) }.toList()
        notifyDataSetChanged()
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val check = view.findViewById<ImageView>(R.id.iv_check)
        val language = view.findViewById<TextView>(R.id.tv_language)
        val layout = view.findViewById<View>(R.id.constraintLayout_All)

        fun bind(languagePair: Pair<String, String>) {

            check.visibility = View.GONE
            if (languagePair.first == nowLang) {
                check.visibility = View.VISIBLE
            }
            language.text = languagePair.second
            layout.setOnClickListener {
                Timber.d("Clik for ${languagePair.first}")
                listener.invoke(languagePair.first)
            }

        }
    }

}


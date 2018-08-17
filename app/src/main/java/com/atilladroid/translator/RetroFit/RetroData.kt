package com.atilladroid.translator.RetroFit

import java.util.*

data class Translate(
        val code :Int,
        val lang:String,
        val text:Array<String>
)

data class Langs(
        val code: Int,
        val langs:Map<String,String>,
        val message:String
)

object Const{
    const val key ="trnsl.1.1.20180817T095122Z.1d21ef38c5a43eec.ae41c29073e67bcfdaf28b6b3407dfa24615a648"
}
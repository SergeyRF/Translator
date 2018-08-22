package com.atilladroid.translator.RetroFit

import retrofit2.http.POST

data class Translate(
        val code: Int = 0,
        val lang: String = "",
        val text: List<String> = listOf()
)

data class Langs(
        val code: Int,
        val langs: Map<String, String>,
        val message: String
)

data class DictionaryMy(
        //val head:String,
       // val def: List<Atributes>,
        val def:List<ObjectAtribut>
)

data class Atributes(
        val text:String,
        val num:Int,
        val pos:String,
        val gen:String
)
data class ObjectAtribut(
        val text:String,
        val num:Int,
        val pos:String,
        val gen:String?,
        val ts:String,
        val tr:  List<Atributes>,
        val syn: List<Atributes>,
        val mean:List<Atributes>,
        val ex:  List<Atributes>
)

object Const {
    const val key = "trnsl.1.1.20180817T095122Z.1d21ef38c5a43eec.ae41c29073e67bcfdaf28b6b3407dfa24615a648"
    const val keyDictionary = "dict.1.1.20180822T091853Z.e292ce880c01dd0a.4937b799e5a26be4886ccded55b1d6eb87719cc6"
}
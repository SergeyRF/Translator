package com.atilladroid.translator.RetroFit

import kotlinx.coroutines.experimental.*


class RetroHelp(private val api: YandexApi) {

   /* suspend fun translate(text: String, lang: String) =
            runBlocking {
                launch(CommonPool) {
                    val translateArray = async { api.getTranslate(Const.key, lang, text) }
                    var translateText = ""
                    translateArray.await().text.forEach { translateText += it }
                }.cancelAndJoin()
            }*/


}
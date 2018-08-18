package com.atilladroid.translator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.atilladroid.translator.RetroFit.Const
import com.atilladroid.translator.RetroFit.YandexApi
import kotlinx.coroutines.experimental.*
import timber.log.Timber
import java.net.URL
import java.nio.charset.Charset

class ViewModuleTranslate(val api: YandexApi) : ViewModel() {


    val translateTextLiveData = MutableLiveData<String>()
    lateinit var laguageBefore: String
    lateinit var languageAfter: String


    init {
        languageAfter = "en"
        laguageBefore = "ru"
    }


    fun translate(text: String) {
       /* Thread(){
            Timber.d("Thread is ${Thread.currentThread().name}")
            val translateArray =
                    api.getTranslate(Const.key,
                            "${laguageBefore}-${languageAfter}", text)

            var translateText = ""
            translateArray.text.forEach {translateTextLiveData.value += it }

        }.start()*/

runBlocking {
                launch(CommonPool) {
                    Timber.d("Thread is ${Thread.currentThread().name}")
                    val url = URL("https://translate.yandex.net/retroHelp/v1.5/tr.json/translate?key=${Const.key}&" +
                            "lang=ru-en&text=${text}")
                            .readText(Charset.defaultCharset())
                    Timber.d(url)

                   /* val translateArray =
                            api.getTranslate(Const.key,
                                    "ru-en", text)

                    var translateText = translateArray.await()
                    Timber.d(translateText.toString())*/
                    /*translateArray.text.forEach {translateTextLiveData.value += it }
                    //translateTextLiveData.value = translateText*/
                }
            }
    }

    fun setTextAfter(after: String) {
        languageAfter = after
    }

    fun setTextBefore(before: String) {
        laguageBefore = before
    }
}
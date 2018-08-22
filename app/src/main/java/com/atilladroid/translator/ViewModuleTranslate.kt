package com.atilladroid.translator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.atilladroid.translator.RetroFit.Const
import com.atilladroid.translator.RetroFit.DictionaryApi
import com.atilladroid.translator.RetroFit.DictionaryMy
import com.atilladroid.translator.RetroFit.YandexApi
import kotlinx.coroutines.experimental.*
import timber.log.Timber


class ViewModuleTranslate(val api: YandexApi, val apiDictionary:DictionaryApi) : ViewModel() {


    val dictionaryTextLD=SingleLiveEvent<DictionaryMy>()
    val translateTextLiveData = MutableLiveData<String>()
    val startFragmendLiveData = SingleLiveEvent<ConstantFragment>()
    val mapLanguageLiveData = MutableLiveData<Map<String, String>>()
    var laguageBeforeLD = MutableLiveData<String>()
    var languageAfterLD = MutableLiveData<String>()
    var laguageBefore: String
    var languageAfter: String
    // before = true
    var flagBeforeOrAfter: Boolean = true


    init {
        languageAfter = "ru"
        laguageBefore = "en"
        launch(CommonPool) {

           val a= async { downloadLangs()}

            a.await().apply { setLanguage() }
        }
    }


    fun translate(text: String) {
        runBlocking {
            launch(Unconfined) {
                Timber.d("Thread is ${Thread.currentThread().name}")

                val translateArray =
                        api.getTranslate(Const.key,
                                "${laguageBefore}-${languageAfter}", text)

                var translateText = translateArray.await()
                translateTextLiveData.postValue(translateText.text[0])
                Timber.d("${translateText.text}")

            }
        }
    }

    fun getLanguageName(key: String) =
            mapLanguageLiveData.value!![key]


    fun startFragmentSelect() {
        startFragmendLiveData.value = ConstantFragment.SELECT
    }

    fun startFragmentTranslate() {
        startFragmendLiveData.value = ConstantFragment.TRANSLATE
    }

    fun downloatMoreTranslate(text: String){

        runBlocking {
            launch(CommonPool) {
                val moreGet = apiDictionary.getDictionary(Const.keyDictionary,
                        "${laguageBefore}-${languageAfter}", text,"ru")
                Timber.d("${moreGet.await().def}")
                dictionaryTextLD.postValue(moreGet.await())

            }
        }
    }

    suspend fun downloadLangs() :Boolean{

        /* launch(CommonPool) {*/
        val languageGet = api.getLangs(Const.key, "ru").await()
        // Timber.d(api.getLangs(Const.key, "ru").await().langs.toString())
        mapLanguageLiveData.postValue(languageGet.langs)
        return true

        /*}*/
    }

    fun getNowLanguage(): String {
        return if (flagBeforeOrAfter) {
            laguageBefore
        } else languageAfter
    }


    fun setLanguage(lang: String) {

        if (flagBeforeOrAfter) {
            laguageBefore = lang
            laguageBeforeLD.value = mapLanguageLiveData.value!![lang]

        } else {
            languageAfter = lang
            languageAfterLD.value = mapLanguageLiveData.value!![lang]
        }
        startFragmentTranslate()
    }

    fun setLanguageAfter() {
        flagBeforeOrAfter = false
        startFragmentSelect()
    }

    fun setLangugeBefore() {
        flagBeforeOrAfter = true
        startFragmentSelect()
    }

    fun repear() {
        val a = laguageBefore
        laguageBefore = languageAfter
        languageAfter = a
        setLanguage()
    }

    fun setLanguage() {
        laguageBeforeLD.postValue( mapLanguageLiveData.value!![laguageBefore])
        languageAfterLD.postValue( mapLanguageLiveData.value!![languageAfter])
    }
}

enum class ConstantFragment {
    SELECT,
    TRANSLATE
}

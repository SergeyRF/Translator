package com.atilladroid.translator


import com.atilladroid.translator.RetroFit.DictionaryApi
import com.atilladroid.translator.RetroFit.YandexApi
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext


val module:Module= applicationContext {
    bean { createRetroApi() }
    bean { createDictionaryApi() }
    viewModel { ViewModuleTranslate(get(),get()) }

}

fun createRetroApi():YandexApi {
    return YandexApi.create()
}
fun createDictionaryApi():DictionaryApi{
    return DictionaryApi.create()
}
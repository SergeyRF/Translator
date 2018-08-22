package com.atilladroid.translator


import com.atilladroid.translator.RetroFit.YandexApi
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext


val module:Module= applicationContext {
    bean { createRetroApi() }
    viewModel { ViewModuleTranslate(get()) }

}

fun createRetroApi():YandexApi {
    return YandexApi.create()
}
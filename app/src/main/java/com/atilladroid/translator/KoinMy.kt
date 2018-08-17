package com.atilladroid.translator

import com.atilladroid.translator.RetroFit.YandexApi
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext


val module:Module= applicationContext {
    bean { createRetroApi() }

}

fun createRetroApi():YandexApi {
    return YandexApi.create()
}
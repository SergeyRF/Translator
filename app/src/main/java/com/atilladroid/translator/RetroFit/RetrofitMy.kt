package com.atilladroid.translator.RetroFit


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

//@GET("translate?lang = {lang}&key={key}&text={text}")


interface YandexApi {


    @POST("translate")
    fun getTranslate(@Query("key") key: String,
                     @Query("lang") lang: String,
                     @Query("text") text: String

    ): Deferred<Translate>

    @POST("getLangs")
    fun getLangs(@Query("key") key: String,
                 @Query("ui") ui: String): Deferred<Langs>

    companion object {
        fun create(): YandexApi {
            val retrofit = Retrofit.Builder()
                    // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl("https://translate.yandex.net/retroHelp/v1.5/tr.json/")
                    .baseUrl("https://translate.yandex.net/api/v1.5/tr.json/")
                    .build()
            return retrofit.create(YandexApi::class.java)
        }
    }
}

interface DictionaryApi {

    @POST("lookup")
    fun getDictionary(
            @Query("key") key: String,
            @Query("lang") lang: String,
            @Query("text") text:String,
            @Query("ui") ui: String): Deferred<DictionaryMy>

    companion object {
        fun create(): DictionaryApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://dictionary.yandex.net/api/v1/dicservice.json/")
                    .build()
            return retrofit.create(DictionaryApi::class.java)
        }
    }
}
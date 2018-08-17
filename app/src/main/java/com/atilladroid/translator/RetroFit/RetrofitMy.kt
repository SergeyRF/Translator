package com.atilladroid.translator.RetroFit

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

//@GET("translate?lang = {lang}&key={key}&text={text}")


interface YandexApi{


    @POST("translate")
    fun getTranslate(@Query("key")key:String ,
                     @Query("lang")lang:String,
                     @Query("text")text:String

                      ):Observable<Translate>

    @POST("getLangs")
    fun getLangs(@Query("key")key:String,
                 @Query("ui")ui:String):Observable<Langs>

    companion object {
        fun create(): YandexApi {
            val retrofit=Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://translate.yandex.net/api/v1.5/tr.json/")
                    .build()
            return retrofit.create(YandexApi::class.java)
        }
    }
}
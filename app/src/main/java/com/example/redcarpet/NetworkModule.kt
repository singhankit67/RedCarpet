package com.example.redcarpet

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
//module is a class that provides information to the hilt about how to provide an instance of a class we don't own
@InstallIn(ApplicationComponent::class) // @InsatallIn annotation and we will pass ApplicationComponent here because we want the NetworkModule to be available for us for application scope
object NetworkModule {
    @Singleton
    //we use @Provide annotation inside the module class to tell hilt to instantiate these objects for us
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Singleton
    @Provides
    fun provideCallFactory(httpLoggingInterceptor: HttpLoggingInterceptor): Call.Factory{
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
    @Singleton
    @Provides
    fun provideMoshi() : Moshi{
        return Moshi.Builder().build()
    }
    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory{
        return MoshiConverterFactory.create()
    }
    //To hook it all up and parse the json to the data class you need to create a Moshi object, create the adapter instance and then pass the JSON to the adapter:

    @Singleton
    @Provides
    fun provideRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory{
        return RxJava3CallAdapterFactory.create()
    }
    @Singleton
    @Provides
    fun provideBaseUrl(): String{
        return "https://newsapi.org/v2/"
    }
    @Singleton
    @Provides
    fun provideRetrofit(httpLoggingInterceptor:Call.Factory,moshiConverterFactory: MoshiConverterFactory,rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,baseUrl:String):Retrofit{
        return Retrofit.Builder()
            .callFactory(httpLoggingInterceptor)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .baseUrl(baseUrl)
            .build()
    }
    @Singleton
    //In the module, we will provide a method to get the newsService object. Create a method provideNewsService of NewsService return type and annotate it with @Provide annotation.
    @Provides
    fun provideNewsService(retrofit: Retrofit):NewsService{
        return retrofit.create(NewsService::class.java)
    }

}

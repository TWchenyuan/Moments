package com.thoughtworks.moments.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://xianmobilelab.gitlab.io/moments-data/"
fun provideRetrofit(): Retrofit = Retrofit.Builder()
  .baseUrl(BASE_URL)
  .addConverterFactory(GsonConverterFactory.create())
  .build()

fun provideAccountAPI(retrofit: Retrofit): AccountAPI = retrofit.create(AccountAPI::class.java)

fun provideMomentAPI(retrofit: Retrofit): MomentAPI = retrofit.create(MomentAPI::class.java)

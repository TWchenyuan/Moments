package com.thoughtworks.moments.di

import com.thoughtworks.moments.data.network.provideAccountAPI
import com.thoughtworks.moments.data.network.provideMomentAPI
import com.thoughtworks.moments.data.network.provideRetrofit
import com.thoughtworks.moments.data.repository.AccountRepository
import com.thoughtworks.moments.data.repository.AccountRepositoryImpl
import com.thoughtworks.moments.data.repository.MomentRepository
import com.thoughtworks.moments.data.repository.MomentRepositoryImpl
import com.thoughtworks.moments.ui.component.moments.MomentsPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
  singleOf(::provideRetrofit)
  singleOf(::provideAccountAPI)
  singleOf(::provideMomentAPI)
}
val appModule = module {
  singleOf(::AccountRepositoryImpl) { bind<AccountRepository>() }
  singleOf(::MomentRepositoryImpl) { bind<MomentRepository>() }
  viewModelOf(::MomentsPageViewModel)
}

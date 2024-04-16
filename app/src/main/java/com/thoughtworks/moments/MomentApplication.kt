package com.thoughtworks.moments

import android.app.Application
import com.thoughtworks.moments.di.appModule
import com.thoughtworks.moments.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MomentApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@MomentApplication)
      androidLogger()
      modules(appModule, networkModule)
    }
  }
}

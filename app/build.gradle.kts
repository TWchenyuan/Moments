plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsKotlinAndroid)
  alias(libs.plugins.jlleitschuhGradleKtlint)
  kotlin("kapt")
}

android {
  namespace = "com.thoughtworks.moments"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.thoughtworks.moments"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

ktlint {
  android = true
  verbose = true
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.lifecycle.runtime.compose)
  // koin
  implementation(platform(libs.koin.bom))
  implementation(libs.koin.androidx.compose)
  // koil compose
  implementation(libs.coil.compose)

  implementation(libs.converter.gson)
  implementation(libs.retrofit)

  // room
  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.room.ktx)
  implementation(libs.androidx.paging.runtime.ktx)
  implementation(libs.androidx.room.paging)
  annotationProcessor(libs.androidx.room.compiler)
  kapt(libs.androidx.room.compiler)

  implementation(libs.androidx.paging.compose)
  implementation(libs.androidx.paging.runtime.ktx)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)

  testImplementation(libs.koin.test.junit4)
  testImplementation(libs.truth)

  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}

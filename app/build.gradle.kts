plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp.gradle.plugin)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.hibidroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hibidroid"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


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
    buildFeatures  {
        dataBinding = true
        compose= true
    }
}

dependencies {
    //Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.common.java8)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.androidx.lifecycle.process)
    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    //Livedata
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.livedata.reactivestreams.ktx)
    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.rxjava3)
    //Okhttp3
    implementation(libs.okhttp)

    //Gson
    implementation (libs.converter.gson)

    //compose
    implementation("androidx.activity:activity-compose:1.9.0")
//    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.compose.material:material-icons-extended")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.2.0")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.0")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
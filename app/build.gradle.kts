plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    alias(libs.plugins.ktlint)
    id(libs.plugins.dagger.hilt.android.get().pluginId)
    id(libs.plugins.devtools.get().pluginId)
    kotlin(libs.plugins.serialization.get().pluginId)
}

android {
    namespace = "com.fady.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fady.newsapp"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField(
                "String", "API_KEY", "\"f4c67f9ea60f4b7b8f1e82db2c829ac6\""
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.bundles.compose)
    // Temporarily use this version to fix google's navigation animation transition bug
    implementation(libs.landscapist.coil)
    implementation(libs.androidx.material.icons.extended.android)
    implementation(libs.material)
    // serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.hilt)
    implementation(libs.hilt.android.gradle.plugin)
    implementation(libs.androidx.navigation.compose)

    // bottom navigation bar
    implementation(libs.compose.ui)
    implementation("androidx.compose.material:material:1.6.1")
    implementation(libs.lifecycle.runtime.compose)
    ksp(libs.hilt.compiler)

    // testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.lokalise) {
        this.isTransitive = true
    }
    implementation(libs.splashscreen)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
}
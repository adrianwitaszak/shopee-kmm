plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
}

android {
    compileSdk = AndroidConfig.compileSdk
    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Android.composeUi
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        freeCompilerArgs = listOf(
            "-Xskip-prerelease-check",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=com.apollographql.apollo3.annotations.ApolloExperimental",
        )
    }
}

dependencies {
    implementation(project(Modules.SHARED))

    // Needed to run Compose Motion Layout Inspector
    implementation("androidx.constraintlayout:constraintlayout-core:1.0.2")

    with(Android) {
        implementation(coil)
        implementation(paging)
        implementation(coreKtx)
        implementation(appcompat)
        implementation(lifecycle)
        implementation(composeUi)
        implementation(composePaging)
        implementation(composeUiUtil)
        implementation(composeTooling)
        implementation(composeRuntime)
        implementation(composeActivity)
        implementation(composeMaterial)
        implementation(composeMaterial3)
        implementation(composeAnimation)
        implementation(composeNavigation)
        implementation(composeUiGraphics)
        implementation(accompanistInsets)
        implementation(composeFoundation)
        implementation(composeAnimationCore)
        implementation(composeMaterialIcons)
        implementation(composeConstrainLayout)
        implementation(accompanistSwipeRefresh)
        implementation(composeFoundationLayout)
        implementation(accompanistNavigationAnimation)
        implementation(Android.accompanistSystemUiController)
    }
}
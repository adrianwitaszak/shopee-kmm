object Build {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradleBuildTool}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlinVersion}"
    const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${Versions.Kotlin.sqlDelight}"
}

object Kotlin {
    const val kotlin =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin.kotlinVersion}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    const val coil = "io.coil-kt:coil:${Versions.Kotlin.coil}"
}

object SqlDelight {
    const val runtime = "com.squareup.sqldelight:runtime:${Versions.Kotlin.sqlDelight}"
    const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.Kotlin.sqlDelight}"
    const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.Kotlin.sqlDelight}"
}

object Apollo {
    const val runtime = "com.apollographql.apollo3:apollo-runtime:${Versions.Apollo.runtime}"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.Android.core}"
    const val material = "com.google.android.material:material:${Versions.Android.material}"

    // Lifecycle
    const val lifecycle =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycle}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecycle}"
    const val lifecycleSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.Android.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycle}"

    // Compose
    const val composeCompiler = "androidx.compose.compiler:compiler:${Versions.Android.compose}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.Android.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.Android.compose}"
    const val composeMaterialIcons =
        "androidx.compose.material:material-icons-extended:${Versions.Android.compose}"
    const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.Android.compose}"
    const val composePreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.Android.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.Android.compose}"
    const val composeFoundation =
        "androidx.compose.foundation:foundation:${Versions.Android.compose}"
    const val composeFoundationLayout =
        "androidx.compose.foundation:foundation-layout:${Versions.Android.compose}"
    const val composeAnimation =
        "androidx.compose.animation:animation:${Versions.Android.compose}"
    const val composeAnimationCore =
        "androidx.compose.animation:animation-core:${Versions.Android.compose}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.Android.compose}"
    const val composeMaterial3 =
        "androidx.compose.material3:material3:${Versions.Android.material3}"
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.Android.navigation}"
    const val composeActivity =
        "androidx.activity:activity-compose:${Versions.Android.activityCompose}"
    const val composePaging = "androidx.paging:paging-compose:${Versions.Android.pagingCompose}"

    // Work
    const val workManager = "androidx.work:work-runtime-ktx:${Versions.Android.workManager}"

    // Helpers
    const val paging = "androidx.paging:paging-common-ktx:${Versions.Android.paging}"
    const val timber = "com.jakewharton.timber:timber:${Versions.Android.timber}"
    const val accompanistInsets =
        "com.google.accompanist:accompanist-insets:${Versions.Android.accompanist}"
    const val accompanistPager =
        "com.google.accompanist:accompanist-pager:${Versions.Android.accompanist}"
    const val accompanistPagerIndicators =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.Android.accompanist}"
    const val accompanistPlaceholder =
        "com.google.accompanist:accompanist-placeholder-material:${Versions.Android.accompanist}"
    const val accompanistSwipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.Android.accompanist}"
    const val accompanistNavigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.Android.accompanist}"
    const val accompanistPermissions =
        "com.google.accompanist:accompanist-permissions:${Versions.Android.accompanist}"
    const val systemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.Android.accompanist}"
}

object AndroidTestDependencies {
    // Core library
    const val junit4Ktx = "androidx.test.ext:junit:${Versions.Android.junit4Ktx}"
    const val junit4 = "junit:junit:${Versions.Android.junit4}"
    const val test_core = "androidx.test:core:${Versions.Android.test_core}"
    const val arch_core = "androidx.arch.core:core-testing:${Versions.Android.arch_core}"

    // Espresso dependencies
    const val espresso_core =
        "androidx.test.espresso:espresso-core:${Versions.Android.espresso_core}"

    // Coroutines
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
    const val turbine = "app.cash.turbine:turbine:${Versions.Android.turbine}"

    // Assertions
    const val kotest = "io.kotest:kotest-assertions-core:${Versions.Android.kotest}"

    // JUnit5
    const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.Android.junit5}"
    const val jupiterEngineRuntimeOnly =
        "org.junit.jupiter:junit-jupiter-engine:${Versions.Android.junit5}"
    const val jupiterParams =
        "org.junit.jupiter:junit-jupiter-params:${Versions.Android.junit5}"
    const val jupiterVintageRuntimeOnly =
        "org.junit.vintage:junit-vintage-engine:${Versions.Android.junit5}"

    const val test_runner = "androidx.test:runner:${Versions.Android.test_core}"
    const val rules = "androidx.test:rules:${Versions.Android.rules}"

    const val kotlin_junit =
        "org.jetbrains.kotlin:kotlin-test-junit:${Versions.Kotlin.kotlinVersion}"
    const val truth = "com.google.truth:truth:${Versions.Android.GoogleTruth}"
    const val GoogleTruth = "androidx.test.ext:truth:${Versions.Android.XTruth}"

    const val mockk = "io.mockk:mockk:${Versions.Android.mockk}"
    const val mockito = "org.mockito.kotlin:mockito-kotlin:${Versions.Android.mockito}"

    // Work
    const val work = "androidx.work:work-testing:${Versions.Android.workManager}"
}

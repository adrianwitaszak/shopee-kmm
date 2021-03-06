object Build {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradleBuildTool}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlinVersion}"
    const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${Versions.Kotlin.sqlDelight}"
    const val serialization =
        "org.jetbrains.kotlin:kotlin-serialization:${Versions.Kotlin.kotlinVersion}"
    const val shadow = "gradle.plugin.com.github.johnrengelman:shadow:${Versions.shadow}"
}

object Kotlin {
    const val stdlib =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin.kotlinVersion}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    const val coil = "io.coil-kt:coil:${Versions.Kotlin.coil}"
    const val kermit =
        "co.touchlab:kermit:${Versions.Kotlin.kermit}" // Logger - https://github.com/touchlab/Kermit
    const val kamel =
        "com.alialbaali.kamel:kamel-image:${Versions.Kotlin.kamel}" // Image loading - https://github.com/alialbaali/Kamel
    const val pixel =
        "io.github.mmobin789.pixel:pixel:${Versions.Kotlin.pixel}" // Image loading - https://github.com/mmobin789/pixel
    const val apollo = "com.apollographql.apollo3:apollo-runtime:${Versions.Kotlin.apollo}"
    const val decompose = "com.arkivanov.decompose:decompose:${Versions.Kotlin.decompose}"
    const val decomposeComposeJetbrains =
        "com.arkivanov.decompose:extensions-compose-jetbrains:${Versions.Kotlin.decompose}"
    const val mviKotlin = "com.arkivanov.mvikotlin:mvikotlin:${Versions.Kotlin.mviKotlin}"
}

object Ktor {
    const val core = "io.ktor:ktor-server-core:${Versions.Backend.ktorVersion}"
    const val netty = "io.ktor:ktor-server-netty:${Versions.Backend.ktorVersion}"
    const val gson = "io.ktor:ktor-gson:${Versions.Backend.ktorVersion}"
    const val auth = "io.ktor:ktor-auth:${Versions.Backend.ktorVersion}"
    const val jwt = "io.ktor:ktor-auth-jwt:${Versions.Backend.ktorVersion}"
    const val jackson = "io.ktor:ktor-jackson:${Versions.Backend.ktorVersion}"
    const val locations = "io.ktor:ktor-locations:${Versions.Backend.ktorVersion}"
    const val hostCommon = "io.ktor:ktor-server-host-common:${Versions.Backend.ktorVersion}"

    const val test = "io.ktor:ktor-server-tests:${Versions.Backend.ktorVersion}"
}

object Backend {
    const val logback = "ch.qos.logback:logback-classic:${Versions.Backend.logbackVersion}"
    const val kMongo = "org.litote.kmongo:kmongo:${Versions.Backend.kMongoVersion}"
    const val kMongoCoroutine = "org.litote.kmongo:kmongo-coroutine:${Versions.Backend.kMongoVersion}"
    const val kGraphQL = "com.apurebase:kgraphql:${Versions.Backend.kGraphQLVersion}"
    const val kGraphQLKtor = "com.apurebase:kgraphql-ktor:${Versions.Backend.kGraphQLVersion}"
    const val bCrypt = "at.favre.lib:bcrypt:${Versions.Backend.bcryptVersion}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.Kotlin.koin}"
    const val test = "io.insert-koin:koin-test:${Versions.Kotlin.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.Kotlin.koin}"
    const val compose = "io.insert-koin:koin-androidx-compose:${Versions.Kotlin.koin}"
    const val ktor = "io.insert-koin:koin-ktor:${Versions.Backend.koinVersion}"
}

object SqlDelight {
    const val runtime = "com.squareup.sqldelight:runtime:${Versions.Kotlin.sqlDelight}"
    const val coroutineExtensions = "com.squareup.sqldelight:coroutines-extensions:${Versions.Kotlin.sqlDelight}"
    const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.Kotlin.sqlDelight}"
    const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.Kotlin.sqlDelight}"
    const val nativeDriverMacos = "com.squareup.sqldelight:native-driver-macosx64:${Versions.Kotlin.sqlDelight}"
    const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.Kotlin.sqlDelight}"
    const val jsDriver = "com.squareup.sqldelight:sqljs-driver:${Versions.Kotlin.sqlDelight}"
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

    // Compose
    const val composeCompiler = "androidx.compose.compiler:compiler:${Versions.Android.composeUi}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.Android.composeRuntime}"
    const val composeConstrainLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.Android.composeConstrainLayout}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.Android.composeUi}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${Versions.Android.composeUi}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.Android.composeUi}"
    const val composeMaterialIcons =
        "androidx.compose.material:material-icons-extended:${Versions.Android.composeUi}"
    const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.Android.composeUi}"
    const val composePreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.Android.composeUi}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.Android.composeUi}"
    const val composeFoundation =
        "androidx.compose.foundation:foundation:${Versions.Android.composeUi}"
    const val composeFoundationLayout =
        "androidx.compose.foundation:foundation-layout:${Versions.Android.composeUi}"
    const val composeAnimation =
        "androidx.compose.animation:animation:${Versions.Android.composeUi}"
    const val composeAnimationCore =
        "androidx.compose.animation:animation-core:${Versions.Android.composeUi}"
    const val composeMaterial3 =
        "androidx.compose.material3:material3:${Versions.Android.material3}"
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.Android.navigation}"
    const val composeActivity =
        "androidx.activity:activity-compose:${Versions.Android.activityCompose}"
    const val composePaging = "androidx.paging:paging-compose:${Versions.Android.pagingCompose}"

    // Helpers
    const val coil = "io.coil-kt:coil-compose:${Versions.Android.coil}"
    const val paging = "androidx.paging:paging-common-ktx:${Versions.Android.paging}"
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
    const val accompanistSystemUiController =
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

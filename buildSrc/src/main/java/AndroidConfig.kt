import org.gradle.api.JavaVersion

object AndroidConfig {
    const val compileSdk = 31
    const val applicationId = "com.adwi.shoppe.android"
    const val minSdk = 23
    const val targetSdk = compileSdk
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val javaVersion = "11"
    val javaVersionName = JavaVersion.VERSION_11
}

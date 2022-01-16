package com.adwi.shoppe

//import com.adwi.shoppe.di.modules
import android.app.Application
import com.adwi.shoppe.di.appModule
import com.apollographql.apollo3.annotations.ApolloExperimental
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tools.LinkServer

@ApolloExperimental
@ExperimentalCoroutinesApi
class ShoppeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LinkServer().start()

        startKoin {
            androidContext(this@ShoppeApplication)
            modules(appModule)
        }
    }
}
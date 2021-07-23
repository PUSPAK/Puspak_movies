package org.demo.lowsproject
import android.app.Application
import org.demo.lowsproject.ui.di.tvMazeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TvMazeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@TvMazeApp)
            modules(tvMazeModule)
        }
    }
}
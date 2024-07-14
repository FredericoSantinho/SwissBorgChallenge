package neuro.swissborg

import android.app.Application
import android.content.Context
import neuro.swissborg.di.daoModule
import neuro.swissborg.di.networkModule
import neuro.swissborg.di.platformModule
import neuro.swissborg.di.repositoryModule
import neuro.swissborg.di.useCaseModule
import neuro.swissborg.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.binds
import org.koin.dsl.module

class App : Application() {
	override fun onCreate() {
		super.onCreate()

		startKoin {
			module {
				single { this@App } binds arrayOf(Context::class, Application::class)
			}
			androidContext(this@App)
			modules(
				platformModule,
				networkModule,
				viewModelModule,
				repositoryModule,
				daoModule,
				useCaseModule
			)
		}
	}
}
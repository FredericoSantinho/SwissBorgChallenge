package di.modules

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatchersModule = module {
	single<CoroutineDispatcher> { Dispatchers.Main }
}
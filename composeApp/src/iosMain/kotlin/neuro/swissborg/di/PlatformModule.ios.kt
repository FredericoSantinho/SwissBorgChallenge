package neuro.swissborg.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import neuro.swissborg.data.SwissBorgChallengeDatabase
import neuro.swissborg.data.instantiateImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

actual val platformModule: Module = module {
	single<SwissBorgChallengeDatabase> { getDatabase() }
}

private fun getDatabase(): SwissBorgChallengeDatabase {
	val dbFile = NSHomeDirectory() + "/swissborg_challenge.db"
	return Room.databaseBuilder<SwissBorgChallengeDatabase>(
		name = dbFile,
		factory = { SwissBorgChallengeDatabase::class.instantiateImpl() }
	).setDriver(BundledSQLiteDriver())
		.fallbackToDestructiveMigration(true)
		.build()
}

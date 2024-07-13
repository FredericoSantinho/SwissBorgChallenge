package neuro.swissborg.di

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import neuro.swissborg.data.SwissBorgChallengeDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
	single<SwissBorgChallengeDatabase> { getDatabase(get()) }
}

private fun getDatabase(context: Context): SwissBorgChallengeDatabase {
	val dbFile = context.getDatabasePath("swissborg_challenge.db")
	return Room.databaseBuilder<SwissBorgChallengeDatabase>(
		context = context.applicationContext,
		name = dbFile.absolutePath
	)
		.setDriver(BundledSQLiteDriver())
		.build()
}
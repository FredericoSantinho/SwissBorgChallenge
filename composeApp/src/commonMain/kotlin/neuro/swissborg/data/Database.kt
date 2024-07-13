package neuro.swissborg.data

import androidx.room.Database
import androidx.room.RoomDatabase
import neuro.swissborg.data.dao.CoinDetailsDao
import neuro.swissborg.data.model.database.RoomCoinDetails

@Database(
	entities = [RoomCoinDetails::class],
	version = 1
)
abstract class SwissBorgChallengeDatabase : RoomDatabase(), DB {
	abstract fun coinDetailsDao(): CoinDetailsDao

	override fun clearAllTables() {
		super.clearAllTables()
	}
}

// FIXME: Added a hack to resolve below issue:
// Class 'SwissBorgDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
	fun clearAllTables() {}
}
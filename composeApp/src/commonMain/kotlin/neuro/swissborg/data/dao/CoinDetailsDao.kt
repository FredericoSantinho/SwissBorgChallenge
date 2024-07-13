package neuro.swissborg.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import neuro.swissborg.data.model.database.RoomCoinDetails

@Dao
interface CoinDetailsDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertCoinsDetails(roomCoinsTickers: List<RoomCoinDetails>)

	@Query("SELECT * FROM coin_details_table")
	fun observeCoinsDetails(): Flow<List<RoomCoinDetails>>
}
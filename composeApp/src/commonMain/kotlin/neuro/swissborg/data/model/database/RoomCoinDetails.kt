package neuro.swissborg.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "coin_details_table"
)
data class RoomCoinDetails(
	val name: String,
	@PrimaryKey
	val symbol: String,
	val iconUrl: String,
	val price: Double,
	val priceChangePercent: Double,
	val priceChangeColor: Long,
)

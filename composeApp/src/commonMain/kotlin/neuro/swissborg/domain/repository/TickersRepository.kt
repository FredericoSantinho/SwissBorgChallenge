package neuro.swissborg.domain.repository

import neuro.swissborg.data.model.TickerDto

interface TickersRepository {
	suspend fun getTickers(symbols: List<String>): List<TickerDto>
}
package neuro.swissborg.domain.repository

import neuro.swissborg.domain.entity.Ticker

interface TickersRepository {
	suspend fun getTickers(symbols: List<String>): List<Ticker>
}
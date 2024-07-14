package neuro.swissborg.domain.repository

import neuro.swissborg.domain.entity.Ticker

interface TickersRepository {
	suspend fun getTickers(symbolPairs: List<String>): List<Ticker>
}
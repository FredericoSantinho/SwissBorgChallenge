package neuro.swissborg.domain.repository

import neuro.swissborg.data.model.SymbolDto

interface SymbolsRepository {
	suspend fun getSymbols(): List<SymbolDto>
}
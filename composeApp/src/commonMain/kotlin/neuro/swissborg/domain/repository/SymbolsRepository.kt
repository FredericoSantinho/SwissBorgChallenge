package neuro.swissborg.domain.repository

import neuro.swissborg.domain.entity.Symbol

interface SymbolsRepository {
	suspend fun getSymbols(): List<Symbol>
}
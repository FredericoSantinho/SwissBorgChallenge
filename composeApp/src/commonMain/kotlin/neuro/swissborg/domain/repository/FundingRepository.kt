package neuro.swissborg.domain.repository

import neuro.swissborg.domain.entity.Funding

interface FundingRepository {
	suspend fun getFunding(symbols: List<String>): List<Funding>
}
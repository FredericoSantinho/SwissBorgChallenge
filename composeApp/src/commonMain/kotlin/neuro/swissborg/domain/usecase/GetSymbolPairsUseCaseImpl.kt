package neuro.swissborg.domain.usecase

class GetSymbolPairsUseCaseImpl : GetSymbolPairsUseCase {
	override fun execute(): List<String> {
		val symbolsQuery =
			"?symbols=tBTCUSD,tETHUSD,tCHSB:USD,tLTCUSD,tXRPUSD,tDSHUSD,tRRTUSD,tEOSUSD,tSANUSD,tDATUSD,tSNTUSD,tDOGE:USD,tLUNA:USD,tMATIC:USD,tNEXO:USD,tOCEAN:USD,tBEST:USD,tAAVE:USD,tPLUUSD,tFILUSD"
				.replace("?symbols=", "")
		val split = symbolsQuery.split(",").map { it.substring(1) }

		return split
	}
}
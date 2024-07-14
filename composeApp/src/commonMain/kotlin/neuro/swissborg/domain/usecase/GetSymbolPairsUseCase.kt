package neuro.swissborg.domain.usecase

interface GetSymbolPairsUseCase {
	fun execute(): List<String>
}
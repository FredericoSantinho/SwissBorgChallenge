package neuro.swissborg.presentation.viewmodel

sealed interface Message {
	data class Literal(val message: String) : Message
	data object NoConnectivity : Message
}
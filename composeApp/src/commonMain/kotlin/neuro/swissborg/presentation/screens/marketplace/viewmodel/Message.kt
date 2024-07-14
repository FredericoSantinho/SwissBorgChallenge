package neuro.swissborg.presentation.screens.marketplace.viewmodel

sealed interface Message {
	data class Literal(val message: String) : Message
	data object NoConnectivity : Message
}
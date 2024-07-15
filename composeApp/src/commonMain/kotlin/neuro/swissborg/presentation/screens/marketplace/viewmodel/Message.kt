package neuro.swissborg.presentation.screens.marketplace.viewmodel

sealed interface Message {
	data object NoConnectivity : Message
	data object DatabaseError : Message
	data object FetchError : Message
}
package neuro.swissborg.presentation.util.connection

import kotlinx.coroutines.flow.Flow

interface ConnectionObserver {
	fun observeHasConnection(): Flow<Boolean>
}
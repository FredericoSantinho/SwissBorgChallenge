package neuro.swissborg.presentation.util.connection

import dev.tmapps.konnection.Konnection
import kotlinx.coroutines.flow.Flow

class ConnectionObserverImpl(private val konnection: Konnection = Konnection.instance) :
	ConnectionObserver {
	override fun observeHasConnection(): Flow<Boolean> {
		return konnection.observeHasConnection()
	}
}
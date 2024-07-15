package neuro.swissborg.data.repository.utils

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

fun getHttpClient(mockEngine: MockEngine) =
	HttpClient(mockEngine) {
		install(ContentNegotiation) {
			json()
		}
	}
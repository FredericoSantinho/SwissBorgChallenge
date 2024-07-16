package neuro.swissborg.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val networkModule = module {
	factory {
		HttpClient(CIO) {
			defaultRequest {
				url(BITFINEX_BASE_URL)
			}

			install(ContentNegotiation) {
				json()
			}
		}
	}
}

private const val BITFINEX_BASE_URL = "https://api-pub.bitfinex.com"
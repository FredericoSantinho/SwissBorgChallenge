package neuro.swissborg.di

import neuro.swissborg.data.SwissBorgChallengeDatabase
import neuro.swissborg.data.dao.CoinDetailsDao
import org.koin.dsl.module

val daoModule = module {
	factory<CoinDetailsDao> { get<SwissBorgChallengeDatabase>().coinDetailsDao() }
}
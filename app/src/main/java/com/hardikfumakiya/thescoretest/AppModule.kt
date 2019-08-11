package com.hardikfumakiya.thescoretest

import com.hardikfumakiya.thescoretest.network.ApiClient
import com.hardikfumakiya.thescoretest.network.ApiInterfaceImpl
import com.hardikfumakiya.thescoretest.network.ApiWrapper
import com.hardikfumakiya.thescoretest.utils.PermissionUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: ScoreApp) {

    @Provides
    @Singleton
    fun provideApplication(): ScoreApp {
        return app
    }

    @Provides
	@Singleton
    fun provideApiWrapper(): ApiWrapper {
        return ApiWrapper()
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(app)
    }

    @Provides
    @Singleton
    fun providePermissionUtil(): PermissionUtil {
        return PermissionUtil()
    }

	@Provides
	@Singleton
	fun provideApiInterfaceImpl(): ApiInterfaceImpl {
		return  ApiInterfaceImpl(ApiClient.API_SCORE)
	}

}

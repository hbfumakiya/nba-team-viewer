package com.hardikfumakiya.thescoretest


import com.hardikfumakiya.thescoretest.network.ApiInterfaceImpl
import com.hardikfumakiya.thescoretest.network.ApiScore
import com.hardikfumakiya.thescoretest.network.ApiWrapper
import com.hardikfumakiya.thescoretest.utils.PermissionUtil
import com.hardikfumakiya.thescoretest.viewmodels.TeamViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Hardik Fumakiya this is singleton app component class where injection of objects is being performed
 * **/
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: ScoreApp)

    fun inject(permissionUtil: PermissionUtil)

    fun inject(apiInterfaceImpl: ApiInterfaceImpl)

    fun inject(apiScore: ApiScore)

    fun inject(apiWrapper: ApiWrapper)

    fun inject(teamViewModel: TeamViewModel)

    fun inject(viewModelFactory: ViewModelFactory)

}
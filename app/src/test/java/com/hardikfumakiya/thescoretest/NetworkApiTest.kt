package com.hardikfumakiya.thescoretest

import androidx.lifecycle.MutableLiveData
import com.hardikfumakiya.thescoretest.model.Player
import com.hardikfumakiya.thescoretest.model.Team
import com.hardikfumakiya.thescoretest.network.ApiInterfaceImpl
import com.hardikfumakiya.thescoretest.network.ApiScore
import com.hardikfumakiya.thescoretest.network.ApiWrapper
import com.hardikfumakiya.thescoretest.network.InterfaceImpl
import com.hardikfumakiya.thescoretest.sorting.ListSortViewModel
import com.hardikfumakiya.thescoretest.utils.NetworkUtil
import com.hardikfumakiya.thescoretest.viewmodels.TeamViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.mockito.Mockito.`when`


/**
 * Created by Hardik Fumakiya this test class for network call on data
 * **/
@RunWith(RobolectricTestRunner::class)
class NetworkApiTest {
    lateinit var teamViewModel: TeamViewModel

    lateinit var listSortViewModel: ListSortViewModel

    var result = MutableLiveData<List<Team>>()

    @Mock
    lateinit var  apiScore: ApiScore

    @InjectMocks
    lateinit var interfaceImpl: ApiInterfaceImpl


    @Rule
    lateinit var rule :TestSchedulerRule
    lateinit var apiWrapper: ApiWrapper
    @Before
    fun initViewModel(){
        MockitoAnnotations.initMocks(this)
        rule = TestSchedulerRule()
        teamViewModel= TeamViewModel(RuntimeEnvironment.application)
        apiWrapper = ApiWrapper()

        listSortViewModel= ListSortViewModel(RuntimeEnvironment.application)


    }

    @Test
    fun isConnectNetwork(){
        //this will be succeed only when connected to internet
        Assert.assertTrue(NetworkUtil.isConnected(RuntimeEnvironment.application))
    }

    @Test
    fun isDataReceived(){
        `when`(interfaceImpl.getTeamList()).thenReturn(Single.just(listOf(
            Team(1,"Abc",12,10, listOf(Player("Alpha1","Romeo1","PF",4))),
            Team(2,"Pqr",10,14, listOf(Player("Alpha2","Romeo2","PG",5))),
            Team(3,"Xyz",19,18, listOf(Player("Alpha3","Romeo3","FC",6)))
        )))
        val testObserver = apiScore.getTeamList().test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertNoErrors()
            .assertValue { t: List<Team> -> t.size == 3 }


        teamViewModel.getTeamList()


    }

    @Test
    fun isDataRealReceived(){
        val testObserver = apiWrapper.getTeamList().test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertNoErrors()
            .assertValue { t: List<Team> -> t.size == 30 }


        teamViewModel.getTeamList()


    }
}
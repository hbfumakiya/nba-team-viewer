package com.hardikfumakiya.thescoretest

import androidx.lifecycle.MutableLiveData
import com.hardikfumakiya.thescoretest.model.Player
import com.hardikfumakiya.thescoretest.model.Team
import com.hardikfumakiya.thescoretest.sorting.ListSortOrder
import com.hardikfumakiya.thescoretest.sorting.ListSortViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by Hardik Fumakiya this test class for sorting and validation on data
 * **/
@RunWith(RobolectricTestRunner::class)
class ListSortViewModelTest {
    lateinit var listSortViewModel: ListSortViewModel

    lateinit var listOfTeam:MutableList<Team>

    var result = MutableLiveData<MutableList<Team>>()

    @Before
    fun initViewModel(){
        listSortViewModel= ListSortViewModel(RuntimeEnvironment.application)
        listOfTeam= mutableListOf(
            Team(1,"Abc",12,10, listOf(Player("Alpha1","Romeo1","PF",4))),
            Team(2,"Pqr",10,14, listOf(Player("Alpha2","Romeo2","PG",5))),
            Team(3,"Xyz",19,18, listOf(Player("Alpha3","Romeo3","FC",6))))
        listSortViewModel.result=  result
    }

    /**
     * validation on team data
     * **/
    @Test
    fun isValidData(){
        var teams=mutableListOf(Team(1,"",12,10, listOf())) //empty team name
        Assert.assertFalse(listSortViewModel.isTeamValid(teams))

        teams=mutableListOf(Team(1,"Abc",12,10, listOf())) //empty team players
        Assert.assertFalse(listSortViewModel.isTeamValid(teams))

        //team with negative win
        teams=mutableListOf(Team(1,"Abc",-1,10, listOf(Player("Alpha","Romeo","DF",4))))
        Assert.assertFalse(listSortViewModel.isTeamValid(teams))

        //team with negative loss
        teams=mutableListOf(Team(1,"Abc",12,-1, listOf(Player("Alpha","Romeo","DF",4))))
        Assert.assertFalse(listSortViewModel.isTeamValid(teams))

        //team with negative team id
        teams=mutableListOf(Team(-1,"Abc",12,10, listOf(Player("Alpha","Romeo","DF",4))))
        Assert.assertFalse(listSortViewModel.isTeamValid(teams))

        //team with correct data
        teams=mutableListOf(Team(1,"Abc",12,10, listOf(Player("Alpha","Romeo","DF",4))))
        Assert.assertTrue(listSortViewModel.isTeamValid(teams))
    }

    /**
     * test sort by name asc
     * **/
    @Test
    fun isSortbyNameASC(){

        val expectedList = mutableListOf<Team>()
        expectedList.add(Team(1,"Abc",12,10, listOf(Player("Alpha1","Romeo1","PF",4))))
        expectedList.add(Team(2,"Pqr",10,14, listOf(Player("Alpha2","Romeo2","PG",5))))
        expectedList.add(Team(3,"Xyz",19,18, listOf(Player("Alpha3","Romeo3","FC",6))))
        listSortViewModel.getTeamSortbyName(ListSortOrder.ASC, listOfTeam)

        Assert.assertEquals(listSortViewModel.result.value, expectedList)

    }

    /**
     * test sort by name desc
     * **/
    @Test
    fun isSortbyNameDESC(){
        val expectedList = mutableListOf<Team>()
        expectedList.add(Team(3,"Xyz",19,18, listOf(Player("Alpha3","Romeo3","FC",6))))
        expectedList.add(Team(2,"Pqr",10,14, listOf(Player("Alpha2","Romeo2","PG",5))))
        expectedList.add(Team(1,"Abc",12,10, listOf(Player("Alpha1","Romeo1","PF",4))))
        listSortViewModel.getTeamSortbyName(ListSortOrder.DESC, listOfTeam)
        Assert.assertEquals(listSortViewModel.result.value, expectedList)
    }

    /**
     * test sort by win asc
     * **/
    @Test
    fun isSortbyWinASC(){
        val expectedList = mutableListOf<Team>()
        expectedList.add(Team(2,"Pqr",10,14, listOf(Player("Alpha2","Romeo2","PG",5))))
        expectedList.add(Team(1,"Abc",12,10, listOf(Player("Alpha1","Romeo1","PF",4))))
        expectedList.add(Team(3,"Xyz",19,18, listOf(Player("Alpha3","Romeo3","FC",6))))
        listSortViewModel.getTeamSortbyWin(ListSortOrder.ASC, listOfTeam)
        Assert.assertEquals(listSortViewModel.result.value, expectedList)
    }

    /**
     * test sort by win desc
     * **/
    @Test
    fun isSortbyWinDESC(){
        val expectedList = mutableListOf<Team>()
        expectedList.add(Team(3,"Xyz",19,18, listOf(Player("Alpha3","Romeo3","FC",6))))
        expectedList.add(Team(1,"Abc",12,10, listOf(Player("Alpha1","Romeo1","PF",4))))
        expectedList.add(Team(2,"Pqr",10,14, listOf(Player("Alpha2","Romeo2","PG",5))))
        listSortViewModel.getTeamSortbyWin(ListSortOrder.DESC, listOfTeam)
        Assert.assertEquals(listSortViewModel.result.value, expectedList)
    }

    /**
     * test sort by loss asc
     * **/
    @Test
    fun isSortbyLossASC(){
        val expectedList = mutableListOf<Team>()
        expectedList.add(Team(1,"Abc",12,10, listOf(Player("Alpha1","Romeo1","PF",4))))
        expectedList.add(Team(2,"Pqr",10,14, listOf(Player("Alpha2","Romeo2","PG",5))))
        expectedList.add(Team(3,"Xyz",19,18, listOf(Player("Alpha3","Romeo3","FC",6))))
        listSortViewModel.getTeamSortbyLoss(ListSortOrder.ASC, listOfTeam)
        Assert.assertEquals(listSortViewModel.result.value, expectedList)
    }

    /**
     * test sort by loss desc
     * **/
    @Test
    fun isSortbyLossDESC(){
        val expectedList = mutableListOf<Team>()
        expectedList.add(Team(3,"Xyz",19,18, listOf(Player("Alpha3","Romeo3","FC",6))))
        expectedList.add(Team(2,"Pqr",10,14, listOf(Player("Alpha2","Romeo2","PG",5))))
        expectedList.add(Team(1,"Abc",12,10, listOf(Player("Alpha1","Romeo1","PF",4))))
        listSortViewModel.getTeamSortbyLoss(ListSortOrder.DESC, listOfTeam)
        Assert.assertEquals(listSortViewModel.result.value, expectedList)
    }
}
package dhl.businessLogicTest.StateMachineTest.state.standings;


import dhl.Mocks.LeagueObjectModel20TeamMocks;
import dhl.Mocks.LeagueObjectModelMocks;
import dhl.Mocks.RegularSeasonStandingListMocks;
import dhl.businessLogic.leagueModel.interfaceModel.ICoach;
import dhl.businessLogic.leagueModel.interfaceModel.IPlayer;
import dhl.businessLogic.simulationStateMachine.interfaces.IStandingSystem;
import dhl.businessLogic.simulationStateMachine.interfaces.IStandings;
import dhl.businessLogic.simulationStateMachine.states.standings.StandingSystem;
import dhl.businessLogic.simulationStateMachine.states.standings.Standings;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StandingSystemTest {

    LeagueObjectModelMocks mockLeagueObjectModel;
    LeagueObjectModel20TeamMocks model20TeamMocks;
    RegularSeasonStandingListMocks standings;
    ICoach coach;
    List<IPlayer> players;
    String manager;

    @BeforeEach
    public void initObject() {
        mockLeagueObjectModel = new LeagueObjectModelMocks();
        standings = new RegularSeasonStandingListMocks();
        coach = mockLeagueObjectModel.getSingleCoach();
        players = mockLeagueObjectModel.get20FreeAgentArrayMock();
        manager = "Harry";
        model20TeamMocks = new LeagueObjectModel20TeamMocks();
        model20TeamMocks.leagueModel20TeamGeneralStandings();
    }

    @Test
    public void getStandingsListTest() {
        List<IStandings> standingsList = model20TeamMocks.getGeneralStandings();
        IStandingSystem standingSystem = new StandingSystem();
        standingSystem.setStandingsList(standingsList);
        Assertions.assertTrue(standingSystem.getStandingsList().size() == 20);
    }

    @Test
    public void setStandingsListTest() {
        List<IStandings> standingsList = model20TeamMocks.getGeneralStandings();
        IStandingSystem standingSystem = new StandingSystem();
        standingSystem.setStandingsList(standingsList);
        Assertions.assertTrue(standingSystem.getStandingsList().size() == 20);
    }

    @Test
    public void StandingSystemTest() {
        List<IStandings> standingsList = model20TeamMocks.getGeneralStandings();
        Assertions.assertFalse(standingsList.isEmpty());
    }

    @Test
    public void updateWinningStandingsTest() {

        IStandingSystem iStandingSystem = new StandingSystem();
        iStandingSystem.setStandingsList(standings.generalSeasonStandings());
        iStandingSystem.updateWinningStandings(iStandingSystem.getStandingsList().get(0).getTeam());

        for (IStandings standing : iStandingSystem.getStandingsList()) {
            if (standing.getTeam().getTeamName().equals(iStandingSystem.getStandingsList().get(0).getTeam().getTeamName())) {
                Assert.assertEquals(standing.getPoints(), 12);
                Assert.assertEquals(standing.getWins(), 6);
                Assert.assertEquals(standing.getGamesPlayed(), 10);
            }
        }
    }

    @Test
    public void updateLosingStandingsTest() {

        IStandingSystem iStandingSystem = new StandingSystem();
        iStandingSystem.setStandingsList(standings.generalSeasonStandings());
        iStandingSystem.updateLosingStandings(iStandingSystem.getStandingsList().get(0).getTeam());

        for (IStandings standing : iStandingSystem.getStandingsList()) {
            if (standing.getTeam().getTeamName().equals(iStandingSystem.getStandingsList().get(0).getTeam().getTeamName())) {
                Assert.assertEquals(standing.getPoints(), 10);
                Assert.assertEquals(standing.getLoss(), 5);
                Assert.assertEquals(standing.getGamesPlayed(), 10);
            }
        }

    }


    @Test
    public void rankGeneratorSamePointsTest() {
        IStandingSystem standingSystem = new StandingSystem();
        List<IStandings> standings = new ArrayList<>();

        IStandings standings1 = new Standings();
        standings1.setPoints(10);
        standings1.setWins(4);
        standings1.setLoss(4);

        IStandings standings2 = new Standings();
        standings2.setPoints(10);
        standings2.setWins(5);
        standings2.setLoss(5);

        standings.add(standings1);
        standings.add(standings2);

        standingSystem.setStandingsList(standings);
        standingSystem.rankGenerator(standings);
        Assert.assertEquals(standings.get(0), standings2);
        Assert.assertEquals(standings.get(1), standings1);
    }

    @Test
    public void rankGeneratorDifferentPointsTest() {
        IStandingSystem standingSystem = new StandingSystem();
        List<IStandings> standings = new ArrayList<>();

        IStandings standings1 = new Standings();
        standings1.setPoints(8);
        standings1.setWins(5);
        standings1.setLoss(5);

        IStandings standings2 = new Standings();
        standings2.setPoints(10);
        standings2.setWins(4);
        standings2.setLoss(4);

        standings.add(standings1);
        standings.add(standings2);

        standingSystem.setStandingsList(standings);
        standingSystem.rankGenerator(standings);
        Assert.assertEquals(standings.get(0), standings2);
        Assert.assertEquals(standings.get(1), standings1);
    }

    @Test
    public void rankGeneratorSameWinsTest() {
        IStandingSystem standingSystem = new StandingSystem();
        List<IStandings> standings = new ArrayList<>();

        IStandings standings1 = new Standings();
        standings1.setPoints(10);
        standings1.setWins(5);
        standings1.setLoss(4);

        IStandings standings2 = new Standings();
        standings2.setPoints(10);
        standings2.setWins(5);
        standings2.setLoss(5);

        standings.add(standings1);
        standings.add(standings2);

        standingSystem.setStandingsList(standings);
        standingSystem.rankGenerator(standings);
        Assert.assertEquals(standings.get(0), standings2);
        Assert.assertEquals(standings.get(1), standings1);
    }

    @Test
    public void leagueRankingTest() {
        List<IStandings> standingsList = model20TeamMocks.getGeneralStandings();
        IStandingSystem standingSystem = new StandingSystem();

        standingSystem.setStandingsList(standingsList);
        standingsList = standingSystem.leagueRanking();

        Assert.assertEquals(standingsList.get(0).getTeam().getTeamName(), "Bruins");
        Assert.assertEquals(standingsList.get(0).getPoints(), 94);

        Assert.assertEquals(standingsList.get(1).getTeam().getTeamName(), "Capitals");
        Assert.assertEquals(standingsList.get(1).getPoints(), 92);
    }

    @Test
    public void conferenceRankingTest() {
        List<IStandings> standingsList = model20TeamMocks.getGeneralStandings();
        IStandingSystem standingSystem = new StandingSystem();

        List<IStandings> conferenceTeamList = standingSystem.conferenceRanking(standingsList.get(0).getTeamConference(), standingsList);

        Assert.assertEquals(conferenceTeamList.get(0).getTeam().getTeamName(), "Bruins");
        Assert.assertEquals(conferenceTeamList.get(0).getPoints(), 94);

        Assert.assertEquals(conferenceTeamList.get(9).getTeam().getTeamName(), "Canadiens");
        Assert.assertEquals(conferenceTeamList.get(9).getPoints(), 70);
    }

    @Test
    public void divisionRankingTest() {
        List<IStandings> standingsList = model20TeamMocks.getGeneralStandings();
        IStandingSystem standingSystem = new StandingSystem();

        List<IStandings> divisionTeamList = standingSystem.divisionRanking(standingsList.get(0).getTeamDivision(), standingsList);

        Assert.assertEquals(divisionTeamList.get(0).getTeam().getTeamName(), "Bruins");
        Assert.assertEquals(divisionTeamList.get(0).getPoints(), 94);

        Assert.assertEquals(divisionTeamList.get(4).getTeam().getTeamName(), "Canadiens");
        Assert.assertEquals(divisionTeamList.get(4).getPoints(), 70);

    }
}


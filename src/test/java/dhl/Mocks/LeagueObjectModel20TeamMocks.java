package dhl.Mocks;

import dhl.businessLogic.leagueModel.*;
import dhl.businessLogic.leagueModel.interfaceModel.*;
import dhl.businessLogic.simulationStateMachine.states.seasonScheduler.Scheduler;
import dhl.businessLogic.simulationStateMachine.states.seasonScheduler.interfaces.IScheduler;
import dhl.businessLogic.simulationStateMachine.states.standings.Standings;
import dhl.businessLogic.simulationStateMachine.states.standings.interfaces.IStandings;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class LeagueObjectModel20TeamMocks {

    ILeagueObjectModel leagueData;
    ArrayList<IStandings> generalStandings;
    LeagueObjectModelMocks mockLeagueObjectModel;
    IScheduler scheduler;
    List<IPlayer> statistics;

    public LeagueObjectModel20TeamMocks() {
        this.leagueData = new LeagueObjectModel();
        this.generalStandings = new ArrayList<>();
        mockLeagueObjectModel = new LeagueObjectModelMocks();
        scheduler = new Scheduler();
        statistics = mockLeagueObjectModel.getPlayerArrayMock();
    }

    public ILeagueObjectModel getLeagueData() {
        return leagueData;
    }

    public void setLeagueData(ILeagueObjectModel leagueData) {
        this.leagueData = leagueData;
    }

    public ArrayList<IStandings> getGeneralStandings() {
        return generalStandings;
    }

    public void setGeneralStandings(ArrayList<IStandings> generalStandings) {
        this.generalStandings = generalStandings;
    }

    public void leagueModel20TeamGeneralStandings() {
        ArrayList<IStandings> standings = new ArrayList<>();

        IGeneralManager generalManager = new GeneralManager("generalManager", "normal");

        ICoach headCoach = new Coach("Coach", 0.1, 0.2, .5, .9);
        ICoach headCoach2 = new Coach("Coach2", 0.1, 0.2, .5, .8);
        List<ICoach> coaches = new ArrayList<>();
        coaches.add(headCoach);
        coaches.add(headCoach2);
        IPlayerStatistics playerStats = new PlayerStatistics(5, 5, 8, 9);
        playerStats.setAge(20);
        IPlayer playersList1 = new Player("playerName", "position", playerStats);
        IPlayer playersList2 = new Player("playerName", "position", playerStats);
        List<IPlayer> playersList = new ArrayList<>();
        playersList.add(playersList1);
        playersList.add(playersList2);

        ITeam team1 = new Team("Bruins", generalManager, headCoach, playersList);
        ITeam team2 = new Team("Lightning", generalManager, headCoach, playersList);
        ITeam team3 = new Team("Maple", generalManager, headCoach, playersList);
        ITeam team4 = new Team("Panthers", generalManager, headCoach, playersList);
        ITeam team5 = new Team("Canadiens", generalManager, headCoach, playersList);

        ITeam team6 = new Team("Capitals", generalManager, headCoach, playersList);
        ITeam team7 = new Team("Flyers", generalManager, headCoach, playersList);
        ITeam team8 = new Team("Penguins", generalManager, headCoach, playersList);
        ITeam team9 = new Team("Hurricanes", generalManager, headCoach, playersList);
        ITeam team10 = new Team("BlueJackets", generalManager, headCoach, playersList);

        ITeam team11 = new Team("Blues", generalManager, headCoach, playersList);
        ITeam team12 = new Team("Avalanche", generalManager, headCoach, playersList);
        ITeam team13 = new Team("Stars", generalManager, headCoach, playersList);
        ITeam team14 = new Team("Jets", generalManager, headCoach, playersList);
        ITeam team15 = new Team("Predators", generalManager, headCoach, playersList);

        ITeam team16 = new Team("Golden", generalManager, headCoach, playersList);
        ITeam team17 = new Team("Oilers", generalManager, headCoach, playersList);
        ITeam team18 = new Team("Flames", generalManager, headCoach, playersList);
        ITeam team19 = new Team("Cancuks", generalManager, headCoach, playersList);
        ITeam team20 = new Team("Coyotes", generalManager, headCoach, playersList);


        ArrayList<ITeam> teamsListDivision1 = new ArrayList<>();
        ArrayList<ITeam> teamsListDivision2 = new ArrayList<>();
        ArrayList<ITeam> teamsListDivision3 = new ArrayList<>();
        ArrayList<ITeam> teamsListDivision4 = new ArrayList<>();

        teamsListDivision1.add(team1);
        teamsListDivision1.add(team2);
        teamsListDivision1.add(team3);
        teamsListDivision1.add(team4);
        teamsListDivision1.add(team5);

        teamsListDivision2.add(team6);
        teamsListDivision2.add(team7);
        teamsListDivision2.add(team8);
        teamsListDivision2.add(team9);
        teamsListDivision2.add(team10);

        teamsListDivision3.add(team11);
        teamsListDivision3.add(team12);
        teamsListDivision3.add(team13);
        teamsListDivision3.add(team14);
        teamsListDivision3.add(team15);

        teamsListDivision4.add(team16);
        teamsListDivision4.add(team17);
        teamsListDivision4.add(team18);
        teamsListDivision4.add(team19);
        teamsListDivision4.add(team20);

        IDivision division1 = new Division("Atlantic Division", teamsListDivision1);
        IDivision division2 = new Division("Metropolitan Division", teamsListDivision2);
        IDivision division3 = new Division("Central Division", teamsListDivision3);
        IDivision division4 = new Division("Pacific Division", teamsListDivision4);

        ArrayList<IDivision> conference1Divisions = new ArrayList<>();
        ArrayList<IDivision> conference2Divisions = new ArrayList<>();

        conference1Divisions.add(division1);
        conference1Divisions.add(division2);
        conference2Divisions.add(division3);
        conference2Divisions.add(division4);

        IConference conference1 = new Conference("Eastern Conference", conference1Divisions);
        IConference conference2 = new Conference("Western Conference", conference2Divisions);

        List<IConference> conferences = new ArrayList<>();
        conferences.add(conference1);
        conferences.add(conference2);

        GameConfigMock gameConfig = new GameConfigMock();

        IGeneralManager iGeneralManager = new GeneralManager("manager1");
        IGeneralManager iGeneralManager2 = new GeneralManager("manager2");
        List<IGeneralManager> iGeneralManagers = new ArrayList<>();
        iGeneralManagers.add(iGeneralManager);
        iGeneralManagers.add(iGeneralManager2);

        ILeagueObjectModel league = new LeagueObjectModel("Dalhousie Hockey League", conferences, playersList, coaches, iGeneralManagers, gameConfig.getGameConfigMock());
        setLeagueData(league);

        IStandings standings1 = new Standings();
        IStandings standings2 = new Standings();
        IStandings standings3 = new Standings();
        IStandings standings4 = new Standings();
        IStandings standings5 = new Standings();
        IStandings standings6 = new Standings();
        IStandings standings7 = new Standings();
        IStandings standings8 = new Standings();
        IStandings standings9 = new Standings();
        IStandings standings10 = new Standings();
        IStandings standings11 = new Standings();
        IStandings standings12 = new Standings();
        IStandings standings13 = new Standings();
        IStandings standings14 = new Standings();
        IStandings standings15 = new Standings();
        IStandings standings16 = new Standings();
        IStandings standings17 = new Standings();
        IStandings standings18 = new Standings();
        IStandings standings19 = new Standings();
        IStandings standings20 = new Standings();

        standings1.setTeamConference(conference1);
        standings1.setTeamDivision(division1);
        standings1.setTeam(team1);
        standings1.setPoints(94);
        standings1.setWins(47);
        standings1.setLoss(3);

        standings2.setTeamConference(conference1);
        standings2.setTeamDivision(division1);
        standings2.setTeam(team2);
        standings2.setPoints(82);
        standings2.setWins(41);
        standings2.setLoss(9);

        standings3.setTeamConference(conference1);
        standings3.setTeamDivision(division1);
        standings3.setTeam(team3);
        standings3.setPoints(80);
        standings3.setWins(40);
        standings3.setLoss(10);

        standings4.setTeamConference(conference1);
        standings4.setTeamDivision(division1);
        standings4.setTeam(team4);
        standings4.setPoints(76);
        standings4.setWins(38);
        standings4.setLoss(12);

        standings5.setTeamConference(conference1);
        standings5.setTeamDivision(division1);
        standings5.setTeam(team5);
        standings5.setPoints(70);
        standings5.setWins(35);
        standings5.setLoss(15);

        standings6.setTeamConference(conference1);
        standings6.setTeamDivision(division2);
        standings6.setTeam(team6);
        standings6.setPoints(92);
        standings6.setWins(46);
        standings6.setLoss(4);

        standings7.setTeamConference(conference1);
        standings7.setTeamDivision(division2);
        standings7.setTeam(team7);
        standings7.setPoints(88);
        standings7.setWins(44);
        standings7.setLoss(6);

        standings8.setTeamConference(conference1);
        standings8.setTeamDivision(division2);
        standings8.setTeam(team8);
        standings8.setPoints(82);
        standings8.setWins(41);
        standings8.setLoss(9);

        standings9.setTeamConference(conference1);
        standings9.setTeamDivision(division2);
        standings9.setTeam(team9);
        standings9.setPoints(80);
        standings9.setWins(40);
        standings9.setLoss(10);

        standings10.setTeamConference(conference1);
        standings10.setTeamDivision(division2);
        standings10.setTeam(team10);
        standings10.setPoints(78);
        standings10.setWins(39);
        standings10.setLoss(11);

        standings11.setTeamConference(conference2);
        standings11.setTeamDivision(division3);
        standings11.setTeam(team11);
        standings11.setPoints(90);
        standings11.setWins(45);
        standings11.setLoss(5);

        standings12.setTeamConference(conference2);
        standings12.setTeamDivision(division3);
        standings12.setTeam(team12);
        standings12.setPoints(88);
        standings12.setWins(44);
        standings12.setLoss(6);

        standings13.setTeamConference(conference2);
        standings13.setTeamDivision(division3);
        standings13.setTeam(team13);
        standings13.setPoints(84);
        standings13.setWins(42);
        standings13.setLoss(8);

        standings14.setTeamConference(conference2);
        standings14.setTeamDivision(division3);
        standings14.setTeam(team14);
        standings14.setPoints(76);
        standings14.setWins(38);
        standings14.setLoss(12);

        standings15.setTeamConference(conference2);
        standings15.setTeamDivision(division3);
        standings15.setTeam(team15);
        standings15.setPoints(72);
        standings15.setWins(36);
        standings15.setLoss(14);

        standings16.setTeamConference(conference2);
        standings16.setTeamDivision(division4);
        standings16.setTeam(team16);
        standings16.setPoints(88);
        standings16.setWins(44);
        standings16.setLoss(6);

        standings17.setTeamConference(conference2);
        standings17.setTeamDivision(division4);
        standings17.setTeam(team17);
        standings17.setPoints(86);
        standings17.setWins(43);
        standings17.setLoss(7);

        standings18.setTeamConference(conference2);
        standings18.setTeamDivision(division4);
        standings18.setTeam(team18);
        standings18.setPoints(82);
        standings18.setWins(41);
        standings18.setLoss(9);

        standings19.setTeamConference(conference2);
        standings19.setTeamDivision(division4);
        standings19.setTeam(team19);
        standings19.setPoints(78);
        standings19.setWins(39);
        standings19.setLoss(11);

        standings20.setTeamConference(conference2);
        standings20.setTeamDivision(division4);
        standings20.setTeam(team20);
        standings20.setPoints(74);
        standings20.setWins(37);
        standings20.setLoss(13);

        standings.add(standings1);
        standings.add(standings2);
        standings.add(standings3);
        standings.add(standings4);
        standings.add(standings5);
        standings.add(standings6);
        standings.add(standings7);
        standings.add(standings8);
        standings.add(standings9);
        standings.add(standings10);
        standings.add(standings11);
        standings.add(standings12);
        standings.add(standings13);
        standings.add(standings14);
        standings.add(standings15);
        standings.add(standings16);
        standings.add(standings17);
        standings.add(standings18);
        standings.add(standings19);
        standings.add(standings20);

        setGeneralStandings(standings);
    }

    public IScheduler leagueModel20TeamPlayoffsSchedules() {
        leagueModel20TeamGeneralStandings();
        ILeagueObjectModel league = this.getLeagueData();
        ArrayList<IStandings> standings = getGeneralStandings();

        IGeneralManager manager = league.getGeneralManagers().get(0);

        ITeam teamPlayOff1 = new Team("Bruins", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayoff2 = new Team("Maple", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayoff3 = new Team("Hurricanes", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayoff4 = new Team("Flyers", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayoff5 = new Team("Blues", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayoff6 = new Team("Avalanche", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayoff7 = new Team("Cancuks", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayoff8 = new Team("Flames", manager, league.getCoaches().get(0), statistics);

        LocalDate playOffStartDate = LocalDate.of(2021, 04, 01);
        LocalDate playOffStarts = playOffStartDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY)).with(
                TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        scheduler.setPlayOffStartDate(playOffStarts);

        scheduler.playOffs(standings, league);

        scheduler.gameWinner(teamPlayOff1);
        scheduler.gameWinner(teamPlayoff2);
        scheduler.gameWinner(teamPlayoff3);
        scheduler.gameWinner(teamPlayoff4);
        scheduler.gameWinner(teamPlayoff5);
        scheduler.gameWinner(teamPlayoff6);
        scheduler.gameWinner(teamPlayoff7);
        scheduler.gameWinner(teamPlayoff8);

        ITeam teamPlayOffRoundThree1 = new Team("Maple", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayOffRoundThree2 = new Team("Flyers", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayOffRoundThree3 = new Team("Avalanche", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayOffRoundThree4 = new Team("Cancuks", manager, league.getCoaches().get(0), statistics);

        scheduler.gameWinner(teamPlayOffRoundThree1);
        scheduler.gameWinner(teamPlayOffRoundThree2);
        scheduler.gameWinner(teamPlayOffRoundThree3);
        scheduler.gameWinner(teamPlayOffRoundThree4);

        ITeam teamPlayOffRoundFour1 = new Team("Maple", manager, league.getCoaches().get(0), statistics);
        ITeam teamPlayOffRoundFour2 = new Team("Avalanche", manager, league.getCoaches().get(0), statistics);

        scheduler.gameWinner(teamPlayOffRoundFour1);
        scheduler.gameWinner(teamPlayOffRoundFour2);

        return scheduler;
    }
}

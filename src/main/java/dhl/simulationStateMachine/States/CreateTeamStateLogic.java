package dhl.simulationStateMachine.States;

import dhl.database.LeagueObjectModelDB;
import dhl.database.interfaceDB.ILeagueObjectModelDB;
import dhl.leagueModel.*;
import dhl.leagueModel.interfaceModel.*;
import dhl.simulationStateMachine.GameContext;
import dhl.simulationStateMachine.States.Interface.ICreateTeamStateLogic;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamStateLogic implements ICreateTeamStateLogic {
    public ILeagueObjectModel saveleagueObject( GameContext ourGame, ILeagueObjectModel inMemoryLeague,ILeagueObjectModelInput leagueObjectModelInput) throws Exception {
        ILeagueObjectModel leagueObjectModel = new LeagueObjectModel();
        try {
            ILeagueObjectModelDB leagueObjectModelDB = new LeagueObjectModelDB();
            leagueObjectModel = inMemoryLeague.saveLeagueObjectModel(leagueObjectModelDB,leagueObjectModelInput);
            ITeam team = leagueObjectModelInput.getNewlyCreatedTeam();
            ourGame.setSelectedTeam(findTeam(inMemoryLeague , team.getTeamName()));
        } catch (Exception e){
            System.out.println(e.getMessage());
            ourGame.setGameInProgress(false);
        }
        return leagueObjectModel;
    }

    public ITeam createNewTeamObject(List<IPlayer> freeAgents, ITeam team) throws Exception {
        List<IPlayer> players= new ArrayList<>();

        freeAgents.forEach((freeAgent)->{
            IPlayer player= new Player(freeAgent.getPlayerName(),freeAgent.getPosition(),false,freeAgent.getPlayerStats());
            players.add(player);
        });

        ITeam newlyCreatedTeam=new Team(team.getTeamName(),team.getGeneralManager(),team.getHeadCoach(),players);

        return newlyCreatedTeam;
    }

    public IConference findConference(List<IConference> confrenceArray, String conferenceName ){
        for(int i= 0; i< confrenceArray.size(); i++){
            IConference ourConference = confrenceArray.get(i);
            if(ourConference.getConferenceName().equals(conferenceName)){
                return ourConference;
            }
        }
        return null;
    }

    public IDivision findDivision(List<IDivision> divisionArrayList , String divisionName){
        for(int i= 0; i< divisionArrayList.size(); i++){
            IDivision ourDivision = divisionArrayList.get(i);
            if(ourDivision.getDivisionName().equals(divisionName)){
                return ourDivision;
            }
        }
        return null;
    }

    public ITeam findTeam(ILeagueObjectModel inMemoryLeague, String teamName){
        ITeam teamObject = null;

        for(IConference conference: inMemoryLeague.getConferences() ){
            for(IDivision division: conference.getDivisions()){
                for (ITeam team: division.getTeams()){
                    if (team.getTeamName().equals(teamName)){
                        teamObject = team;
                    }
                }
            }
        }
        return teamObject;
    }

    public IPlayer findFreeAgent(List<IPlayer> freeAgentArrayList, String freeAgentName){
        for(IPlayer ourFreeAgent:freeAgentArrayList){
            String name=ourFreeAgent.getPlayerName();
            if(name.equals(freeAgentName)){
                return ourFreeAgent;
            }
        }
        return null;
    }
    public String findGeneralManager(List<IGeneralManager> generalManagerArray, String generalManager ){
        for(IGeneralManager ourGeneralManager : generalManagerArray){
            String ourGeneralManagerName= ourGeneralManager.getGeneralManagerName();
            if(ourGeneralManagerName.equals(generalManager)){
                return generalManager;
            }
        }
        return null;
    }
    public String findCoach(List<ICoach> coachArray, String coachName ){
        for(ICoach ourCoach : coachArray){
            String coachesName= ourCoach.getCoachName();
            if(coachesName.equals(coachName)){
                return coachName;
            }
        }
        return null;
    }
}

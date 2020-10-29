package dhl.simulationStateMachineTest;

import dhl.Mocks.LeagueObjectModelMocks;
import dhl.database.interfaceDB.ILeagueObjectModelData;
import dhl.leagueModel.*;
import dhl.leagueModel.interfaceModel.*;
import dhl.leagueModelTests.MockDatabase;
import dhl.simulationStateMachine.GameContext;
import dhl.simulationStateMachine.States.CreateTeamStateLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateTeamStateLogicTest {
    GameContext ourGame;
    CreateTeamStateLogic testClassObject;
    LeagueObjectModelMocks mocks;
    private ILeagueObjectModel inMemoryLeague;

    @BeforeEach
    public void initObject(){
        ourGame = new GameContext();
        testClassObject = new CreateTeamStateLogic();
        mocks = new LeagueObjectModelMocks();
        inMemoryLeague = mocks.getLeagueObjectMock();
    }
    @Test
    public void saveleagueObjectTest() throws Exception {
        ILeagueObjectModel objLeagueObjectModel = new LeagueObjectModel();
        ILeagueObjectModelData mockDb=new MockDatabase();

        objLeagueObjectModel = testClassObject.saveleagueObject(inMemoryLeague.getLeagueName(), "Western","Atlantic","Ontario1","Mathew1",mocks.get20FreeAgentArrayMock(),mocks.getSingleCoach(), ourGame, inMemoryLeague,mockDb);
        Assertions.assertEquals("Dhl",objLeagueObjectModel.getLeagueName());
    }

    @Test
    public void findConferenceTest(){
        IConference conferenceTest = testClassObject.findConference(mocks.getConferenceArrayMock() , "Mock Conference1");
        assertTrue(conferenceTest.getConferenceName().equals("Mock Conference1"));

        conferenceTest = testClassObject.findConference(mocks.getConferenceArrayMock() , "Wrong Conference1");
        assertTrue(conferenceTest == null);
    }

    @Test
    public void findDivisionTest(){
        IDivision divisionTest = testClassObject.findDivision(mocks.getDivisionArrayMock() , "Mock Division2");
        assertTrue(divisionTest.getDivisionName().equals("Mock Division2"));

        divisionTest = testClassObject.findDivision(mocks.getDivisionArrayMock() , "Wrong DivisionName");
        assertTrue(divisionTest == null);

    }

    @Test
    public void findFreeAgent(){
        IFreeAgent freeAgentTest = testClassObject.findFreeAgent(mocks.getFreeAgentArrayMock() , "Mock Free Agent 1");
        assertTrue(freeAgentTest.getPlayerName().equals("Mock Free Agent 1"));

        freeAgentTest = testClassObject.findFreeAgent(mocks.getFreeAgentArrayMock() , "Wrong Free Agent");
        assertTrue(freeAgentTest == null);

    }

    @Test
    public void createNewTeamObjectTest() throws Exception {
        ITeam newlyCreatedTeam= new Team();

        newlyCreatedTeam = testClassObject.createNewTeamObject(mocks.get20FreeAgentArrayMock(), "testTeam", "testGenManager", mocks.getSingleCoach());
        Assertions.assertEquals(20,newlyCreatedTeam.getPlayers().size());
    }
}
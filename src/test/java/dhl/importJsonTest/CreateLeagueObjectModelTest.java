package dhl.importJsonTest;

import dhl.inputOutput.importJson.CreateLeagueObjectModel;
import dhl.inputOutput.importJson.GameConfig;
import dhl.inputOutput.importJson.ImportJsonFile;
import dhl.inputOutput.importJson.interfaces.IGameConfig;
import dhl.Mocks.JsonFilePathMock;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateLeagueObjectModelTest {
    JSONObject leagueObject;
    CreateLeagueObjectModel testClassObject;
    JsonFilePathMock filePathMock;

    @BeforeEach
    public void initObject() throws Exception {
        this.filePathMock = new JsonFilePathMock();
        ImportJsonFile importJsonFile = new ImportJsonFile(filePathMock.getFilePath());
        this.leagueObject = importJsonFile.getJsonObject();
        this.testClassObject = new CreateLeagueObjectModel();
    }

    @Test
    public void checkJsonArrayTest() {
        String conferenceArrayKey = filePathMock.getConferenceArrayKey();
        assertTrue(testClassObject.checkJsonArray(leagueObject, conferenceArrayKey));

        String freeAgentArrayKey = filePathMock.getFreeAgentArrayKey();
        assertTrue(testClassObject.checkJsonArray(leagueObject, freeAgentArrayKey));

        assertFalse(testClassObject.checkJsonArray(leagueObject, "invalidArrayKey"));
    }

    @Test
    public void getLeagueObjectModel() throws Exception {
        IGameConfig gameConfig = new GameConfig(new JSONObject());
        CreateLeagueObjectModel testObject = new CreateLeagueObjectModel(leagueObject, gameConfig);
        assertTrue(testObject.getLeagueObjectModel() != null);
    }

}

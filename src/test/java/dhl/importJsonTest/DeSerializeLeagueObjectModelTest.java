package dhl.importJsonTest;

import dhl.Mocks.LeagueObjectModelMocks;
import dhl.Mocks.SerializedJsonMock;
import dhl.businessLogic.leagueModel.LeagueObjectModel;
import dhl.businessLogic.leagueModel.interfaceModel.ILeagueObjectModel;
import dhl.inputOutput.importJson.serializeDeserialize.DeserializeLeagueObjectModel;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DeSerializeLeagueObjectModelTest {

    DeserializeLeagueObjectModel deserializeleagueObjectModel;
    SerializedJsonMock jsonMock;
    ILeagueObjectModel leagueObjectModel;
    JSONParser jsonParser;
    LeagueObjectModelMocks leagueObjectModelMocks;

    @BeforeEach
    public void initObject() {
        deserializeleagueObjectModel = new DeserializeLeagueObjectModel("testLeagueName");
        jsonMock = new SerializedJsonMock();
        leagueObjectModel = new LeagueObjectModel();
        jsonParser = new JSONParser();
        leagueObjectModelMocks = new LeagueObjectModelMocks();
    }

    @Test
    public void deserializeLeagueObjectJsonTest() throws Exception {
//        JSONObject jsonLeagueObject = (JSONObject) jsonParser.parse(jsonMock.serializedJson());
//        leagueObjectModel = deserializeleagueObjectModel.deserializeLeagueObjectJson("");
//        Assertions.assertEquals("Dhl", leagueObjectModel.getLeagueName());
    }

    @Test
    public void updateLeagueObjectModelJsonTest() throws Exception {
//        JSONObject jsonLeagueObject = (JSONObject) jsonParser.parse(jsonMock.serializedJson());
//        JSONObject updatedJsonLeagueObject = deserializeleagueObjectModel.updateLeagueObjectModelJson(jsonLeagueObject);
//        Assertions.assertEquals("Dhl", updatedJsonLeagueObject.get("leagueName"));
    }

    @Test
    public void deserializePlayersTest() throws ParseException, IOException {
//        JSONArray jsonPlayerObject = (JSONArray) jsonParser.parse(jsonMock.serializedPlayerList());
//        List<IPlayer> playersObject = deserializeleagueObjectModel.deserializePlayers("");
//        Assertions.assertEquals(1, playersObject.size());
    }
}

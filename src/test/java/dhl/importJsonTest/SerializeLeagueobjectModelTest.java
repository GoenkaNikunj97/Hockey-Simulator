//package dhl.importJsonTest;
//
//import dhl.inputOutput.importJson.serializeDeserialize.DeserializeLeagueObjectModel;
//import dhl.inputOutput.importJson.serializeDeserialize.interfaces.IDeserializeLeagueObjectModel;
//import dhl.inputOutput.importJson.serializeDeserialize.SerializeLeagueObjectModel;
//import dhl.Mocks.LeagueObjectModelMocks;
//import dhl.Mocks.SerializedJsonMock;
//import dhl.businessLogic.leagueModel.LeagueObjectModel;
//import dhl.businessLogic.leagueModel.Player;
//import dhl.businessLogic.leagueModel.PlayerStatistics;
//import dhl.businessLogic.leagueModel.interfaceModel.ILeagueObjectModel;
//import dhl.businessLogic.leagueModel.interfaceModel.IPlayer;
//import dhl.businessLogic.leagueModel.interfaceModel.IPlayerStatistics;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//class SerializeLeagueobjectModelTest {
//    private static final String filepath = "src/test/java/dhl/Mocks/";
//    private static final String extension = ".json";
//    private final String playerFileName = "--InjuredPlayer.json";
//
//    SerializeLeagueObjectModel serializeLeagueobjectModel;
//    IDeserializeLeagueObjectModel deserializeLeagueobjectModel;
//    LeagueObjectModelMocks mockLeagueObjectModel;
//    SerializedJsonMock mockSerializedJson;
//    String filePath;
//
//    @BeforeEach
//    public void initObject() {
//        serializeLeagueobjectModel = new SerializeLeagueObjectModel(filepath);
//        deserializeLeagueobjectModel = new DeserializeLeagueObjectModel(filepath);
//        mockLeagueObjectModel = new LeagueObjectModelMocks();
//        mockSerializedJson = new SerializedJsonMock();
//    }
//
//    @Test
//    void serializeLeagueObjectModelTest() throws Exception {
//        ILeagueObjectModel leagueObjectModel = new LeagueObjectModel();
//
//        String serializedleagueModel = serializeLeagueobjectModel.serializeData(mockLeagueObjectModel.leagueModelMockWith30Players());
//
//        String jsonFilePath = filepath + "Dhl" + extension;
//        serializeLeagueobjectModel.writeSerializedLeagueObjectToJsonFile(mockLeagueObjectModel.leagueModelMockWith30Players());
//
//        leagueObjectModel = deserializeLeagueobjectModel.deserializeLeagueObjectJson("Dhl");
//
//        // TODO: 21-11-2020 Rajni Update this test
//        //Assertions.assertEquals(serializedleagueModel, mockSerializedJson.serializedJson());
//        Assertions.assertEquals("Dhl", leagueObjectModel.getLeagueName());
//        Assertions.assertEquals(2, leagueObjectModel.getConferences().size());
//        Assertions.assertEquals(2, leagueObjectModel.getFreeAgents().size());
//        Assertions.assertEquals(2, leagueObjectModel.getCoaches().size());
//        Assertions.assertEquals(2, leagueObjectModel.getGeneralManagers().size());
//
//        deleteFile(jsonFilePath);
//    }
//
//    @Test
//    void WriteSerializedLeagueObjectToFileTest() throws Exception {
//        ILeagueObjectModel leagueObjectModel = new LeagueObjectModel();
//        String jsonFilePath = filepath + "Dhl" + extension;
//
//        serializeLeagueobjectModel.writeSerializedLeagueObjectToJsonFile(mockLeagueObjectModel.leagueModelMockWith30Players());
//
//        FileReader leagueObjectJsonReader = new FileReader(jsonFilePath);
//        JSONParser jsonParser = new JSONParser();
//        JSONObject objLeagueObject = (JSONObject) jsonParser.parse(leagueObjectJsonReader);
//        leagueObjectModel = deserializeLeagueobjectModel.deserializeLeagueObjectJson("Dhl");
//        Assertions.assertNotNull(leagueObjectModel);
//        Assertions.assertEquals("Dhl", leagueObjectModel.getLeagueName());
//
//        leagueObjectJsonReader.close();
//        deleteFile(jsonFilePath);
//    }
//
//    @Test
//    void UpdateSerializedLeagueObjectToJsonFileTest() throws Exception {
//        ILeagueObjectModel leagueObjectModel = new LeagueObjectModel();
//        String jsonFilePath = filepath + "Dhl" + extension;
//
//        serializeLeagueobjectModel.writeSerializedLeagueObjectToJsonFile(mockLeagueObjectModel.leagueModelMockWith30Players());
//        serializeLeagueobjectModel.updateSerializedLeagueObjectToJsonFile(mockLeagueObjectModel.leagueModelMockWith30Players());
//
//        FileReader leagueObjectJsonReader = new FileReader(jsonFilePath);
//        JSONParser jsonParser = new JSONParser();
//        JSONObject objLeagueObject = (JSONObject) jsonParser.parse(leagueObjectJsonReader);
//
//        leagueObjectModel = deserializeLeagueobjectModel.deserializeLeagueObjectJson("Dhl");
//
//        Assertions.assertNotNull(leagueObjectModel);
//        Assertions.assertEquals("Dhl", leagueObjectModel.getLeagueName());
//
//        leagueObjectJsonReader.close();
//        deleteFile(jsonFilePath);
//    }
//
//    @Test
//    void updateSerializedPlayerListToJsonFile() throws Exception {
//        List<IPlayer> playersList = new ArrayList<>();
//        String jsonFilePath = filepath + "Dhl" + playerFileName;
//
//        List<IPlayer> players = new ArrayList<>();
//        IPlayerStatistics playerStatistics1 = new PlayerStatistics(50, 5, 5, 5, 5);
//        players.add(new Player("Henry", "forward", false, playerStatistics1));
//
//        serializeLeagueobjectModel.updateSerializedPlayerListToJsonFile(players, "Dhl");
//
//        FileReader playersJsonReader = new FileReader(jsonFilePath);
//        JSONParser jsonParser = new JSONParser();
//        JSONArray objPlayersObject = (JSONArray) jsonParser.parse(playersJsonReader);
//        playersList = deserializeLeagueobjectModel.deserializePlayers("Dhl");
//        Assertions.assertNotNull(playersList);
//        Assertions.assertEquals(1, playersList.size());
//
//        playersJsonReader.close();
//        deleteFile(jsonFilePath);
//    }
//
//    @Test
//    void writeJsonToFileTest() throws IOException {
//        String jsonFilePath = filepath + "TestPlayerList" + extension;
//
//        serializeLeagueobjectModel.writeJsonToFile(jsonFilePath,mockSerializedJson.serializedPlayerList());
//        FileReader playersJson = new FileReader(jsonFilePath);
//        Assertions.assertNotNull(playersJson);
//
//        playersJson.close();
//        deleteFile(jsonFilePath);
//    }
//
//    @Test
//    void updateJsonFileTest() throws IOException, ParseException {
//        String jsonFilePath = filepath + "Dhl" + playerFileName;
//
//        serializeLeagueobjectModel.writeJsonToFile(jsonFilePath,mockSerializedJson.serializedPlayerList());
//        serializeLeagueobjectModel.updateJsonFile(mockSerializedJson.serializedPlayerList(),jsonFilePath);
//
//        FileReader playersJson = new FileReader(jsonFilePath);
//        Assertions.assertNotNull(playersJson);
//
//        playersJson.close();
//        deleteFile(jsonFilePath);
//    }
//
//    void deleteFile(String path) throws IOException {
//        File objFile = new File(path);
//
//        if(objFile.exists()) {
//            objFile.delete();
//        }
//    }
//}

package dhl.importJsonTest;


import dhl.Mocks.JsonFilePathMock;
import dhl.inputOutput.importJson.ImportJsonAbstractFactory;
import dhl.inputOutput.importJson.interfaces.IImportJsonFile;
import dhl.inputOutput.importJson.interfaces.IJsonFilePath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ImportJsonFileTest {

    IJsonFilePath filePathMock;
    ImportJsonAbstractFactory importJsonFactory;
    @BeforeEach
    public void initObject(){
        importJsonFactory = ImportJsonAbstractFactory.instance();
        filePathMock = importJsonFactory.createJsonFilePath();
    }


    @Test
    public void getJsonObjectTest(){
        IImportJsonFile importJsonFile = importJsonFactory.createImportJsonFile(filePathMock.getFilePath());
        assertFalse( importJsonFile.getJsonObject().isEmpty() );
    }

    @Test
    public void getJsonIntoStringTest() throws IOException {
        IImportJsonFile importJsonFile = importJsonFactory.createImportJsonFile(filePathMock.getFilePath());
        assertFalse( importJsonFile.getJsonIntoString(filePathMock.getFilePath()).isEmpty() );
    }

}

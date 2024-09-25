package org.utils;

import org.restassured.data.Student;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.util.List;
import static org.utils.ConfigManager.*;

public class TestDataProvider {

    @DataProvider(name = "testData")
    public static Object[][] getData() throws FileNotFoundException {
        List<Student> testDataList = JsonUtils.getTestData(getConfigProperty("students.data.path"));
        Object[][] data = new Object[testDataList.size()][1];
        for (int i = 0; i < testDataList.size(); i++) {
            data[i][0] = testDataList.get(i);
        }
        return data;
    }
}

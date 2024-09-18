package org.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.restassured.data.Student;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class JsonUtils
{

//    @DataProvider(name = "StudentDetailsProvider")
//    public Object[][] getData() throws FileNotFoundException {
//        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/testData/UserTestData.json"));
//        JsonElement dataSet = jsonData.getAsJsonObject().get("users");
//        List<Student> testData = new Gson().fromJson(dataSet, new TypeToken<List<Student>>() {
//        }.getType());
//        Object[][] returnValue = new Object[testData.size()][1];
//        int index = 0;
//        for (Object[] each : returnValue) {
//            each[0] = testData.get(index++);
//        }
//        return returnValue;
//    }

    public static List<Student> getTestData(String filePath) throws FileNotFoundException {
        JsonElement jsonData = JsonParser.parseReader(new FileReader(filePath));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        return new Gson().fromJson(dataSet, new TypeToken<List<Student>>() {}.getType());
    }

}

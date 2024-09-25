package org.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.restassured.data.Student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class JsonUtils {

    public static List<Student> getTestData(String filePath) throws FileNotFoundException {
        JsonElement jsonData = JsonParser.parseReader(new FileReader(filePath));
        JsonElement dataSet = jsonData.getAsJsonObject().get("students");
        return new Gson().fromJson(dataSet, new TypeToken<List<Student>>() {}.getType());
    }

    public static JSONObject getStudentAsJson(Student data) {

        JSONObject jo = new JSONObject();
        jo.put("first_name", data.getFirst_name());
        jo.put("middle_name", data.getMiddle_name());
        jo.put("last_name", data.getLast_name());
        jo.put("date_of_birth", data.getDate_of_birth());

        return jo;
    }

}

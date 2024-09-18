package org.base;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.restassured.data.Student;
import org.restassured.response.StudentDetailsActions;
import org.testng.annotations.*;
import org.utils.ConfigManager;
import static org.utils.JsonUtils.getStudentAsJson;

import org.utils.TestDataProvider;

/**
 * Unit test for simple App.
 */
public class BaseTest {

    StudentDetailsActions response = new StudentDetailsActions();

    private Student student;

    @Factory(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    public BaseTest(Student student) {
        this.student = student;
    }

    @BeforeSuite
    public void setBaseUrl() {
        RestAssured.baseURI = ConfigManager.getConfigProperty("base.url");
    }

    @BeforeTest
    public void addTestUser() {
        JSONObject payload = getStudentAsJson(student);
        response.addStudentDetails(payload).then().log().all();
    }

    @Test
    public void checkCreatedUser() {


        response.getAllStudentsDetails().then().log().all();

    }

    @AfterTest
    public void deleteCreatedUser() {
        Response students = response.getAllStudentsDetails();
        System.out.println(students.body().asString());
    }
}

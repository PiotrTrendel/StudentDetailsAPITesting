package org.functional;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.restassured.data.Student;
import static org.restassured.enums.TestContext.*;
import org.restassured.actions.StudentDetailsActions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import static org.utils.ConfigManager.*;
import org.utils.JsonUtils;

import org.utils.TestDataProvider;

import static java.net.HttpURLConnection.*;

@Getter
public class BaseTest {

    StudentDetailsActions studentsDetails = new StudentDetailsActions();

    private Student testStudent;

    private Response studentAdditionResponse;

    @Factory(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    public BaseTest(Student testStudent) {
        this.testStudent = testStudent;
    }

    @BeforeSuite
    public void setBaseUrl() {
        RestAssured.baseURI = getConfigProperty("base.url");
    }

    @BeforeMethod
    public void setContextAttributes(ITestContext ctx) {
        try {
            String testStudentMidName = studentAdditionResponse.then().extract().response().path("middle_name");
            ctx.setAttribute(getAttrName(MIDDLE_NAME), testStudentMidName);
        } catch (NullPointerException ex) {
            ctx.setAttribute(getAttrName(MIDDLE_NAME), "");
        }
        try {
            String testStudentFirstName = studentAdditionResponse.then().extract().response().path("first_name");
            ctx.setAttribute(getAttrName(FIRST_NAME), testStudentFirstName);
        } catch (NullPointerException ex) {
            ctx.setAttribute(getAttrName(FIRST_NAME), "");
        }
        int testStudentId = studentAdditionResponse.then().extract().response().path("id");
        ctx.setAttribute(getAttrName(STUDENT_ID), testStudentId);
    }

    @BeforeClass(description = "test student is added to list of students details")
    public void addTestStudent(ITestContext ctx) {
        JSONObject payload = JsonUtils.getStudentAsJson(testStudent);
        studentAdditionResponse = studentsDetails.addStudentDetails(payload);

        studentAdditionResponse.then().assertThat().statusCode(HTTP_CREATED);
    }

    @AfterClass(alwaysRun = true, description = "test student is deleted from list of students details after all tests")
    public void deleteCreatedStudent(ITestContext ctx) {
        int studentId = (Integer) ctx.getAttribute(getAttrName(STUDENT_ID));
        Response studentRemovalResponse = studentsDetails.deleteStudentDetails(String.valueOf(studentId));

        studentRemovalResponse.then().assertThat().statusCode(HTTP_OK);
    }


}


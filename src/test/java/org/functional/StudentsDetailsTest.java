package org.functional;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.restassured.data.Student;
import org.testng.ITestContext;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.utils.TestDataProvider;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.*;
import static org.restassured.enums.TestContext.*;
import static org.utils.ConfigManager.getConfigProperty;
import static org.utils.PayloadCreator.createPayloadForUpdate;
import static org.utils.RandomDataGenerator.generateRandomLastName;
import static org.utils.RandomDataGenerator.generateRandomName;

public class StudentsDetailsTest extends BaseTest {

    @Factory(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    public StudentsDetailsTest(Student testStudent) {
        super(testStudent);
    }

    @Test(
            description = "verify created student is successfully present on student details list",
            priority = 0,
            groups = "successfulAddition"
    )
    public void checkCreatedStudent(ITestContext ctx) {
        String studentFirstName = getTestStudent().getFirst_name();
        int studentId = (Integer) ctx.getAttribute(getAttrName(STUDENT_ID));
        Response createdStudentDetails = studentsDetails.getSingleStudentDetailsById(String.valueOf(studentId));

        createdStudentDetails.then().assertThat().statusCode(HTTP_OK);
        createdStudentDetails.then().assertThat().body(getConfigProperty("student.first-name.path"), equalTo(studentFirstName));
    }

    @Test(
            description = "Full list of student details is displayed",
            priority = 1,
            dependsOnGroups = "successfulAddition"
    )
    public void checkIfListOfAllDetailsIsDisplayed(ITestContext ctx) {
        String firstName = (String) ctx.getAttribute(getAttrName(FIRST_NAME));
        Response studentsDetailsResponse = studentsDetails.getAllStudentsDetails();

        studentsDetailsResponse.then().assertThat().statusCode(HTTP_OK);
        studentsDetailsResponse.then().assertThat().body("size()", lessThanOrEqualTo(100));
        studentsDetailsResponse.then().assertThat().body("first_name", hasItem(firstName));
    }


    @Test(
            description = "student's last name can be changed",
            priority = 2,
            dependsOnGroups = "successfulAddition"
    )
    public void changeStudentLastName(ITestContext ctx) {
        String newLastName = generateRandomLastName();
        int studentId = (Integer) ctx.getAttribute(getAttrName(STUDENT_ID));
        JSONObject payload = createPayloadForUpdate(studentId,null,null, newLastName,null, getTestStudent());
        Response modifyTestStudentDetails = studentsDetails.updateStudentDetails(String.valueOf(studentId), payload);
        Response modifiedStudentDetails = studentsDetails.getSingleStudentDetailsById(String.valueOf(studentId));

        modifyTestStudentDetails.then().assertThat().statusCode(HTTP_OK);
        modifiedStudentDetails.then().assertThat().body(getConfigProperty("student.last-name.path"), equalTo(newLastName));
    }

    @Test(
            description = "middle name can be updated when empty",
            priority = 3,
            dependsOnGroups = "successfulAddition"
    )
    public void checkIfMiddleNameCanBeAddedIfEmpty(ITestContext ctx) {
        String newMidName = generateRandomName();
        int studentId = (Integer) ctx.getAttribute(getAttrName(STUDENT_ID));
        JSONObject payload = createPayloadForUpdate(studentId,null, newMidName, null,null, getTestStudent());
        Response modifyStudentDetails = studentsDetails.updateStudentDetails(String.valueOf(studentId), payload);
        Response emptyMidNameStudentDetails = studentsDetails.getSingleStudentDetailsById(Integer.toString(studentId));

        modifyStudentDetails.then().assertThat().statusCode(HTTP_OK);
        emptyMidNameStudentDetails.then().assertThat().body(getConfigProperty("student.middle-name.path"), equalTo(newMidName));
    }

}


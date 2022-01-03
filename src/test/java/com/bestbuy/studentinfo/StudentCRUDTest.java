package com.bestbuy.studentinfo;

import com.bestbuy.model.StudentPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;


public class StudentCRUDTest extends TestBase {

    static String firstName = "PrimUser" + TestUtils.getRandomValue();
    static String lastName = "PrimeUser" + TestUtils.getRandomValue();
    static String programme = "Api Testing";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static int studentId;

    @Test
    public void test001() {

        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Selenium");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(studentPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void test002() {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value =
                given()
                        .when()
                        .get("/list")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + firstName + p2);
        System.out.println(value);
        studentId = (int) value.get("id");
    }

    @Test
    public void test003() {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        firstName = firstName + "_Updated";

        List<String> courseList = new ArrayList<>();
        courseList.add("Scala");
        courseList.add("Java");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        given()
                .header("Content-Type", "application/json")
                .pathParam("studentID", studentId)
                .body(studentPojo)
                .when()
                .put("/{studentID}")
                .then().log().all().statusCode(200);

        HashMap<String, Object> value =

                given()
                        .when()
                        .get("/list")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + firstName + p2);
        System.out.println(value);
    }

    @Test
    public void test004() {

        given()
                .pathParam("studentID", studentId)
                .when()
                .delete("/{studentID}")
                .then()
                .statusCode(204);

        given()
                .pathParam("studentID", studentId)
                .when()
                .get("/{studentID}")
                .then()
                .statusCode(404);

    }

}

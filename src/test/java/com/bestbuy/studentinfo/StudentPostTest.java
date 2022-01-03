package com.bestbuy.studentinfo;

import com.bestbuy.model.StudentPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class StudentPostTest extends TestBase {

    @Test
    public void createStudent() {
        List<String> courseList= new ArrayList<>();
        courseList.add("java");
        courseList.add("selenium");
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Prime1");
        studentPojo.setLastName("Testing");
        studentPojo.setEmail("primetesting@gmail.com");
        studentPojo.setProgramme("Software QA");
        studentPojo.setCourses(courseList);


        Response response = given()
                .header("Content-Type","application/json")
                .body(studentPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();

    }
}

package com.bestbuy.studentinfo;

import com.bestbuy.model.StudentPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentCRUDHomework extends TestBase {
    //Create @BeforeClass if you are not extending class with TestBase
   /* @BeforeClass
    public static void inIt(){
        RestAssured.baseURI= "http://localhost";
        RestAssured.port=8080;
        RestAssured.basePath="/student";
    }*/
    static String firstName= TestUtils.getRandomText();
    static String lastName= TestUtils.getRandomText();
    static String programme= "API Testing";
    static String email= TestUtils.getRandomText()+"@gmail.com";
    static int studentID;
    @Test
    public void test001Create(){
        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Selenium");
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        Response response= given()
                .header("Content-Type", "application/json")
                .body(studentPojo)
                .when()
                .post();
        response.then().log().all().statusCode(201);
        response.prettyPrint();

    }
    @Test
    public void test002Read(){
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";
        HashMap<String,Object> value=
        given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract().path(p1+firstName+p2);
        System.out.println(value);
        studentID= (int) value.get("id");
    }

    @Test
    public void test003Update(){
        firstName= firstName+"_Update";
        String p1="findAll{it.firstName=='";
        String p2= "'}.get(0)";
        List<String> courses= new ArrayList<>();
        courses.add("Java");
        courses.add("Cucumber");

        StudentPojo studentPojo= new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setProgramme(programme);
        studentPojo.setEmail(email);
        studentPojo.setCourses(courses);
        given()
                .header("Content-Type", "application/json")
                .pathParam("id",studentID)
                .body(studentPojo)
                .when()
                .put("/{id}")
                .then().log().all().statusCode(200);
        HashMap<String ,Object> value=
                given()
                        .when()
                        .get("/list")
                        .then()
                        .statusCode(200)
                        .extract().path(p1+firstName+p2);
        System.out.println(value);

    }
    @Test
    public void test004UpdateViaPatch(){
        email= "123"+email;
        StudentPojo studentPojo= new StudentPojo();
        studentPojo.setEmail(email);
        given()
                .header("Content-Type", "application/json")
                .pathParam("id",studentID)
                .body(studentPojo)
                .when()
                .patch("/{id}")
                .then().log().all().statusCode(200);
    }
    @Test
    public void test005Delete(){
        given()
                .pathParam("id",studentID)
                .when()
                .delete("/{id}")
                .then().log().all().statusCode(204);
    }

}

package com.bestbuy.studentinfo;

import com.bestbuy.testbase.TestBase;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class StudentGetTest extends TestBase {

    @Test
    public void getAllStudentsInfo() {
        Response response = given()
                .when()
                .get("/list");
        response.then().statusCode(200);
        response.prettyPrint();
    }


    @Test
    public void getSingleStudentInfo() {
       /* Response response = given()
                .when()
                .get("/3");
        response.then().statusCode(200);
        response.prettyPrint();*/
        Response response = given()
                .pathParam("id", 5)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();


    }

    @Test
    public void searchStudentWithParameter() {
        /*Response response = given()
                .queryParam("programme","Financial Analysis")
                .queryParam("limit",2)
                .when()
                .get("/3");
        response.then().statusCode(200);
        response.prettyPrint();*/
        HashMap<String,Object> qParams = new HashMap<>();
        qParams.put("programme","Financial Analysis");
        qParams.put("limit",2);
        Response response = given()

                //.queryParam("programme","Financial Analysis")
               // .queryParam("limit",2)
                .queryParams(qParams)
                .when()
                .get("/3");
        response.then().statusCode(200);
        response.prettyPrint();

    }


}

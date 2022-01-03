package com.bestbuy.storehomework;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AssertStoreHW {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }
    @Test  //Verify the if the total is equal to 1561
    public void test001(){
        response.body("total",equalTo(1561));
    }
    @Test //Verify the if the stores of limit is equal to 10
    public void test002(){
        response.body("limit",equalTo(10));
    }
    @Test //Check the single ‘Name’ in the Array list (Inver Grove Heights)
    public void test003(){
    response.body("data.name",hasItem("Inver Grove Heights"));
    }
    @Test //Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
     public void test004(){
        response.body("data.name",hasItems("Roseville","Burnsville","Maplewood"));
    }
    @Test  //Verify the storied=7 inside storeservices of the third store of second services
    public void test005(){
     // response.body("data[2].services[1].storeservices",hasEntry("storeId",7));
      response.body("data[2].services[1].storeservices.storeId",equalTo(7));
    }
    @Test //Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    public void test006(){
        //response.body("data.findAll{it.name=='Roseville'}.services.storeservices[0]",hasItem(hasKey("createdAt")));
        response.body("data.findAll{it.name=='Roseville'}.services.storeservices",hasItem(hasItem(hasKey("createdAt"))));
    }
    @Test //Verify the state = MN of forth store
    public void test007(){
        response.body("data[3].state",equalTo("MN"));
    }
    @Test //Verify the store name = Rochester of 9th store
    public void test008(){
        response.body("data[8]",hasEntry("name","Rochester"));
    }
    @Test //Verify the storeId = 11 for the 6th store
    public void test009(){
    response.body("data[5].id",equalTo(11));
    }
    @Test //Verify the serviceId = 4 for the forth service of the 7th store
    public void test010(){
       // response.body("data[7].services[3].id",equalTo(4));
        response.body("data[7].services[3]",hasEntry("id",4));
    }

}

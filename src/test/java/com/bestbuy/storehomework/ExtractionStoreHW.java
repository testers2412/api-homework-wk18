package com.bestbuy.storehomework;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.event.ListDataEvent;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ExtractionStoreHW {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                //    .then().log().all(); // to print the response
                .then().statusCode(200);
    }

    @Test //Extract the limit
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println(limit);
    }

    @Test //Extract the total
    public void test002() {
        int total = response.extract().path("total");
        System.out.println(total);
    }

    @Test //Extract the name of 5th store
    public void test003() {
        String storeName = response.extract().path("data[4].name");
        System.out.println(storeName);
    }

    @Test //Extract the names of all the store
    public void test004() {
        List<String> storeNames = response.extract().path("data.name");
        System.out.println(storeNames);
    }

    @Test //Extract the storeId of all the store
    public void test005() {
        List<Integer> storeIDs = response.extract().path("data.services.storeservices.storeId");
        System.out.println(storeIDs);
    }

    @Test //Print the size of the data list
    public void test006() {
        List<HashMap<String, ?>> data = response.extract().path("data");
        int listSize = data.size();
        System.out.println(listSize);
    }

    @Test //Get all the value of the store where store name = St Cloud
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='St Cloud'}");

        System.out.println(values);
    }

    @Test //Get the address of the store where store name = Rochester
      public void test008(){
        List<String> address= response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println(address);
    }
    @Test //Get all the services of 8th store
    public void test009(){
        List<HashMap<String,?>> services= response.extract().path("data[7].services");
        System.out.println(services);
    }
    @Test  //Get storeservices of the store where service name = Windows Store
    public void test010(){
       List<HashMap<String,?>> storeServices= response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.storeservices");
       System.out.println(storeServices);
    }
    @Test //Get all the storeId of all the store
    public void test011(){
         List<Integer> storeIDs=   response.extract().path("data.services.storeservices.storeId");
        System.out.println(storeIDs);
    }
    @Test //Get id of all the store
    public void test012(){
       List<Integer> ids= response.extract().path("data.id");
        System.out.println(ids);
    }
    @Test //Find the store names Where state = ND
    public void test013(){
    List<String> storeNames= response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println(storeNames);
    }
    @Test //Find the Total number of services for the store where store name = Rochester
    public void test014(){
       /* List<List<HashMap<String ,?>>> services= response.extract().path("data.findAll{it.name=='Rochester'}.services");
         List<HashMap<String,?>>numOfServ= services.get(0);*/
        List<HashMap<String,?>> numOfServ=response.extract().path("data.findAll{it.name=='Rochester'}.services[0]");
        System.out.println(numOfServ.size());
    }
    @Test //Find the createdAt for all services whose name = “Windows Store”
    public void test015(){
     List<String > createdAt= response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.storeservices.createdAt");
        System.out.println(createdAt);
    }
    @Test //Find the name of all services Where store name = “Fargo”
    public void test016(){
        List<String > names= response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println(names);
    }
 @Test //Find the zip of all the store
    public void test017(){
    List<String> zips= response.extract().path("data.zip");
     System.out.println(zips);
    }
 @Test //Find the zip of store name = Roseville
    public void test018(){
   List<String > zips= response.extract().path("data.findAll{it.name=='Roseville'}.zip");
     System.out.println(zips);
    }
 @Test //Find the storeservices details of the service name = Magnolia Home Theater
    public void test019(){
    List<HashMap<String,?>> storeServices= response.extract().path("data.findAll{it.services.findAll{it.name=='Magnolia Home Theater'}}.services.storeservices[0]");
     System.out.println(storeServices);
    }
 @Test //Find the lat of all the stores
    public void test020(){
    List<Integer> lat= response.extract().path("data.lat");
     System.out.println(lat);
    }

}

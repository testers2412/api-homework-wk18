package com.bestbuy.extractingresponsedata;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;


public class SearchJsonPathExample {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                //    .then().log().all(); // to print the response
                .then().statusCode(200);

    }


    // 1) Extract the value of limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2) Extract the list of IDs of all products
    @Test
    public void test002() {
        List<Integer> idList = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + idList);
        System.out.println("------------------End of Test---------------------------");

    }

    // 3) Extract first product name from data by providing list index value
    @Test
    public void test003() {
        String productName1 = response.extract().path("data[0].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The first product name is : " + productName1);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Get the categories list of the first data
    @Test
    public void test004() {
             List<HashMap<String,?>> category=  response.extract().path("data[0].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Categories list are : "+category);
        System.out.println("------------------End of Test---------------------------");

    }

    // 5)Print the size of data
    @Test
    public void test005() {
      int dataSize= response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : "+dataSize);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Get All the products Name from data
    @Test
    public void test006() {
     // List<List<String > >names= response.extract().path("data.categories.id");
       List<String > names = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the products are : "+names);
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get all the values for Name == Duracell - AA Batteries (8-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='Duracell - AA Batteries (8-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Duracell - AA Batteries (8-Pack)' are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the price for product Name == Duracell - D Batteries (4-Pack)
    @Test
    public void test008() {
        List<Integer> price = response.extract().path("data.findAll{it.name=='Duracell - D Batteries (4-Pack)'}.price");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The price of product name 'Duracell - D Batteries (4-Pack)' is : " + price);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get the Names of products which have price less than 16.99
    @Test
    public void test009() {
        List<String> names = response.extract().path("data.findAll{it.price<16.99}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of products that price is less than 16.99 are: " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get the manufacturer of products whose name Start = McV
    @Test
    public void test010() {

        List<String> manufacturers = response.extract().path("data.findAll{it.name==~/Ene.*/}.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of products whose name Start = Ene are: "+manufacturers);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get the price of products whose name end with = Vehicles
    @Test
    public void test011() {
//List<Integer> prices= response.extract().path("data.findAll{it.name==~/.*vehic.*/}.price");
List<Integer> prices= response.extract().path("data.findAll{it.name==~/.*Black/}.price");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The prices of products whose name end with = Vehicles are: "+prices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 12) Get the id of product whose name is 'Energizer - N Cell E90 Batteries (2-Pack)'
    @Test
    public void test012() {
     //List<Integer> id= response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}.id");
       List<HashMap<String,?>> productMapList=  response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}");
       HashMap<String,?> productMap= productMapList.get(0);
        int id=(Integer)  productMap.get("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of product whose name 'Energizer - N Cell E90 Batteries (2-Pack)' is : "+id);
        System.out.println("------------------End of Test---------------------------");
    }

}

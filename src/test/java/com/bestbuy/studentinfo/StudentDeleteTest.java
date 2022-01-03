package com.bestbuy.studentinfo;

import com.bestbuy.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest extends TestBase {
    @Test
    public void deleteDataWithDelete() {
           given()
                   .header("Content-Type","application/json")
                   .pathParam("id",103)
                   .when()
                   .delete("/{id}")
                   .then().log().all();
    }

}

package WebAutomation;

import io.reactivex.rxjava3.internal.operators.maybe.MaybeZipArray;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Login {
    private static final String AUTH_TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiJ0LTIzMDI3YWJkLWU4NTEtNGJhNy05MzExLTkxMjZkMmMxYTJhNyIsImlzcyI6ImFub255bW91c19hdXRoIiwiaWF0IjoxNzI3MTY4NDM4LCJleHAiOjE3OTAyNDA0Mzh9.GmLP1ZcKZ1FwSpz4e5rkkiJGIunt9iX27ItcT6IuZwNDUHudlqGrKhCIGHIme78aVx72CfqNefngQ-z70RmQsw";

    @Test
            public void test(){
      given()
              .baseUri("https://fourdoor-catalog-service-qa.fourdoor.dev")
              .header("Content-Type","application/json")
                      .when()
              .get("/api/v1/category")
              .then()
              .statusCode(200);
    }

    @Test
    public void test1(){
        given()
                .baseUri("https://fourdoor-oms-service-qa.fourdoor.dev")
                .header("Content-Type", "application/json")
                .header("Authorization" ,AUTH_TOKEN)
                .body("{\n" +
                        "    \"customerId\": \"t-f015f82e-0f1c-4750-bf97-e03438d6139d\",\n" +
                        "    \"regNum\": \"HR26EM0754\",\n" +
                        "    \"mmvId\": 5\n" +
                        "}")

                .when()
                .post("/api/v1/customer/vehicles")
                .then()
                .statusCode(200);
    }

}















        // addtion of two martix
     /*   int[][] Marks1 = {{9,3,4},
                {8,4,9}};
        int[][] Marks2 = {{9,5,9},
                {3,7,9}};
        int [][] result = {{0,0,0},{0,0,0}};
        for (int i = 0; i < Marks1.length; i++) {
            for (int j = 0; j < Marks1[i].length; j++) {
                result[i][j] = Marks1[i][j] + Marks2[i][j];
         System.out.print(result[i][j] + " ");

            }
            System.out.println("");

        }*/

//        String [] marks ={"abcd","cdef"};
//        for (int i = marks.length-1 ; i >= 0 ; i-- ){
//            System.out.print(" " + marks[i]);
//        }










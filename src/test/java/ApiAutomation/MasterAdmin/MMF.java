package ApiAutomation.MasterAdmin;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.*;

public class MMF {
    private static RequestSpecification requestSpecification;
    private static String authToken;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("baseURI_MasterAdmin"); // Load base URL from properties
        authToken = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IlNLMmY4QXlYNlBkWHcyQlVIT0lnNXRGYlNGa0s3IiwidHlwIjoiSldUIn0.eyJhbXIiOlsib2F1dGgiXSwiZGVwYXJ0bWVudCI6bnVsbCwiZHJuIjoiRFMiLCJlbWFpbCI6InRhbnlhLmFnYXJ3YWxAY2FyczI0LmNvbSIsImVtcGxveWVlQ29kZSI6bnVsbCwiZXhwIjoxNzQxNjkzMjI1LCJmaXJzdE5hbWUiOm51bGwsImZvdXJkb29yVXNlcklkIjoiNzkiLCJpYXQiOjE3NDEwODg0MjUsImlzcyI6IlAyZjhBeUhoN0I0Z1VVNVRDRlpZQlZNRUNJYzYiLCJsYXN0TmFtZSI6bnVsbCwibmFtZSI6IlRhbnlhIiwib3JnYW5pemF0aW9uTmFtZSI6bnVsbCwicGVybWlzc2lvbnMiOlsiT1JERVJfTU9ESUZZIiwiT1JERVJfQ1JFQVRFIiwiT1JERVJfVklFVyIsIkNPVVBPTl9DUkVBVEUiLCJDT1VQT05fTU9ESUZZIiwiQ09VUE9OX1ZJRVciLCJDQUxMQkFDS19WSUVXIiwiQ0FMTEJBQ0tfTU9ESUZZIiwiQUNUSVZJVFlfTUFTVEVSX0FMTCIsIkFDVElWSVRZX1pPTkVfTUFTVEVSX0FMTCIsIkFDVElWSVRZX01BU1RFUl9FRElUIiwiQUNUSVZJVFlfWk9ORV9WSUVXIiwiQUNUSVZJVFlfWk9ORV9FRElUIiwiV1RCRF9NQVNURVJfVklFVyIsIldUQkRfTUFTVEVSX0VESVQiLCJTQUNfVklFVyIsIlNBQ19FRElUIiwiQUNUSVZJVFlfTUFTVEVSX1ZJRVciLCJVU0VSX01BU1RFUl9WSUVXIiwiVVNFUl9NQVNURVJfRURJVCIsIldPUktTSE9QX1ZJRVciLCJXT1JLU0hPUF9FRElUIiwiQjJCX01BU1RFUl9WSUVXIiwiQjJCX01BU1RFUl9FRElUIiwiQ0lUWV9BREQiLCJCT09LSU5HX1ZJRVciLCJCT09LSU5HX0NSRUFUSU9OIiwiQk9PS0lOR19DT05GSVJNQVRJT04iLCJDQVJfTUFTVEVSX1ZJRVciLCJDQVJfTUFTVEVSX0VESVQiLCJQQUNLQUdFU19NQVNURVJfVklFVyIsIlBBQ0tBR0VTX01BU1RFUl9FRElUIiwiRkFRX1ZJRVciLCJGQVFfRURJVCIsIlNQRUNJQUxJWkFUSU9OX1ZJRVciLCJTUEVDSUFMSVpBVElPTl9FRElUIiwiUEFDS0FHRV9VU1BfRURJVCIsIlBBQ0tBR0VfVVNQX1ZJRVciLCJGSUxFX1VQTE9BRCIsIlBBQ0tBR0VfVklFVyIsIlBBQ0tBR0VfRURJVCIsIklOU1VSQU5DRV9FRElUIiwiSU5TVVJBTkNFX1ZJRVciLCJTQl9ISUVSQVJDSFlfVklFVyIsIldPUktTSE9QX0NSRUFURSIsIk1NRl9DUkVBVEUiLCJNTUZfVklFVyIsIk1NRl9FRElUIiwiQlVMS19VUExPQURfRURJVCIsIkJVTEtfVVBMT0FEIiwiQlVMS19ET1dOTE9BRCJdLCJwaG9uZU51bWJlciI6Iis5MTgzNjg4NDM4MTQiLCJyZWdpb25JZCI6bnVsbCwicmVnaW9uTmFtZSI6bnVsbCwicmV4cCI6IjIwMjUtMDMtMThUMTE6NDA6MjVaIiwicm9sZSI6bnVsbCwicm9sZXMiOlsiRkRfQURNSU4iXSwic3RvcmVJZCI6bnVsbCwic3RvcmVJZHMiOm51bGwsInN1YiI6IlUyZzhEUlhKOHlnc09KY2xPS1VBNDNJRGNVaTEiLCJ1c2VySWQiOm51bGx9.Oc9gH8yHBrlOr9D7UHXAy-d4u12tELGoSjv70YE3bsnIW35vH-vVxvkpQgw5TjttDMx9rRgP8mg89iUuIogj6LKFAGHYQr0xyKSB41jAmtQdSDPPIG6YdidPBEwwuUo3BJuooZvzCvvzsFtWjlqfsl1oH58gIbt62ZVSK6MkCwVvjHUcVZJ51w3g_CJbYUlwIBen-HVuwCbjDOwSJalv0IjXxNtWUmAdM2Lw8pYm4oQda6WivjmHASB9naoCbDihgDSmmvXEh87ixUK4fdJLs1BokJL1Vx_nJoUGtru-TJU94UVg0S915A82Stja91ofTLgGEg-qwpZr2Tg59bwsTA";
        requestSpecification = RestAssured.given()
                .header("Authorization", authToken)
                .header("Content-Type", "application/json");
    }

    @ParameterizedTest
    @CsvSource({
            "/v1/mmf, 200",
            "/v1/mmf/makes, 200",
            "/v1/mmf/make/14/models, 200",
            "/v1/mmf/model/1/fuel, 200"
    })
    public void testValidGetRequests(String endpoint, int expectedStatusCode) {
        System.out.println("Base URI: " + RestAssured.baseURI);
        String fullUrl = RestAssured.baseURI + endpoint;
        Response response = requestSpecification.when().get(endpoint);
        response.getBody().prettyPrint();
        assertAll("Response Validations",
                () -> assertEquals(expectedStatusCode, response.getStatusCode(),
                        "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode()),
                () -> assertNotNull(response.getBody().asString(), "Response body should not be null")
        );
    }}

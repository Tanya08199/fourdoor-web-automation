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
        authToken = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IlNLMmY4QXlYNlBkWHcyQlVIT0lnNXRGYlNGa0s3IiwidHlwIjoiSldUIn0.eyJhbXIiOlsib2F1dGgiXSwiZGVwYXJ0bWVudCI6bnVsbCwiZHJuIjoiRFMiLCJlbWFpbCI6InRhbnlhLmFnYXJ3YWxAY2FyczI0LmNvbSIsImVtcGxveWVlQ29kZSI6bnVsbCwiZXhwIjoxNzQ0MDg5NTE5LCJmaXJzdE5hbWUiOm51bGwsImZvdXJkb29yVXNlcklkIjoiNzkiLCJpYXQiOjE3NDM0ODQ3MTksImlzcyI6IlAyZjhBeUhoN0I0Z1VVNVRDRlpZQlZNRUNJYzYiLCJsYXN0TmFtZSI6bnVsbCwibmFtZSI6IlRhbnlhIEFnYXJ3YWwiLCJvcmdhbml6YXRpb25OYW1lIjpudWxsLCJwZXJtaXNzaW9ucyI6WyJPUkRFUl9NT0RJRlkiLCJPUkRFUl9DUkVBVEUiLCJPUkRFUl9WSUVXIiwiQ0FMTEJBQ0tfVklFVyIsIkNBTExCQUNLX01PRElGWSIsIlVTRVJfTUFTVEVSX1ZJRVciLCJXT1JLU0hPUF9WSUVXIiwiQjJCX01BU1RFUl9WSUVXIiwiQk9PS0lOR19WSUVXIiwiQk9PS0lOR19DUkVBVElPTiIsIkJPT0tJTkdfQ09ORklSTUFUSU9OIiwiQ0FSX01BU1RFUl9WSUVXIiwiQ0FSX01BU1RFUl9FRElUIiwiUEFDS0FHRV9WSUVXIiwiU0JfSElFUkFSQ0hZX1ZJRVciXSwicGhvbmVOdW1iZXIiOiIrOTE4MzY4ODQzODE0IiwicmVnaW9uSWQiOm51bGwsInJlZ2lvbk5hbWUiOm51bGwsInJleHAiOiIyMDI1LTA0LTE1VDA1OjE4OjM5WiIsInJvbGUiOm51bGwsInJvbGVzIjpbIkZEX1NFUlZJQ0VfQlVERFkiXSwic3RvcmVJZCI6bnVsbCwic3RvcmVJZHMiOm51bGwsInN1YiI6IlUyZzhEUlhKOHlnc09KY2xPS1VBNDNJRGNVaTEiLCJ1c2VySWQiOm51bGx9.D9lcRwXonUVSvrjjec17suIBqccFwFtKZbkRcCQgINhNmzYUOpei8u_TKgJr2V4X9habESYjODe4_2UsZlIjykb7v-PkQ68ugi-hIFvN9gNozWMvh3peU2alA7-YptGpUQeT4k5n19Z8zx3QF4Ijbva-5uVrm2lSzRi6DBCCRanCLNmF3FscrxtvhHGo8MwjxFZ3mi3nQUV9NwnOVjwQbvplJ6rL62T8KReKf80WIh4yKWKwi2jJWY34TEKOnZwN_4uS4wktiGuh0IHPfTG3zA4k716pvIOVb8LrOpAc304wDMPcXJ4Zq3BqmdyKY8KQ1igzdrRdyfL2TrjxVSua0g";
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

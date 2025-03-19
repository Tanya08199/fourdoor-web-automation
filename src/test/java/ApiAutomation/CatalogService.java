package ApiAutomation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogService {

    private static RequestSpecification requestSpecification;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("baseURI_Catalog"); // Load base URL from properties
        requestSpecification = RestAssured.given();
    }

    @ParameterizedTest
    @CsvSource({
            "/api/v1/category/super, 200",
            "/api/v1/category/super, 200",
            "/api/v1/category, 200",
            "/api/v1/category/super, 200",
            "/api/v1/packages/listing, 200",
            "/api/v1/packages/P44, 200",
            "/public/api/v1/content, 200",
            "/api/v1/makes, 200",
            "/api/v1/make/1/models, 200",
            "/api/v1/make/model/6/fuel, 200",
            "/api/v1/vehicle-number/HR26EM0752, 200",
            "/api/v1/make/model?search=maruti, 200",
            "/public/health, 200",
            "/api/v1/mmfv?make=Tata&fuel=Petrol&model=Tiago, 200",
            "/api/v1/mmv/1, 200",
            "/api/v1/packages/added-together?packageCodes=P1, 200",
            "/api/v1/service/all, 200",
            "/api/v1/package/pricing?pricingCodes=PP15, 200",
            "/api/v1/packages/added-together?packageCodes=P1, 200",
            "/v3/vehicle-number/HR26DS7314, 200",
            "/api/v1/packages/conflict?packageCodes=P1, 200",
            "/api/v1/packages/top, 200"
    })
    public void testValidGetRequests(String endpoint, int expectedStatusCode) {
        Response response = requestSpecification.when().get(endpoint);
        assertAll("Response Validations",
                () -> assertEquals(expectedStatusCode, response.getStatusCode(),
                        "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode()),
                () -> assertNotNull(response.getBody().asString(), "Response body should not be null")
        );

        System.out.println("Response from " + endpoint + ":");
        response.getBody().prettyPrint();
    }

    @ParameterizedTest
    @CsvSource({
            "/api/v1/invalidEndpoint, 404",
            "/api/v1/packages/nonexistent, 404"
    })
    public void testInvalidEndpoints(String endpoint, int expectedStatusCode) {
        Response response = requestSpecification.when().get(endpoint);

        assertEquals(expectedStatusCode, response.getStatusCode(),
                "Expected 404 but got " + response.getStatusCode() + " for endpoint: " + endpoint);

        System.out.println("Invalid endpoint response from " + endpoint + ": " + response.getBody().asString());
    }

    @ParameterizedTest
    @CsvSource({
            "/api/v1/category/super",
            "/api/v1/packages/top"
    })
    public void testApiResponseTime(String endpoint) {
        Response response = requestSpecification.when().get(endpoint);
        assertTrue(response.getTime() < 2000, "API response took too long: " + response.getTime() + "ms");
        System.out.println("API response time for " + endpoint + ": " + response.getTime() + "ms");
    }
}

package ApiAutomation.MasterAdmin;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FetchAPIFieldNames {
    public static void main(String[] args) {
        // List of API endpoints
        String[] endpoints = {
                "/api/v1/category/super",
                "/api/v1/category",
                "/api/v1/packages/listing",
                "/api/v1/packages/P44",
                "/public/api/v1/content",
                "/api/v1/makes",
                "/api/v1/make/1/models",
                "/api/v1/make/model/6/fuel",
                "/api/v1/vehicle-number/HR26EM0752",
                "/api/v1/make/model?search=maruti",
                "/public/health",
                "/api/v1/mmfv?make=Tata&fuel=Petrol&model=Tiago",
                "/api/v1/mmv/1",
                "/api/v1/packages/added-together?packageCodes=P1",
                "/api/v1/service/all",
                "/api/v1/package/pricing?pricingCodes=PP15",
                "/api/v1/packages/conflict?packageCodes=P1",
                "/api/v1/packages/top"
        };

        // Base URL
        RestAssured.baseURI = "https://fourdoor-catalog-service-qa.fourdoor.dev";  // Change this to your API base URL

        for (String endpoint : endpoints) {
            System.out.println("\nFields in response for: " + endpoint);
            fetchResponseFieldNames(endpoint);
        }
    }

    private static void fetchResponseFieldNames(String endpoint) {
        Response response = RestAssured.given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(200) // Ensure response is OK
                .extract()
                .response();

        String jsonString = response.asString();
        ObjectMapper objectMapper = new ObjectMapper();
        Set<String> fieldNames = new HashSet<>();

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            extractFieldNames("", jsonNode, fieldNames);
        } catch (IOException e) {
            System.out.println("Error parsing JSON response: " + e.getMessage());
        }

        // Print field names
        for (String field : fieldNames) {
            System.out.println(field);
        }
    }

    private static void extractFieldNames(String parentKey, JsonNode jsonNode, Set<String> fieldNames) {
        if (jsonNode.isObject()) {
            // Use while loop instead of for-each
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String newKey = parentKey.isEmpty() ? entry.getKey() : parentKey + "." + entry.getKey();
                fieldNames.add(newKey);
                extractFieldNames(newKey, entry.getValue(), fieldNames);
            }
        } else if (jsonNode.isArray()) {
            for (JsonNode arrayElement : jsonNode) {
                extractFieldNames(parentKey, arrayElement, fieldNames);
            }
        }
    }

}

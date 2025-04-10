package ApiAutomation.validation;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class UserApiTest {

    @Test
    public void testUserResponseValidation() {

        Response response = RestAssured.get("https://fourdoor-catalog-service-qa.fourdoor.dev/api/v1/category/super");
        System.out.println("RAW RESPONSE: " + response.asString());

        List<Map<String, Object>> dataList = response.jsonPath().getList("data");

        Map<String, FieldValidator> validators = new HashMap<>();
        validators.put("isVisible", new BooleanValidator());
        validators.put("cityCode", new StringValidator());

        JsonValidator validator = new JsonValidator(validators);

        boolean allValid = true;
        for (Map<String, Object> item : dataList) {
            if (!validator.validate(item)) {
                allValid = false;
            }
        }

        assertTrue("❌ JSON validation failed!", allValid);

    }
}

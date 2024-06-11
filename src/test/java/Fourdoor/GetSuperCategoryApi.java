package Fourdoor;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.RestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetSuperCategoryApi {


    @Test
    public void getSuperCategory() throws IOException {

        Map<String,String> data = jsonUtils.jsonUtils.getJsonDataAsMap("/Fourdoor/QA/fourdoorApiData.json");
        String endPoint = data.get("fourdoorEndpoint");
        Map<String,String> headers = new HashMap<>();
        headers.put("FD_CITY_CODE","gurugram");
        Response response = RestUtils.performGet(endPoint,headers);
        System.out.println(response.getBody().asString());
    }
}

package org.example;

import java.util.HashMap;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;


public class GetInspectionHistory {

    public static void main(String[] args) {

        String URL = "https://stage-refurb-app-gateway.qac24svc.dev/inspection-list/INSPECTION-HISTORY?page=0&size=20&lang=en";


        Map<String, String> headers = new HashMap<>();
        headers.put("authorization","Bearer eyJraWQiOiJ0eGFEQ25sTHJsRXhrX29RcEpqcWtyY3JrblBPREpOVEFQTV9Wc0Z5YXB3IiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULmhKZGFSbXhFMU1iLS0xd1JScV9SVWVEQVgtRzU5UkxjWVUtWDRRZ1BNZ1kiLCJpc3MiOiJodHRwczovL2NhcnMyNC5va3RhcHJldmlldy5jb20vb2F1dGgyL2RlZmF1bHQiLCJhdWQiOiJhcGk6Ly9kZWZhdWx0IiwiaWF0IjoxNzEzOTU3OTQ4LCJleHAiOjE3MTQwNDQzNDgsImNpZCI6IjBvYXhhdmE0MUw5NUZNRTdJMWQ2IiwidWlkIjoiMDB1M2YzbGFlZEZha0d0ckoxZDciLCJzY3AiOlsiZW1haWwiLCJwaG9uZSIsIm9wZW5pZCIsInByb2ZpbGUiXSwiYXV0aF90aW1lIjoxNzEzOTU3OTQ2LCJzdWItbmFtZSI6ImVzdGltYXRvci5tcmxAY2FyczI0LmNvbSIsImxhc3ROYW1lIjoiTVJMIiwic3ViIjoiZXN0aW1hdG9yLm1ybEBjYXJzMjQuY29tIiwiY291bnRyeSI6WyJJTiJdLCJmaXJzdE5hbWUiOiJFc3RpbWF0b3IiLCJURVNUX0NSRUFURV9NT1ZFTUVOVCI6ZmFsc2UsImdyb3VwcyI6WyJFdmVyeW9uZSIsIkNBVEFMT0dfRVZBTFVBVE9SIiwiV0FSUkFOVFlfQ09TVF9BUFBST1ZBTF8zIiwiV0FSUkFOVFlfQlVERFkiLCJDQVRBTE9HX0VTVElNQVRPUl9NUkwiXSwibG9jYXRpb25zIjpbIklOX0NBUl9DQ180NzA5X1NDXzE1IiwiSU5fQ0FSX0NDXzE2OTJfU0NfNiIsIklOX0NBUl9DQ18zNjg2X1NDXzciXSwiem9uZXMiOltdLCJ2ZWhpY2xlVHlwZSI6WyJDQVIiXX0.KSzlMi4KEjOaMzCGaW-y6MgYheQ3QYmRz88KtTj_GPyGdqrGOzAAsW1OtXVW78c8UkHkwDLvompurHj7TV27uLpkBvtqLo44qG6_ahlpCdW60QfdWxKiGhObeeSAsdkjYxZmm9GL7p1Q469Tg-NIRyIvLKzsX-TY8eV5Jy1iHFmXyM3dXgM_9gkcc-FHH45cOzUM7k3YEnIA_T4A843VVDP1foG_5RlXcUh-Us_VwLfcvojVR-Gwme9VFhB48SQB0lOSierWqtce2E5rtxRXURH5PBSU4DzH8TJYI39LGMM4ZN2-zLg7iYTuAPnEYMGdLQgKRJKHdrBwdnjP33Msfw");
        headers.put("x_country","IN");
        headers.put("x_vehicle_type","CAR");

        Response response = RestAssured.given().headers(headers).get(URL);

        String responseBody = response.getBody().asString();

        System.out.println(responseBody);

    }
}














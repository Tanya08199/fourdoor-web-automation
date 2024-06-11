package utils;

import Reporting.ExtentReportManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.Map;

public class RestUtils {

    private static RequestSpecification getRequestSpecification(String endPoint, Map<String, String> headers)
    {
       return RestAssured.given().baseUri(endPoint).headers(headers);
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification)
    {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("End point is "+ queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("Method  is "+ queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Headers are "+ queryableRequestSpecification.getHeaders().asList().toString());

    }

    private static void printResponseLogInReport(Response response)
    {
        ExtentReportManager.logInfoDetails("Response status code "+ response.getStatusCode() );
        ExtentReportManager.logInfoDetails("Response headers is "+ response.getHeaders().asList().toString());
        ExtentReportManager.logInfoDetails("Response body "+ response.getBody().asString());
    }

    public static Response performGet(String endPoint, Map<String, String> headers)
    {
       RequestSpecification requestSpecification = getRequestSpecification(endPoint,headers);
       Response response =  requestSpecification.get();
       printRequestLogInReport(requestSpecification);
       printResponseLogInReport(response);
       return response;

    }
}

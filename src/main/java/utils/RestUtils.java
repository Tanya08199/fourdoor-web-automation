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
        ExtentReportManager.logInfoDetails("Headers are ");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());

    }

    private static void printResponseLogInReport(Response response)
    {
        ExtentReportManager.logInfoDetails("Response status code "+ response.getStatusCode() );
        ExtentReportManager.logInfoDetails("Response headers is ");
        ExtentReportManager.logHeaders(response.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Response body ");
        ExtentReportManager.logInfoJson(response.getBody().prettyPrint());
    }

    public static Response performGet(String endPoint, Map<String, String> headers)
    {
       RequestSpecification requestSpecification = getRequestSpecification(endPoint,headers);
       Response response =  requestSpecification.get();
       printRequestLogInReport(requestSpecification);
       printResponseLogInReport(response);
       return response;

    }


    public static Response performGetWithParams(String endPoint, Map<String, String> headers, Map<String,String> pathParams)
    {
        RequestSpecification requestSpecification = getRequestSpecification(endPoint,headers).pathParams(pathParams);
        Response response = requestSpecification.get("{param1}");
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;

    }
}

package Fourdoor;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import jsonUtils.jsonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.POJO.*;
import utils.RestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetSuperCategory {

  private Map<String, String> headers;
  private String endPoint;


  @BeforeClass
  public void setUp() throws IOException {
    Map<String,Object> data = jsonUtils.getJsonDataAsMap("/Fourdoor/QA/fourdoorApiData.json");
    endPoint = (String) data.get("GetSuperCategory");
    headers = new HashMap<>();
    headers.put("FD_CITY_CODE","gurugram");
  }


  @Test
  public void checkResponseCode() throws IOException {

    long startTime = System.currentTimeMillis();

    Response response = RestUtils.performGet(endPoint,headers);

    long endTime = System.currentTimeMillis();
    long responseTime = endTime-startTime;

    System.out.println("The response time for checkResponseCode is "+ responseTime + " ms");


    int statusCode = response.getStatusCode();

    Assert.assertEquals(statusCode, 200, "Correct status code returned");
  }

  @Test
  public void getSuperCategoryDetails() throws IOException {

    long startTime = System.currentTimeMillis();
    Response response = RestUtils.performGet(endPoint,headers);
    long endTime = System.currentTimeMillis();
    long responseTime = endTime-startTime;
    System.out.println("The response time for getSuperCategoryDetails is "+ responseTime + " ms");

    GetSuperCategoryResponse getSuperCategoryResponse = response.as(GetSuperCategoryResponse.class);
    for(SuperCategory superCategory : getSuperCategoryResponse.getData())
    {
      String categoryCode = superCategory.getCategoryCode();
      System.out.println("The super category code is "+categoryCode);
      String categoryName = superCategory.getName();
      System.out.println("The super category name is "+categoryName);

      MetaData metadata = superCategory.getMetadata();
      if(metadata != null)
      {
        String h1 = metadata.getH1();
        System.out.println("The h1 is "+h1);
        String title = metadata.getTitle();
        System.out.println("The title is "+title);
        String desc = metadata.getDescription();
        System.out.println("The description is "+desc);
      }
    }

/*
    JSONObject jsonObject = new JSONObject(response.getBody().asString());
    JSONArray superCategoryData = jsonObject.getJSONArray("data");

   for (int i = 0; i < superCategoryData.length(); i++) {
      JSONObject superCategoryResponse = superCategoryData.getJSONObject(i);
      int id = superCategoryResponse.getInt("id");
      String superCategoryName = superCategoryResponse.getString("name");
      System.out.println("Id is :-" + id);
      System.out.println("Super Category Name :-" + superCategoryName);
      if (superCategoryResponse.has("metadata")) {
        JSONObject superCategoryMetaData = superCategoryResponse.getJSONObject("metadata");
        String h1 = superCategoryMetaData.getString("h1");
        String title = superCategoryMetaData.getString("title");
        String desc = superCategoryMetaData.getString("description");
        System.out.println("Heading H1 :-" + h1);
        System.out.println("Title is :-" + title);
        System.out.println("Description is :-" + desc);

      }

    }

 */
  }

  @DataProvider(name = "slugs")
  public Object[][] createSlugs() throws IOException {
    List<String> slugs = jsonUtils.getSlugs("/Fourdoor/QA/CategoryName.json");
    Object[][] data = new Object[slugs.size()][1];
    for (int i= 0 ;i < slugs.size(); i++)
    {
      data[i][0] = slugs.get(i);
    }
    return data;

  }

  @Test(dataProvider = "slugs")
  public void getCategoryBasedOnSuper(String slug) throws IOException {

    long startTime = System.currentTimeMillis();
    Map<String,String> pathParams = new HashMap<>();
    pathParams.put("param1",slug);

    Response response = RestUtils.performGetWithParams(endPoint,headers,pathParams);

    long endTime = System.currentTimeMillis();
    long responseTime = endTime-startTime;
    System.out.println("The response time for getCategoryBasedOnSuper is "+ responseTime + " ms");
    Assert.assertEquals(response.getStatusCode(), 200);

    GetSuperCategoryResponse getSuperCategoryResponse = response.as(GetSuperCategoryResponse.class);
    for(SuperCategory superCategory : getSuperCategoryResponse.getData())
    {
      String categoryCode = superCategory.getCategoryCode();
      System.out.println("The category code is "+categoryCode);

      String categoryName = superCategory.getName();
      System.out.println("The category name is "+categoryName);
    }

    /*

    JSONObject jsonObject = new JSONObject(response.getBody().asString());
    JSONArray categoryData = jsonObject.getJSONArray("data");
    for(int i =0; i< categoryData.length(); i++)
    {
      JSONObject categoryResponse = categoryData.getJSONObject(i);
      String name = categoryResponse.getString("name");
      System.out.println("Category name is :-"+ name);

    }
    */

  }


  @Test(dataProvider = "slugs")
  public void getPackageBasedOnCategory(String slug) throws IOException {
    long startTime = System.currentTimeMillis();

    Map<String,String> pathParams = new HashMap<>();
    pathParams.put("param1",slug);

    Response response = RestUtils.performGetWithParams(endPoint,headers,pathParams);
    long endTime = System.currentTimeMillis();
    long responseTime = endTime-startTime;
    System.out.println("The response time for getPackageBasedOnCategory is "+ responseTime + " ms");

    GetCategoryResponse getCategoryResponse = response.as(GetCategoryResponse.class);
    for(Category category : getCategoryResponse.getData())
    {
      String name = category.getName();
      System.out.println("The category name is "+name);
      for(Packages packages : category.getPackages())
      {
        String packagesName = packages.getName();
        System.out.println("The package name is "+packagesName);
      }


    }



/*
    JSONObject jsonObject = new JSONObject(response.getBody().asString());
    JSONArray categoryData = jsonObject.getJSONArray("data");
    for(int i =0; i< categoryData.length(); i++)
    {
      JSONObject categoryResponse = categoryData.getJSONObject(i);
      JSONArray packageData = categoryResponse.getJSONArray("packages");
      for(int j =0; j< packageData.length(); j++)
      {
        JSONObject packageResponse = packageData.getJSONObject(j);
        String name = packageResponse.getString("name");
        System.out.println("Package name is :-" + name);
        JSONObject packageMetaData = packageResponse.getJSONObject("metadata");
        String h1 = packageMetaData.getString("h1");
        System.out.println("Heading h1 for package :-"+h1);
        String title = packageMetaData.getString("title");
        String desc = packageMetaData.getString("description");
        System.out.println("Package title is :- "+title);
        System.out.println("Package description is :- "+desc);

      }
    }*/
  }


}
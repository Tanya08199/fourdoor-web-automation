package Fourdoor;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import jsonUtils.jsonUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtils;
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

   XSSFSheet sheet = ExcelUtils.readExcel("data/TestExcel (1).xlsx","Sheet1");

   for(int i =1 ; i<= sheet.getLastRowNum(); i++)
   {

     String expectedCategoryCode = ExcelUtils.getCellValue(sheet,i,0);
     String expectedCategoryName = ExcelUtils.getCellValue(sheet,i,2);
     String expectedSlug = ExcelUtils.getCellValue(sheet,i,1);
     String expectedCityCode = ExcelUtils.getCellValue(sheet,i,5);
     String expectedSearchRanking = ExcelUtils.getCellValue(sheet,i,6);
     SuperCategory superCategory = getSuperCategoryResponse.getData().get(i-1);

       Assert.assertEquals(expectedCategoryCode, superCategory.getCategoryCode(), "Category code mismatch");
       Assert.assertEquals(expectedCategoryName, superCategory.getName(), "Category name mismatch");
       Assert.assertEquals(expectedSlug, superCategory.getSlug(), "Slug mis match");
       Assert.assertEquals(expectedCityCode,superCategory.getCityCode(), "City code is mismatch");
       Assert.assertEquals(expectedSearchRanking,superCategory.getSearchRanking(), "Search rank is mismatch");


       MetaData metadata = superCategory.getMetadata();
       if (metadata != null) {
         String h1 = metadata.getH1();
         String title = metadata.getTitle();
         String desc = metadata.getDescription();
         Icon icon = metadata.getIcon();
         HeroImage heroImage = metadata.getHeroImage();

         if (icon != null) {
           String pathIcon = icon.getPath();
         }
         if (heroImage != null) {
           String pathHero = heroImage.getPath();
         }
       }
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


  /*
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
    }

  }*/


}
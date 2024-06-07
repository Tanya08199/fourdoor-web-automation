package Fourdoor;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class GetSuperCategory {

  @BeforeTest
  public void setUp() {
    RestAssured.baseURI = "https://fourdoor-catalog-service-qa.fourdoor.dev/api/v1/category/super";
  }

  @Test
  public void checkResponseCode() {
    Response response = RestAssured.given().headers("FD_CITY_CODE", "gurugram").get();

    int statusCode = response.getStatusCode();

    Assert.assertEquals(statusCode, 200, "Correct status code returned");
  }

  @Test
  public void getSuperCategoryDetails() {
    Response response = RestAssured.given().headers("FD_CITY_CODE", "gurugram").get();

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


  }

  @DataProvider(name = "slugs")
  public Object[][] createSlugs() {
    return new Object[][]{
            {"car-service-and-maintenance"},
            {"car-ac-service"},
            {"general-car-inspection"},
            {"bodyshop-dent-and-paint"},
            {"steering-and-suspension"},
            {"engine-and-brakes"},
            {"car-detailing"},
            {"car-wash-and-spa"}
    };
  }

  @Test(dataProvider = "slugs")
  public void getCategoryBasedOnSuper(String slug) {

    Response response = RestAssured.given().pathParam("slug", slug).headers("FD_CITY_CODE", "gurugram").get("{slug}");

    Assert.assertEquals(response.getStatusCode(), 200);

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
  public void getPackageBasedOnCategory(String slug)
  {
    Response response = RestAssured.given().pathParam("slug",slug).headers("FD_CITY_CODE","gurugram").get("{slug}");

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
  }
}
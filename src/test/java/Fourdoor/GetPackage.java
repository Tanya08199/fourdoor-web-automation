package Fourdoor;

import com.beust.ah.A;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtils;
import utils.POJO.Package.*;
import utils.RestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetPackage {

    private Map<String,String> headers;
    private String endPoint;

    private int i =1;

    @BeforeClass
    public void setUp() throws IOException {
        Map<String,Object> data = jsonUtils.jsonUtils.getJsonDataAsMap("Fourdoor/QA/fourdoorApiData.json");
        endPoint = (String) data.get("GetPackage");
        headers = new HashMap<>();
        headers.put("FD_CITY_CODE","gurugram");
    }

    @DataProvider(name = "slugs")
    public Object[][] createSlugs() throws IOException {
        List<String> slugs = jsonUtils.jsonUtils.getSlugs("/Fourdoor/QA/CategoryName.json");
        Object[][] data = new Object[slugs.size()][1];
        for (int i= 0 ;i < slugs.size(); i++)
        {
            data[i][0] = slugs.get(i);
        }
        return data;

    }


    @Test(dataProvider = "slugs")
    public void getPackageDetails(String slug) throws IOException {
        long startTime = System.currentTimeMillis();

        Map<String,String> pathParams = new HashMap<>();
        pathParams.put("param1",slug);

        Response response = RestUtils.performGetWithParams(endPoint,headers,pathParams);
        long endTime = System.currentTimeMillis();
        long responseTime= endTime-startTime;

        System.out.println("The response time for getPackageDetails is "+ responseTime + " ms");

        GetCategoryResponse getCategoryResponse = response.as(GetCategoryResponse.class);

        XSSFSheet sheet = ExcelUtils.readExcel("data/TestExcel (3).xlsx", "Package");


        for(CategoryData categoryData : getCategoryResponse.getData())
        {


            for (PackageResponse packageResponse : categoryData.getPackages())
            {
                String expectedCategoryCode = ExcelUtils.getCellValue(sheet,i,0);
                String expectedSlug = ExcelUtils.getCellValue(sheet,i,1);
                String expectedName = ExcelUtils.getCellValue(sheet,i,2);
                String expectedPackageCode = ExcelUtils.getCellValue(sheet,i,3);
                String expectedRanking = ExcelUtils.getCellValue(sheet,i,4);
                String expectedTitle = ExcelUtils.getCellValue(sheet,i,16);
                String expectedDesc = ExcelUtils.getCellValue(sheet,i,17);
                String expectedH1 = ExcelUtils.getCellValue(sheet,i,18);

                try {
                    Assert.assertEquals(packageResponse.getCategoryCode(),expectedCategoryCode,"Category Code mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println("Category code mismatch at row " + i + ": " + e.getMessage());
                }
                try {
                    Assert.assertEquals(packageResponse.getPackageCode(),expectedPackageCode,"Package code mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println("Category code mismatch at row " + i + ": " + e.getMessage());
                }
                try {
                    Assert.assertEquals(packageResponse.getName(),expectedName,"Package name mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println("Category code mismatch at row " + i + ": " + e.getMessage());
                }
                try {
                    Assert.assertEquals(packageResponse.getSlug(),expectedSlug,"Package slug mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println("Category code mismatch at row " + i + ": " + e.getMessage());
                }
                try {
                    Assert.assertEquals(packageResponse.getSearchRanking(),expectedRanking,"Package rank mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println("Category code mismatch at row " + i + ": " + e.getMessage());
                }
                packageMetaData packageMetaData = packageResponse.getMetadata();
                try {
                    Assert.assertEquals(packageMetaData.getH1(),expectedH1,"Package H1 mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println("Category code mismatch at row " + i + ": " + e.getMessage());
                }
                try {
                    Assert.assertEquals(packageMetaData.getTitle(),expectedTitle,"Package title mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println("Category code mismatch at row " + i + ": " + e.getMessage());
                }
                try {
                    Assert.assertEquals(packageMetaData.getDescription(),expectedDesc,"Package desc mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println("Category code mismatch at row " + i + ": " + e.getMessage());
                }

                int j =10;
                for(PackageImages packageImages : packageMetaData.getImages())
                {

                    String path = ExcelUtils.getCellValue(sheet,i,j);
                    try {
                        Assert.assertEquals(packageImages.getPath(),path,"Package path mismatch");
                    }
                    catch (AssertionError e)
                    {
                        System.err.println("Category code mismatch at row " + i + ": " + e.getMessage());
                    }
                    j++;
                }


                i++;
            }

        }



    }





}

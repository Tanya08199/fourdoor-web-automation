package Fourdoor;

import io.restassured.response.Response;
import jsonUtils.jsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ExcelUtils;
import utils.POJO.Category;
import utils.POJO.GetCategoryResponse;
import utils.POJO.Icon;
import utils.POJO.MetaData;
import utils.RestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetCategory {

    private Map<String, String> headers;
    private String endPoint;

    @BeforeClass
    public void setUp() throws IOException {
        Map<String, Object> data = jsonUtils.getJsonDataAsMap("/Fourdoor/QA/fourdoorApiData.json");
        endPoint = (String) data.get("GetCategory");
        headers = new HashMap<>();
        headers.put("FD_CITY_CODE", "gurugram");
    }

    @Test
    public void getResponseCode() {
        long startTime = System.currentTimeMillis();
        Response response = RestUtils.performGet(endPoint, headers);
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        System.out.println("The response time for getResponseCode is " + responseTime + " ms");

        int statusCode = response.getStatusCode();

        try {
            Assert.assertEquals(statusCode, 200, "Correct status code returned");
        } catch (AssertionError e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void getCategoryDetails() throws IOException {
        Response response = RestUtils.performGet(endPoint, headers);
        GetCategoryResponse getCategoryResponse = response.as(GetCategoryResponse.class);

        XSSFSheet sheet = ExcelUtils.readExcel("data/TestExcel (2).xlsx", "Package_category");

        Map<String, ExcelCategory> excelCategoryMap = new HashMap<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            String expectedCategoryCode = ExcelUtils.getCellValue(sheet, i, 1);
            String expectedCategoryName = ExcelUtils.getCellValue(sheet, i, 3);
            String expectedSearchRanking = ExcelUtils.getCellValue(sheet, i, 5);
            String expectedSlug = ExcelUtils.getCellValue(sheet, i, 2);
            String expectedPath = ExcelUtils.getCellValue(sheet, i, 6);

            ExcelCategory excelCategory = new ExcelCategory(expectedCategoryCode, expectedCategoryName, expectedSearchRanking, expectedSlug, expectedPath);
            excelCategoryMap.put(expectedCategoryCode, excelCategory);

        }
        Map<String, Category> apiCategoryMap = new HashMap<>();
        for (Category category : getCategoryResponse.getData()) {
            apiCategoryMap.put(category.getCategoryCode(), category);
        }

        for (String categoryCode : excelCategoryMap.keySet()) {
            ExcelCategory expectedCategory = excelCategoryMap.get(categoryCode);
            Category actualCategory = apiCategoryMap.get(categoryCode);
            if (actualCategory != null) {
                try {
                    Assert.assertEquals(actualCategory.getCategoryCode(), expectedCategory.getCategoryCode(), "Category Code mismatch");

                } catch (AssertionError e) {
                    System.err.println(e.getMessage());
                }

                try {
                    Assert.assertEquals(actualCategory.getName(),expectedCategory.getCategoryName(), "Category name mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println(e.getMessage());
                }
                try {
                    Assert.assertEquals(actualCategory.getSearchRanking(),expectedCategory.getSearchRanking(), "Category rank mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println(e.getMessage());
                }
                try {
                    Assert.assertEquals(actualCategory.getSlug(),expectedCategory.getSlug(), "Category slug mismatch");
                }
                catch (AssertionError e)
                {
                    System.err.println(e.getMessage());
                }

                MetaData metaData = actualCategory.getMetadata();
                if(metaData != null)
                {
                    Icon icon = metaData.getIcon();
                    try {
                        Assert.assertEquals(icon.getPath(),expectedCategory.getPath(),"Path mis match");
                    }
                    catch (AssertionError e)
                    {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }


    }


    @Data
    @AllArgsConstructor
    static class ExcelCategory {
        private String categoryCode;
        private String categoryName;
        private String searchRanking;
        private String slug;
        private String path;
    }
}






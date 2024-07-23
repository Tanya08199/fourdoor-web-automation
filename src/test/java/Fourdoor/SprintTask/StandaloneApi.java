package Fourdoor.SprintTask;

import io.restassured.response.Response;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ExcelUtils;
import utils.POJO.Standalone.GetStandalone;
import utils.POJO.Standalone.Images;
import utils.POJO.Standalone.LearnMore;
import utils.POJO.Standalone.MetaData;
import utils.POJO.Standalone.StandaloneData;
import utils.RestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StandaloneApi {

    private Map<String, String> headers;
    private String endPoint;
    private static int counter = 1;

    @BeforeClass
    public void setUp() throws IOException {
        Map<String, Object> data = jsonUtils.jsonUtils.getJsonDataAsMap("Fourdoor/QA/fourdoorApiData.json");
        endPoint = (String) data.get("GetStandalone");
        headers = new HashMap<>();
    }

    @DataProvider(name = "slugs")
    public Object[][] createSlugs() throws IOException {
        List<String> slugs = jsonUtils.jsonUtils.getSlugs("/Fourdoor/QA/StandaloneSlug.json");
        Object[][] data = new Object[slugs.size()][1];
        for (int i = 0; i < slugs.size(); i++) {
            data[i][0] = slugs.get(i);
        }
        return data;
    }

    @Test(dataProvider = "slugs")
    public void getStandalone(String slug) throws IOException {

        int i = getAndIncrementCounter();

        System.out.println("Processing entry: " + i);

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("param1", slug);

        long startTime = System.currentTimeMillis();
        Response response = RestUtils.performGetWithParams(endPoint, headers, pathParams);
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        System.out.println("The response time of the API is " + responseTime);

        GetStandalone getStandalone = response.as(GetStandalone.class);

        XSSFSheet sheet = ExcelUtils.readExcel("data/TestExcel (8).xlsx", "Standalone");

        String expectedTitle = ExcelUtils.getCellValue(sheet, i, 2);
        String expectedSlug = ExcelUtils.getCellValue(sheet, i, 5);
        String expectedSuperSlug = ExcelUtils.getCellValue(sheet, i, 4);
        String expectedName = ExcelUtils.getCellValue(sheet, i, 0);
        String expectedPath = ExcelUtils.getCellValue(sheet, i, 3);
        String expectedDesc = ExcelUtils.getCellValue(sheet, i, 6);

        StandaloneData standaloneData = getStandalone.getData();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(standaloneData.getName(), expectedTitle, "Title mismatch");
        softAssert.assertEquals(standaloneData.getSlug(), expectedSlug, "Category Slug mismatch");
        softAssert.assertEquals(standaloneData.getSuperCategorySlug(), expectedSuperSlug, "Super category slug mismatch");

        MetaData metaData = standaloneData.getMetadata();
        for (Images images : metaData.getImages()) {
            softAssert.assertEquals(images.getName(), expectedName, "Name mismatch");
           // softAssert.assertEquals(images.getPath(), expectedPath, "Banner image mismatch");
        }

        LearnMore learnMore = standaloneData.getLearnMore();
        softAssert.assertEquals(learnMore.getDescr(), expectedDesc, "Description mismatch");

        softAssert.assertAll();
    }

    private synchronized int getAndIncrementCounter() {
        return counter++;
    }
}

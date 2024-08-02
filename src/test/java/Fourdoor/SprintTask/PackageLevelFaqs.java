package Fourdoor.SprintTask;

import io.restassured.response.Response;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ExcelUtils;
import utils.POJO.PackageDetails.PackageData;
import utils.POJO.PackageDetails.PackageFaq;
import utils.POJO.PackageDetails.PackageResponse;
import utils.RestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PackageLevelFaqs {

    private Map<String,String> headers;
    private String endPoint;

    @BeforeClass
    public void setUp() throws IOException {
        Map<String,Object> data = jsonUtils.jsonUtils.getJsonDataAsMap("Fourdoor/QA/fourdoorApiData.json");
        endPoint = (String) data.get("GetPackageDetails");
        headers = new HashMap<>();
        headers.put("FD_CITY_CODE","gurugram");
    }

    @Test
    public void getFaqs() throws IOException {
        XSSFSheet sheet = ExcelUtils.readExcel("data/TestExcel (6).xlsx","FQA_Data");
        Map<String, PackageData> packageDataHashMap = new HashMap<>();

        for(int i =1; i<=49; i++)
        {
            long startTime = System.currentTimeMillis();
            Response response = RestUtils.performGet(endPoint + "/" + "P" + i,headers);

            long endTime = System.currentTimeMillis();
            long responseTime = endTime-startTime;

            System.out.println("The response time for api is "+ responseTime + " ms");

            PackageResponse packageResponse = response.as(PackageResponse.class);
            PackageData packageData =  packageResponse.getData();
            for(PackageFaq faq : packageData.getFaq())
            {
                packageDataHashMap.put(faq.getId(), packageData);
            }
        }
        performAssertion(sheet,packageDataHashMap);

    }

    private void performAssertion(XSSFSheet sheet, Map<String, PackageData> packageDataMap) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            String expectedFaqId = ExcelUtils.getCellValue(sheet, i, 0);
            String expectedTitle = ExcelUtils.getCellValue(sheet, i, 1);
            String expectedDesc = ExcelUtils.getCellValue(sheet, i, 2);

            PackageData packageData = packageDataMap.get(expectedFaqId);
            if (packageData != null) {
                boolean faqFound = false;
                for (PackageFaq faq : packageData.getFaq()) {
                    if (faq.getId().equals(expectedFaqId)) {
                        faqFound = true;
                        try {
                            Assert.assertEquals(faq.getTitle(), expectedTitle, "Title mismatch for FAQ ID " + expectedFaqId);
                            Assert.assertEquals(faq.getDescr(), expectedDesc, "Description mismatch for FAQ ID " + expectedFaqId);
                        } catch (AssertionError e) {
                            System.err.println("Mismatch at row " + i + ": " + e.getMessage());
                        }
                        break;
                    }
                }
                if (!faqFound) {
                    System.err.println("FAQ with ID " + expectedFaqId + " not found in API response for package.");
                }
            } else {
                System.err.println("Package data for FAQ ID " + expectedFaqId + " not found.");
            }
        }
    }



}

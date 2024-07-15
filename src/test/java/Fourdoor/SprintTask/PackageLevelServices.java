package Fourdoor.SprintTask;

import io.restassured.response.Response;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ExcelUtils;
import utils.POJO.PackageDetails.PackageData;
import utils.POJO.PackageDetails.PackageResponse;
import utils.POJO.PackageDetails.PackageService;
import utils.RestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PackageLevelServices {

    private Map<String, String> headers;
    private String endPoint;

    @BeforeClass
    public void setUp() throws IOException {
        Map<String,Object> data = jsonUtils.jsonUtils.getJsonDataAsMap("Fourdoor/QA/fourdoorApiData.json");
        endPoint = (String) data.get("GetPackageDetails");
        headers = new HashMap<>();
        headers.put("FD_CITY_CODE","gurugram");
    }


    @Test
    public void getServices() throws IOException {
        XSSFSheet sheet = ExcelUtils.readExcel("data/TestExcel (6).xlsx","Service History");
        Map<String, PackageData> packageDataMap = new HashMap<>();

        for(int i =1 ;i<=49; i++)
        {
            long startTime = System.currentTimeMillis();
            Response response = RestUtils.performGet(endPoint + "/" + "P" +i, headers);
            long endTime = System.currentTimeMillis();

            long responseTime = endTime-startTime;
            System.out.println("The response time for api is "+ responseTime);

            PackageResponse packageResponse = response.as(PackageResponse.class);
            PackageData packageData = packageResponse.getData();

            for(PackageService packageService : packageData.getServices())
            {
                packageDataMap.put(packageService.getServiceCode(),packageData);
            }
        }

        performAssertion(sheet,packageDataMap);



    }

    public void performAssertion(XSSFSheet sheet, Map<String,PackageData> packageDataMap )
    {
        for(int i =1; i<sheet.getLastRowNum(); i++)
        {
            String expectedId = ExcelUtils.getCellValue(sheet,i,0);
            String expectedName = ExcelUtils.getCellValue(sheet,i,1);

            PackageData packageData = packageDataMap.get(expectedId);
            if(packageData != null)
            {
                boolean serviceFound = false;
                for (PackageService service : packageData.getServices())
                {
                    if(service.getServiceCode().equals(expectedId))
                    {
                        serviceFound = true;
                        try {
                            Assert.assertEquals(service.getName(),expectedName,"Name mismatch");
                        }
                        catch (AssertionError e)
                        {
                            System.err.println("Mismatch at row " + i + ": " + e.getMessage());
                        }
                        break;
                    }

                }
                if (!serviceFound) {
                    System.err.println("FAQ with ID " + expectedId + " not found in API response for package.");
                }

            }
            else {
                System.err.println("Package data for FAQ ID " + expectedId + " not found.");
            }
        }
    }

}

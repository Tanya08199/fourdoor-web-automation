package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fourdoorhomepage extends BaseTest{

    @Test

    public void matchExcelData() throws InterruptedException, IOException {
        List<Map<String, Object>> urlData = validatingdataurl();

        Fourdoorexcel fe = new Fourdoorexcel();
        List<Map<String, Object>> excelData = fe.fetchExcelData("Sheet1");

        List<Map<String, String>> mismatchedNames = compareLists(urlData, excelData);

        if (mismatchedNames.isEmpty()) {
            System.out.println("Data Validated Successfully;");
        } else {
            System.out.println("Names where values don't match:");
            for (Map<String, String> name : mismatchedNames) {
                for (Map.Entry<String, String> entry : name.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
            }
        }

        System.exit(1);
    }

    public List<Map<String, String>> compareLists(List<Map<String, Object>> list1, List<Map<String, Object>> list2) {
        List<Map<String, String>> mismatchedData = new ArrayList<>();

        // Match keys first
        List<String> keysList1 = new ArrayList<>();
        List<String> keysList2 = new ArrayList<>();

        for (Map<String, Object> map : list1) {
            for (String key : map.keySet()) {
                if (!keysList1.contains(key)) {
                    keysList1.add(key);
                }
            }
        }

        for (Map<String, Object> map : list2) {
            for (String key : map.keySet()) {
                if (!keysList2.contains(key)) {
                    keysList2.add(key);
                }
            }
        }

        // Compare keys in both lists
        for (String key : keysList1) {
            if (!keysList2.contains(key)) {
                Map<String, String> keyData = new HashMap<>();
                keyData.put("KeyMismatch", key + " not found in List2");
                mismatchedData.add(keyData);
            }
        }

        for (String key : keysList2) {
            if (!keysList1.contains(key)) {
                Map<String, String> keyData = new HashMap<>();
                keyData.put("KeyMismatch", key + " not found in List1");
                mismatchedData.add(keyData);
            }
        }

        // Compare values for matching keys
        for (Map<String, Object> map1 : list1) {
            for (Map<String, Object> map2 : list2) {
                if (map1.get("name").equals(map2.get("name"))) {
                    for (String key : keysList1) {
                        Object value1 = map1.getOrDefault(key, "Key Not Found");
                        Object value2 = map2.getOrDefault(key, "Key Not Found");

                        if (!value1.equals(value2)) {
                            Map<String, String> valueMismatch = new HashMap<>();
                            valueMismatch.put(key, value1 + " (List1) | " + value2 + " (List2)");
                            mismatchedData.add(valueMismatch);
                        }
                    }
                }
            }
        }

        return mismatchedData;
    }

    public List<Map<String, Object>> validatingdataurl() throws InterruptedException, IOException {
        driver.manage().window().maximize();
        driver.get("https://fourdoor-web-stage.fourdoor.dev/");
        Thread.sleep(5000);

        String xpath = "//div[contains(@class,'max-w-screen-xl')]//div[contains(@class,'lg:shadow-landingCard')]/a";
        List<WebElement> anchorTags = driver.findElements(By.xpath(xpath));
        int size_anchor = anchorTags.size();

        List<Map<String, Object>> excelDataList = new ArrayList<>();

        for(int i=1;i<=size_anchor;i++) {
            Thread.sleep(4000);
            Map<String, Object> rowData = new HashMap<>(); // Create new rowData map for each iteration
            driver.findElement(By.xpath("//div[contains(@class,'max-w-screen-xl')]//div[contains(@class,'lg:shadow-landingCard')]/a["+i+"]")).click();
            Thread.sleep(4000);
            List<WebElement> h1Tags = driver.findElements(By.tagName("h1"));
            for (WebElement h1 : h1Tags) {
                rowData.put("h1",h1.getText());
            }

            WebElement name = driver.findElement(By.xpath("//div[contains(@class,'border-primaryBlue')]//div[contains(@class,'flex gap-2')]"));
            String h3Tags = name.getText();
            rowData.put("name",h3Tags);

            WebElement metaDescription = driver.findElement(By.xpath("//meta[@name='description']"));
            String content = metaDescription.getAttribute("content");
            rowData.put("meta_description",content);

            String title = driver.getTitle();
            rowData.put("title",title);

            h1Tags.clear();
            metaDescription = null;
            content = null;
            title = null;
            h3Tags = null;
            System.out.println(rowData);
            excelDataList.add(rowData);

            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[contains(@class,\"mr-10\")]")).click();
        }

        tearDown();
        return excelDataList;
    }

//@Test
//public  void validatingdataexcelpage2() throws InterruptedException{
//    driver.manage().window().maximize();
//    driver.get("https://fourdoor-web-stage.fourdoor.dev/");
//    Thread.sleep(8000);
//    driver.findElement(By.xpath("//div[contains(@class,\"max-w-screen-xl\")]//div[contains(@class,\"lg:shadow-landingCard\")]/a[2]")).click();
//    Thread.sleep(1000);
//    List<WebElement> h1Tags = driver.findElements(By.tagName("h1"));
//    for (WebElement h1 : h1Tags) {
//        System.out.println(h1.getText());
//    }
//    WebElement metaDescription = driver.findElement(By.xpath("//meta[@name='description']"));
//    String content = metaDescription.getAttribute("content");
//    System.out.println("Meta Description: " + content);
//    String title = driver.getTitle();
//    System.out.println("Page Title: " + title);
//
//}

}


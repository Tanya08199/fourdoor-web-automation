//package FourdoorSelenium;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//public class CanonicalTagsTestCase extends BaseTest{
//
//    @DataProvider(name = "urls")
//    public Object[][] createUrl()
//    {
//        String url = "stage";
//        return new Object[][]{
//
//                {"https://fourdoor-web-stage.fourdoor.dev/"},
//<<<<<<< HEAD
//                {"https://fourdoor-web-qa.fourdoor.dev/gurugram/car-ac-service/regular-ac-service?source="},
//                {"https://fourdoor-web-qa.fourdoor.dev/gurugram/car-service-and-maintenance"},
//                {"https://fourdoor-web-qa.fourdoor.dev/gurugram/general-car-inspection"},
//                {"https://fourdoor-web-qa.fourdoor.dev/gurugram/bodyshop-dent-and-paint"},
//                {"https://fourdoor-web-qa.fourdoor.dev/gurugram/steering-and-suspension"},
//                {"https://fourdoor-web-qa.fourdoor.dev/gurugram/engine-and-brakes"},
//                {"https://fourdoor-web-qa.fourdoor.dev/gurugram/car-service-and-maintenance/essential-package?source=Home%20page"},
//                {"https://fourdoor-web-qa.fourdoor.dev/gurugram/car-service-and-maintenance/essential-package?source="},
//                {"https://fourdoor-web-qa.fourdoor.dev/help-and-faq?source=Home%20page"}
//=======
//                {"https://fourdoor-web-stage.fourdoor.dev/gurugram/car-ac-service/regular-ac-service?source="},
//                {"https://fourdoor-web-stage.fourdoor.dev/gurugram/car-service-and-maintenance"},
//                {"https://fourdoor-web-stage.fourdoor.dev/gurugram/general-car-inspection"},
//                {"https://fourdoor-web-stage.fourdoor.dev/gurugram/bodyshop-dent-and-paint"},
//                {"https://fourdoor-web-stage.fourdoor.dev/gurugram/steering-and-suspension"},
//                {"https://fourdoor-web-stage.fourdoor.dev/gurugram/engine-and-brakes"},
//                {"https://fourdoor-web-stage.fourdoor.dev/gurugram/car-service-and-maintenance/essential-package?source=Home%20page"},
//                {"https://fourdoor-web-stage.fourdoor.dev/gurugram/car-service-and-maintenance/essential-package?source="},
//                {"https://fourdoor-web-stage.fourdoor.dev/cart?orderId=SRSB3R&source=Listing%20page"},
//                {"https://fourdoor-web-stage.fourdoor.dev/gurugram/bodyshop-dent-and-paint/front-bumper-paint?source="},
//                {"https://fourdoor-web-stage.fourdoor.dev/checkout/SRSB3R"},
//                {"https://fourdoor-web-stage.fourdoor.dev/gurugram/car-ac-service/radiator-flush-and-cleaning?source=Home%20page"}
//>>>>>>> anshuman-super-api
//        };
//    }
//
//    @Test(dataProvider = "urls")
//    public void testCanonicalTag(String url)
//    {
//        driver.get(url);
//
//        String pageSource = driver.getPageSource();
//        Document doc = Jsoup.parse(pageSource);
//
//        Element canonicalTag = doc.selectFirst("link[rel=canonical]");
//
//        Assert.assertNotNull(canonicalTag, "Tag is missing");
//
//        String canonicalUrl = canonicalTag.attr("href");
//        System.out.println("Canonical URL: " + canonicalUrl);
//        Assert.assertFalse(canonicalUrl.isEmpty(), "Canonical URL is empty!");
//
//
//
//    }
//}

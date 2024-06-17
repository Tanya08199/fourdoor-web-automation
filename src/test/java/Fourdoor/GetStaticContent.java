package Fourdoor;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.POJO.StaticContent.*;
import utils.RestUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static utils.ImageValidation.*;

public class GetStaticContent {


    private String endPoint;

    @BeforeClass
    public void setUp() throws IOException {
        Map<String,Object> data = jsonUtils.jsonUtils.getJsonDataAsMap("Fourdoor/QA/fourdoorApiData.json");
        endPoint = (String) data.get("StaticContent");
    }

    @Test
    public void getStaticContentDetails() throws IOException {
        long startTime = System.currentTimeMillis();
        Response response = RestUtils.performGet(endPoint,new HashMap<>());
        long endTime = System.currentTimeMillis();
        long responseTime = endTime-startTime;

        System.out.println("The response time for getStaticContent is " + responseTime + " ms");
        GetStaticContentResponse getStaticContentResponse = response.as(GetStaticContentResponse.class);

        StaticContentData staticContentData = getStaticContentResponse.getData();

        for (Banners banners : staticContentData.getBanners())
        {
            for (Msite msite : banners.getMsite())
            {
                String path = msite.getPath();
                if(isValidUrl(path))
                {
                    System.out.println("The image url is valid "+ path);
                    if(isImageExists(path))
                    {
                        System.out.println("The image exists at the url "+path);
                        openInBrowser(path);
                    }
                    else {
                        System.err.println("The image does not exist at the URL: " + path);
                    }

                }

                else {
                    System.err.println("The image URL is not valid: " + path);
                }

            }

            for (Desktop desktop : banners.getDesktop())
            {
                String path = desktop.getPath();
                if(isValidUrl(path))
                {
                    System.out.println("The image url is valid "+ path);
                    if(isImageExists(path))
                    {
                        System.out.println("The image exists at the url "+path);
                        openInBrowser(path);
                    }
                    else {
                        System.err.println("The image does not exist at the URL: " + path);
                    }

                }

                else {
                    System.err.println("The image URL is not valid: " + path);
                }
            }
        }

        for (FourdoorGurantee fourdoorGurantee : staticContentData.getFourDoorGuarantee())
        {
            Icon icon = fourdoorGurantee.getIcon();
            String path = icon.getPath();
            if(isValidUrl(path))
            {
                System.out.println("The image url is valid "+ path);
                if(isImageExists(path))
                {
                    System.out.println("The image exists at the url "+path);
                    openInBrowser(path);
                }
                else {
                    System.err.println("The image does not exist at the URL: " + path);
                }

            }

            else {
                System.err.println("The image URL is not valid: " + path);
            }
        }

        for (PriceComparison priceComparison : staticContentData.getPriceComparison())
        {
            Icon icon = priceComparison.getIcon();
            String path = icon.getPath();
            if(isValidUrl(path))
            {
                System.out.println("The image url is valid "+ path);
                if(isImageExists(path))
                {
                    System.out.println("The image exists at the url "+path);
                    openInBrowser(path);
                }
                else {
                    System.err.println("The image does not exist at the URL: " + path);
                }

            }

            else {
                System.err.println("The image URL is not valid: " + path);
            }
        }

        for (Testimonial testimonial : staticContentData.getTestimonials())
        {
            Icon icon = testimonial.getIcon();
            String path = icon.getPath();
            if(isValidUrl(path))
            {
                System.out.println("The image url is valid "+ path);
                if(isImageExists(path))
                {
                    System.out.println("The image exists at the url "+path);
                    openInBrowser(path);
                }
                else {
                    System.err.println("The image does not exist at the URL: " + path);
                }

            }

            else {
                System.err.println("The image URL is not valid: " + path);
            }
        }

    }


}

package utils;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

public class ImageValidation {

    public static boolean isValidUrl(String urlPath)
    {
        try {
            URL url = new URL(urlPath);
            url.toURI();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean isImageExists(String urlPath) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlPath).openConnection();
        connection.setRequestMethod("HEAD");
        int responseCode = connection.getResponseCode();
        return (responseCode == HttpURLConnection.HTTP_OK);

    }

    public static void openInBrowser(String urlPath)
    {
        if(Desktop.isDesktopSupported())
        {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(urlPath));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Not supported browser");
        }
    }
}

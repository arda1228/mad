package com.example.authapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils { // needed to establish and manage communication with Expedia's API
    public static String makeHTTPRequest(URL url) throws IOException {
        // initiating connection to given url
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // new input stream object instantiated
        InputStream inputStream = connection.getInputStream();

        try {
            // reads input stream from connection
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput)
                return scanner.next();
            else return null;
        }
        finally {
            connection.disconnect();
            // disconnects once finished
        }
    }

}

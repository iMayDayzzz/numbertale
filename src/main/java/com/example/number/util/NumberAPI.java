package com.example.number.util;


import com.example.number.util.exception.WrongConnectionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NumberAPI {

    public static final String URL_NUMBER_API = "http://numbersapi.com/";

    public static String fetchNumber(int number) throws IOException {
        URL url = new URL(URL_NUMBER_API + number);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "text/plain");

        if (con.getResponseCode() != 200) throw new WrongConnectionException("Connection gone wrong");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (Exception ex) {
            return ex.getMessage();
        } finally {
           con.disconnect();
        }
    }

}

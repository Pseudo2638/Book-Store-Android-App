package com.example.hp.bookshop;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 7/29/2017.
 */

public final class QueryUtils {

    QueryUtils()
    {

    }

    public static List<Books> fetchFeatures(String requestUrl)
    {
        URL url = createUrl(requestUrl);
        String jsonResponce = null;
        try
        {
            jsonResponce=makeHttpRequest(url);

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Books> books = extractFeaturesFromJson(jsonResponce);
        return books;
    }

    private static URL createUrl(String stringUrl)
    {

        URL url= null;
        try {
            url= new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException
    {
        String jsonResponce = null;
        if (url==null){
            return jsonResponce;
        }
        HttpURLConnection httpURLConnection= null;
        InputStream inputStream = null;
        try
        {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode()==200) {

                inputStream = httpURLConnection.getInputStream();
                jsonResponce = readFromStream(inputStream);
            }
            else {

            }

        }
        catch (IOException e) {
           e.printStackTrace();
        }
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
            return jsonResponce;
        }


    private static String readFromStream(InputStream inputStream)throws IOException
    {

        StringBuilder output = new StringBuilder();
        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null)
            {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Books> extractFeaturesFromJson(String jsonResponce)
    {
        if (TextUtils.isEmpty(jsonResponce))
        {
            return null;
        }
        List<Books> bookses = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(jsonResponce);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i =0;i<jsonArray.length();i++)
            {
                JSONObject current = jsonArray.getJSONObject(i);
                JSONObject volumeInfo = current.getJSONObject("volumeInfo");
                String book_id = volumeInfo.getString("title");
                String book_authors=volumeInfo.getString("publisher");
                String book_rating = volumeInfo.getString("maturityRating");
                Double page_count = volumeInfo.getDouble("pageCount");
                Books books = new Books(book_id,book_authors,book_rating,page_count);
                bookses.add(books);

            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return bookses;
    }

}

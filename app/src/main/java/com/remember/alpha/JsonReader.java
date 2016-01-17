package com.remember.alpha;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by cnnr2 on 2016-01-15.
 */
public class JsonReader {
    public  String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
    public String keyedJson1;
    public String keyedJson2;
    public String getJsonData( final String key, final String url) {




        try {
            JSONObject issueObj = new JSONObject( readUrl(url) );

            keyedJson1 =  issueObj.getString("address");
            JSONObject issueObj2 = new JSONObject(keyedJson1);
            keyedJson2 = issueObj2.getString(key);


        } catch (Exception e) {
            e.printStackTrace();
        }




        return keyedJson2;

    }
}

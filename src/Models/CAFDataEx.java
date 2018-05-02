//
//  CAFDataEx.java
//
//  Created by Cesar Franco on 10/07/16.
//  Copyright © 2015 Cesar Franco. All rights reserved.
//
package models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class CAFDataEx {

    private static final int bufferSize = 100 * 1024; // ~130K.
    private ByteBuffer byteData = null;

    //Constructors
    public CAFDataEx(byte[] bytes, int length) {

        byteData = ByteBuffer.wrap(bytes, 0, length);
    }

    private CAFDataEx(String path) {
        File inFile = new File(path);
        InputStream inStream = null;
        //byteData = null;
        try {
            inStream = new FileInputStream(inFile);
            byteData = getBytesFromInputStream(inStream);
            inStream.close();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        catch (IOException e) {

        }

    }

    private CAFDataEx(URL url) {
        InputStream inStream = null;
        int response = -1;
        URLConnection conn = null;

        try {
            conn = url.openConnection();
            if (conn instanceof HttpURLConnection) {
                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setReadTimeout(10000);
                httpConn.setConnectTimeout(15000);
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.setDoInput(true);
                httpConn.connect();
                response = httpConn.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {
                    //try {
                    inStream = httpConn.getInputStream();
                    byteData = getBytesFromInputStream(inStream);
                    inStream.close();
                    //} catch (){

                    //}
                }
                httpConn.disconnect();
            }

        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Private methods
    private static ByteBuffer getBytesFromInputStream(InputStream inStream) {
        byte[] buffer = new byte[bufferSize];
        ByteArrayOutputStream outStream = null;
        ByteBuffer byteData = null;

        try {
            outStream = new ByteArrayOutputStream(buffer.length);
            int bytesRead = 0;
            while (bytesRead != -1) {
                bytesRead = inStream.read(buffer);
                if (bytesRead > 0) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
            byteData = ByteBuffer.wrap(outStream.toByteArray());
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            byteData = null;
        }

        return byteData;
    }

    // Public methods
    public static CAFDataEx dataWithContentsOfURL(URL url) {

        CAFDataEx data = new CAFDataEx(url);
        if (data != null && data.length() == 0) {
            data = null;
        }

        return data;
    }

    public static CAFDataEx dataWithContentsOfFile(String fullFilename) {

        CAFDataEx data = new CAFDataEx(fullFilename);
        if (data != null && data.length() == 0) {
            data = null;
        }

        return data;
    }

    public String toText() {
        return new String(byteData.array(), Charset.forName("UTF-8"));
    }

    public JSONObject toJSONObject() {
        JSONObject obj = null;
        try {
            obj = new JSONObject(this.toText());
        }
        catch (JSONException e) {
            obj = null;
        }

        return obj;
    }

    public JSONArray toJSONArray() {
        JSONArray array = null;
        try {
            array = new JSONArray(this.toText());
        }
        catch (JSONException e) {
            array = null;
        }

        return array;
    }

    public int length() {
        int length = 0;

        if (byteData != null) {
            length = byteData.array().length;
        }

        return length;
    }

    public boolean isEqualToData(CAFDataEx other) {
        return byteData.equals(other.byteData);
    }
}

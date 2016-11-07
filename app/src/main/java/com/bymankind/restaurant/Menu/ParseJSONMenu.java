package com.bymankind.restaurant.Menu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import com.android.volley.toolbox.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.R.attr.bitmap;

/**
 * Created by Server-Panduit on 7/27/2016.
 */
public class ParseJSONMenu {
    public static String[] id_menu;
    public static String[] name;
    public static String[] price;
    public static String[] picture;
    public static String[] description;
    public static Bitmap[] bitmaps;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID = "id_menu";
    public static final String KEY_NAME = "name";
    public static final String KEY_PRICE = "price";
    public static final String KEY_PICTURE = "picture";
    public static final String KEY_DESCRIPTION = "description";

    private JSONArray users = null;

    private String json;

    public ParseJSONMenu(String json){
        this.json = json;
    }


    public void parseJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id_menu = new String[users.length()];
            name = new String[users.length()];
            price = new String[users.length()];
            picture = new String[users.length()];
            description = new String[users.length()];
            bitmaps = new Bitmap[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);

                id_menu[i] = jo.getString(KEY_ID);
                name[i] = jo.getString(KEY_NAME);
                price[i] = jo.getString(KEY_PRICE);
                picture[i] = jo.getString(KEY_PICTURE);
                description[i] = jo.getString(KEY_DESCRIPTION);

                bitmaps[i] = getImage(KEY_PICTURE);

            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private Bitmap getImage(String jo) {

        Bitmap bitmap = null;
        InputStream stream = null;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;
        try {
            stream = getHttpConnection(jo);
            bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
            stream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private InputStream getHttpConnection(String urlString) throws IOException {
        InputStream stream = null;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        try {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return stream;
    }


}

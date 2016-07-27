package com.bymankind.restaurant.posisi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 7/27/2016.
 */
public class ParseJSONReadPosisi {
    public static String[] id;
    public static String[] posisi;
    public static String[] password;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID = "id_posisi";
    public static final String KEY_POSISI = "posisi";
    public static final String KEY_PASSWORD = "password";

    private JSONArray users = null;

    private String json;

    public ParseJSONReadPosisi(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);


            id = new String[users.length()];
            posisi = new String[users.length()];
            password = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id[i] = jo.getString(KEY_ID);
                posisi[i] = jo.getString(KEY_POSISI);
                password[i] = jo.getString(KEY_PASSWORD);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

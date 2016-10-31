package com.bymankind.restaurant.Menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 7/27/2016.
 */
public class ParseJSONMenu {
    public static String[] id_menu;
    public static String[] name;
    public static String[] price;
    public static String[] picture;
    public static String[] description;

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

    protected void parseJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id_menu = new String[users.length()];
            name = new String[users.length()];
            price = new String[users.length()];
            picture = new String[users.length()];
            description = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id_menu[i] = jo.getString(KEY_ID);
                name[i] = jo.getString(KEY_NAME);
                price[i] = jo.getString(KEY_PRICE);
                picture[i] = jo.getString(KEY_PICTURE);
                description[i] = jo.getString(KEY_DESCRIPTION);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

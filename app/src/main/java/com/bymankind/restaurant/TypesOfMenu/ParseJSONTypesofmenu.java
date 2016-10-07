package com.bymankind.restaurant.TypesOfMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 10/7/2016.
 */

public class ParseJSONTypesofmenu {
    public static String[] id_types_of_menu;
    public static String[] name;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID = "id_types_of_menu";
    public static final String KEY_NAME = "name";

    private JSONArray users = null;

    private String json;

    public ParseJSONTypesofmenu(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id_types_of_menu = new String[users.length()];
            name = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id_types_of_menu[i] = jo.getString(KEY_ID);
                name[i] = jo.getString(KEY_NAME);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

package com.bymankind.restaurant.Chef;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 11/14/2016.
 */

public class ParseJSONStatusCooked {
    public static String[] id_transaction;
    public static String[] name;
    public static String[] id_table;
    public static String[] menu;
    public static String[] quantity;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID_TRANSACTION = "id_transaction";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID_TABLE = "id_table";
    public static final String KEY_MENU = "menu";
    public static final String KEY_QUANTITY = "quantity";

    private JSONArray users = null;

    private String json;

    public ParseJSONStatusCooked(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id_transaction = new String[users.length()];
            name = new String[users.length()];
            id_table = new String[users.length()];
            menu = new String[users.length()];
            quantity = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id_transaction[i] = jo.getString(KEY_ID_TRANSACTION);
                name[i] = jo.getString(KEY_NAME);
                id_table[i] = jo.getString(KEY_ID_TABLE);
                menu[i] = jo.getString(KEY_MENU);
                quantity[i] = jo.getString(KEY_QUANTITY);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

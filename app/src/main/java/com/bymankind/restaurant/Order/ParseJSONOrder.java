package com.bymankind.restaurant.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 10/10/2016.
 */

public class ParseJSONOrder {
    public static String[] id_transaction;
    public static String[] name;
    public static String[] id_table;
    public static String[] description;
    public static String[] menu;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID = "id_transaction";
    public static final String KEY_NAME = "name";
    public static final String KEY_TABLE = "id_table";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_MENU = "menu";

    private JSONArray users = null;

    private String json;

    public ParseJSONOrder(String json){
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
            description = new String[users.length()];
            menu = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id_transaction[i] = jo.getString(KEY_ID);
                name[i] = jo.getString(KEY_NAME);
                id_table[i] = jo.getString(KEY_TABLE);
                description[i] = jo.getString(KEY_DESCRIPTION);
                menu[i] = jo.getString(KEY_MENU);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

package com.bymankind.restaurant.Chef;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 11/14/2016.
 */

public class ParseJSONStatusCooked {
    public static String[] id_order;
    public static String[] name;
    public static String[] id_table;
    public static String[] id_menu;
    public static String[] id_customer;
    public static String[] menu;
    public static String[] quantity;
    public static String[] timeOrderCooked;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_id_order = "id_order";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID_TABLE = "id_table";
    public static final String KEY_MENU = "menu";
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_ID_MENU = "id_menu";
    public static final String KEY_ID_CUSTOMER = "id_customer";
    public static final String KEY_TIME_ORDER_COOKED = "timeOrderCooked";


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

            id_order = new String[users.length()];
            name = new String[users.length()];
            id_table = new String[users.length()];
            menu = new String[users.length()];
            quantity = new String[users.length()];
            id_menu = new String[users.length()];
            id_customer = new String[users.length()];
            timeOrderCooked = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id_order[i] = jo.getString(KEY_id_order);
                name[i] = jo.getString(KEY_NAME);
                id_table[i] = jo.getString(KEY_ID_TABLE);
                menu[i] = jo.getString(KEY_MENU);
                quantity[i] = jo.getString(KEY_QUANTITY);
                id_menu[i] = jo.getString(KEY_ID_MENU);
                id_customer[i] = jo.getString(KEY_ID_CUSTOMER);
                timeOrderCooked[i] = jo.getString(KEY_TIME_ORDER_COOKED);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

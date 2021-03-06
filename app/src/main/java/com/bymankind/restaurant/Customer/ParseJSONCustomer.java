package com.bymankind.restaurant.Customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 10/10/2016.
 */

public class ParseJSONCustomer {
    public static String[] id_customer;
    public static String[] name;
    public static String[] description;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID = "id_customer";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";

    private JSONArray users = null;

    private String json;

    public ParseJSONCustomer(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id_customer = new String[users.length()];
            name = new String[users.length()];
            description = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id_customer[i] = jo.getString(KEY_ID);
                name[i] = jo.getString(KEY_NAME);
                description[i] = jo.getString(KEY_DESCRIPTION);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

package com.bymankind.restaurant.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 10/5/2016.
 */

public class ParseJSONEmployee {
    public static String[] id_employee;
    public static String[] name;
    public static String[] position;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID = "id_employee";
    public static final String KEY_NAME = "name";
    public static final String KEY_POSITION = "position";

    private JSONArray users = null;

    private String json;

    public ParseJSONEmployee(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id_employee = new String[users.length()];
            name = new String[users.length()];
            position = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id_employee[i] = jo.getString(KEY_ID);
                name[i] = jo.getString(KEY_NAME);
                position[i] = jo.getString(KEY_POSITION);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

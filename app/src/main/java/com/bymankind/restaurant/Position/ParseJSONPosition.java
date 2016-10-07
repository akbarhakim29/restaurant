package com.bymankind.restaurant.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 7/27/2016.
 */
public class ParseJSONPosition {
    public static String[] id_position;
    public static String[] name;
    public static String[] salary;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID = "id_position";
    public static final String KEY_NAME = "name";
    public static final String KEY_SALARY = "salary";

    private JSONArray users = null;

    private String json;

    public ParseJSONPosition(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id_position = new String[users.length()];
            name = new String[users.length()];
            salary = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id_position[i] = jo.getString(KEY_ID);
                name[i] = jo.getString(KEY_NAME);
                salary[i] = jo.getString(KEY_SALARY);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

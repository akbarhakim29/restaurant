package com.bymankind.customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 8/4/2016.
 */
public class CheckMejaParseJSON {
    public static String[] id_table;
    public static String[] description;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID = "id_table";
    public static final String KEY_DESCRIPTION = "description";

    private JSONArray users = null;

    private String json;

    public CheckMejaParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);


            id_table = new String[users.length()];
            description = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id_table[i] = jo.getString(KEY_ID);
                description[i] = jo.getString(KEY_DESCRIPTION);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

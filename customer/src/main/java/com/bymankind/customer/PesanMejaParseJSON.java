package com.bymankind.customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Server-Panduit on 8/8/2016.
 */
public class PesanMejaParseJSON {
    public static String[] id_makanan;
    public static String[] nama;
    public static String[] harga;
    public static String[] deskripsi;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID = "id_makanan";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_HARGA = "harga";
    public static final String KEY_DESKRIPSI = "deskripsi";

    private JSONArray users = null;

    private String json;

    public PesanMejaParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);


            id_makanan = new String[users.length()];
            nama = new String[users.length()];
            harga = new String[users.length()];
            deskripsi = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                id_makanan[i] = jo.getString(KEY_ID);
                nama[i] = jo.getString(KEY_NAMA);
                harga[i] = jo.getString(KEY_HARGA);
                deskripsi[i] = jo.getString(KEY_DESKRIPSI);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}

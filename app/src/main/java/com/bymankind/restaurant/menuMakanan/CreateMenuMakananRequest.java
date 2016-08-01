package com.bymankind.restaurant.menuMakanan;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/27/2016.
 */
public class CreateMenuMakananRequest extends StringRequest{
    private final static String CREATE_REQUEST_URL = "http://192.168.100.4/login/sql_restaurant.php?operasi=create_menu_makanan";
    private Map<String, String> params;

    public CreateMenuMakananRequest(String nama, String harga, String deskripsi, Response.Listener<String> listener){
        super(Method.POST , CREATE_REQUEST_URL, listener , null);
        params = new HashMap<>();

        params.put("nama",nama);
        params.put("harga",harga);
        params.put("deskripsi",deskripsi);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

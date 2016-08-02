package com.bymankind.restaurant.menuMakanan;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/27/2016.
 */
public class CreateMenuMakananRequest extends StringRequest{
    private final static String CREATE_REQUEST_URL = "http://188.166.176.9/API/public/menu/submit";
    private Map<String, String> params;

    public CreateMenuMakananRequest(String token,int id_makanan,String nama, String harga, String deskripsi, Response.Listener<String> listener){
        super(Method.POST , CREATE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("token",token);
        params.put("id_makanan",id_makanan+"");
        params.put("nama",nama);
        params.put("harga",harga);
        params.put("deskripsi",deskripsi);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

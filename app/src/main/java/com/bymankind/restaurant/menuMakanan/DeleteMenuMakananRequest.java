package com.bymankind.restaurant.menuMakanan;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 8/3/2016.
 */
public class DeleteMenuMakananRequest extends StringRequest{
    private final static String DELETE_REQUEST_URL = "http://192.168.100.16/restoserver/api/deleteMenu";
    private Map<String, String> params;

    public DeleteMenuMakananRequest(int id_menu, Response.Listener<String> listener){
        super(Request.Method.POST , DELETE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_menu",id_menu+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

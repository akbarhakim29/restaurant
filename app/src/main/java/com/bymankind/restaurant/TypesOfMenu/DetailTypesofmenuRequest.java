package com.bymankind.restaurant.TypesOfMenu;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/7/2016.
 */

public class DetailTypesofmenuRequest extends StringRequest{
    private final static String DETAIL_TOM_REQUEST_URL = "http://192.168.100.37/restoserver/api/getToM";
    private Map<String, String> params;

    public DetailTypesofmenuRequest(String id_types_of_menu, Response.Listener<String> listener){
        super(Request.Method.POST , DETAIL_TOM_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_types_of_menu",id_types_of_menu);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

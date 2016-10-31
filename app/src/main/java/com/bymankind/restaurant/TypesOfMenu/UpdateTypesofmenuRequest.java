package com.bymankind.restaurant.TypesOfMenu;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/7/2016.
 */

public class UpdateTypesofmenuRequest extends StringRequest {
    private final static String UPDATE_REQUEST_URL = "http://192.168.100.30/restoserver/api/updateToM";
    private Map<String, String> params;

    public UpdateTypesofmenuRequest(int id_types_of_menu, String name, Response.Listener<String> listener){
        super(Request.Method.POST , UPDATE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_types_of_menu",id_types_of_menu+"");
        params.put("name",name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

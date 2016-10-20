package com.bymankind.restaurant.TypesOfMenu;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/13/2016.
 */

public class CreateTypesofmenuRequest extends StringRequest {
    private final static String CREATE_TOM_REQUEST_URL = "http://192.168.100.8/restoserver/api/insertToM";
    private Map<String, String> params;

    public CreateTypesofmenuRequest(String name, String description, Response.Listener<String> listener){
        super(Method.POST , CREATE_TOM_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("description",description);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

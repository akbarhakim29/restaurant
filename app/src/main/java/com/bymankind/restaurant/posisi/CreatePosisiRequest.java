package com.bymankind.restaurant.posisi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/21/2016.
 */
public class CreatePosisiRequest extends StringRequest{
    private final static String CREATE_REQUEST_URL = "http://192.168.100.4/login/sql_restaurant.php?operasi=create_posisi";
    private Map<String, String> params;

    public CreatePosisiRequest(String posisi, String password, Response.Listener<String> listener){
        super(Method.POST , CREATE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("posisi",posisi);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

package com.bymankind.restaurant;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/20/2016.
 */
public class LoginRequest extends StringRequest {
    private final static String LOGIN_REQUEST_URL = "http://192.168.100.14/restoserver/api/login";
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener){
        super(Method.POST , LOGIN_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

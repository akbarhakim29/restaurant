package com.bymankind.restaurant.posisi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/22/2016.
 */
public class UpdatePosisiRequest extends StringRequest {
    private final static String UPDATE_REQUEST_URL = "http://192.168.1.173/login/sql_restaurant.php?operasi=update_posisi";
    private Map<String, String> params;

    public UpdatePosisiRequest(String oldposisi, String newposisi, String newpassword, Response.Listener<String> listener){
        super(Method.POST , UPDATE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("oldposisi",oldposisi);
        params.put("newposisi",newposisi);
        params.put("newpassword",newpassword);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

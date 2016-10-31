package com.bymankind.restaurant.Table;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/13/2016.
 */

public class CreateTableRequest extends StringRequest {
    private final static String CREATE_TABLE_REQUEST_URL = "http://192.168.100.30/restoserver/api/insertTable";
    private Map<String, String> params;

    public CreateTableRequest(int id_table, int id_status, Response.Listener<String> listener){
        super(Method.POST , CREATE_TABLE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_table",id_table+"");
        params.put("id_status",id_status+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

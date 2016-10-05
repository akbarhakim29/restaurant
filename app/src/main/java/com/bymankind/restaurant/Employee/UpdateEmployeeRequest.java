package com.bymankind.restaurant.Employee;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/5/2016.
 */

public class UpdateEmployeeRequest extends StringRequest{
    private final static String UPDATE_REQUEST_URL = "http://192.168.100.14/restoserver/api/updateEmployee";
    private Map<String, String> params;

    public UpdateEmployeeRequest(int id_employee, String name,String birthPlace, Response.Listener<String> listener){
        super(Request.Method.POST , UPDATE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_employee",id_employee+"");
        params.put("name",name);
        params.put("birthPlace",birthPlace);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
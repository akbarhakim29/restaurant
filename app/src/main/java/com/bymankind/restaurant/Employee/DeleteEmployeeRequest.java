package com.bymankind.restaurant.Employee;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/5/2016.
 */

public class DeleteEmployeeRequest extends StringRequest{
    private final static String DELETE_EMPLOYEE_REQUEST_URL = "http://192.168.100.9/restoserver/api/deleteEmployee";
    private Map<String, String> params;

    public DeleteEmployeeRequest(int id_employee, Response.Listener<String> listener){
        super(Request.Method.POST , DELETE_EMPLOYEE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_employee",id_employee+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

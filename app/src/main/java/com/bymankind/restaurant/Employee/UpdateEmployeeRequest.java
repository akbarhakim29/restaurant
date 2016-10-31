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
    private final static String UPDATE_EMPLOYEE_REQUEST_URL = "http://192.168.100.30/restoserver/api/updateEmployee";
    private Map<String, String> params;

    public UpdateEmployeeRequest(int id_employee, String name,String birthPlace,String birthDay,int id_position,String contractStart,String contractEnd, Response.Listener<String> listener){
        super(Request.Method.POST , UPDATE_EMPLOYEE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_employee",id_employee+"");
        params.put("name",name);
        params.put("birthPlace",birthPlace);
        params.put("birthDay",birthDay);
        params.put("id_position",id_position+"");
        params.put("contractStart",contractStart);
        params.put("contractEnd",contractEnd);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

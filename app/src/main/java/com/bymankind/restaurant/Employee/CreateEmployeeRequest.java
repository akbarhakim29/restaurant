package com.bymankind.restaurant.Employee;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/12/2016.
 */

public class CreateEmployeeRequest extends StringRequest{
    private final static String CREATE_EMPLOYEE_REQUEST_URL = "http://192.168.100.8/restoserver/api/insertEmployee";
    private Map<String, String> params;

    public CreateEmployeeRequest(String name,String birthPlace,String birthDay,String lastEducation,int id_position,String username,String password,String contractStart,String contractEnd, Response.Listener<String> listener){
        super(Request.Method.POST , CREATE_EMPLOYEE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("birthPlace",birthPlace);
        params.put("birthDay",birthDay);
        params.put("lastEducation",lastEducation);
        params.put("id_position",id_position+"");
        params.put("username",username);
        params.put("password",password);
        params.put("contractStart",contractStart);
        params.put("contractEnd",contractEnd);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

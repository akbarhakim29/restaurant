package com.bymankind.restaurant.Customer;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/10/2016.
 */

public class DetailCustomerRequest extends StringRequest{
    private final static String DETAIL_CUSTOMER_REQUEST_URL = "http://192.168.100.8/restoserver/api/getCustomer";
    private Map<String, String> params;

    public DetailCustomerRequest(String id_customer, Response.Listener<String> listener){
        super(Request.Method.POST , DETAIL_CUSTOMER_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_customer",id_customer);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

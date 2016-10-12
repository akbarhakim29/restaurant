package com.bymankind.restaurant.Customer;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/10/2016.
 */

public class UpdateCustomerRequest extends StringRequest{
    private final static String UPDATE_CUSTOMER_REQUEST_URL = "http://192.168.100.8/restoserver/api/updateCustomer";
    private Map<String, String> params;

    public UpdateCustomerRequest(int id_customer, int id_customer_status,String name,String address,String telephone, Response.Listener<String> listener) {
        super(Request.Method.POST, UPDATE_CUSTOMER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_customer", id_customer + "");
        params.put("id_customer_status", id_customer_status + "");
        params.put("name", name);
        params.put("address", address);
        params.put("telephone", telephone + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

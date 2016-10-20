package com.bymankind.restaurant.Inventory;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/12/2016.
 */

public class CreateInventoryRequest extends StringRequest{
    private final static String CREATE_INVENTORY_REQUEST_URL = "http://192.168.100.8/restoserver/api/insertInventory";
    private Map<String, String> params;

    public CreateInventoryRequest(String name, int amount,String description, Response.Listener<String> listener){
        super(Request.Method.POST , CREATE_INVENTORY_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("amount",amount+"");
        params.put("description",description);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

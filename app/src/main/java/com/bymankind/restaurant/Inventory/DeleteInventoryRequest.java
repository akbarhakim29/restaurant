package com.bymankind.restaurant.Inventory;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/7/2016.
 */

public class DeleteInventoryRequest extends StringRequest{
    private final static String DELETE_INVENTORY_REQUEST_URL = "http://192.168.100.5/restoserver/api/deleteInventory";
    private Map<String, String> params;

    public DeleteInventoryRequest(int id_inventory, Response.Listener<String> listener){
        super(Request.Method.POST , DELETE_INVENTORY_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_inventory",id_inventory+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

package com.bymankind.restaurant.Inventory;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/7/2016.
 */

public class DetailInventoryRequest extends StringRequest{
    private final static String DETAIL_INVENTORY_REQUEST_URL = "http://192.168.100.8/restoserver/api/getInventory";
    private Map<String, String> params;

    public DetailInventoryRequest(String id_inventory, Response.Listener<String> listener){
        super(Request.Method.POST , DETAIL_INVENTORY_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_inventory",id_inventory);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

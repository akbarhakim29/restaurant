package com.bymankind.restaurant.Inventory;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 10/7/2016.
 */

public class UpdateInventoryRequest extends StringRequest{
    private final static String UPDATE_INVENTORY_REQUEST_URL = "http://192.168.100.4/restoserver/api/updateInventory";
    private Map<String, String> params;

    public UpdateInventoryRequest(int id_inventory, String name, int amount, String description, Response.Listener<String> listener){
        super(Request.Method.POST , UPDATE_INVENTORY_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_inventory",id_inventory+"");
        params.put("name",name);
        params.put("amount",amount+"");
        params.put("description",description);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

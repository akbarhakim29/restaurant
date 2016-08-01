package com.bymankind.restaurant.menuMakanan;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/28/2016.
 */
public class UpdateMenuMakananRequest extends StringRequest{
    private final static String UPDATE_REQUEST_URL = "http://192.168.100.4/login/sql_restaurant.php?operasi=update_menu_makanan";
    private Map<String, String> params;

    public UpdateMenuMakananRequest(String oldMenu, String newMenu, String newPrice, String newDescription, Response.Listener<String> listener){
        super(Request.Method.POST , UPDATE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("oldMenu",oldMenu);
        params.put("newMenu",newMenu);
        params.put("newPrice",newPrice);
        params.put("newDescription",newDescription);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

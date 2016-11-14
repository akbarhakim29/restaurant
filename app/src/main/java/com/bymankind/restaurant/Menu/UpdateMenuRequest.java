package com.bymankind.restaurant.Menu;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 8/3/2016.
 */
public class UpdateMenuRequest extends StringRequest{
    private final static String UPDATE_MENU_REQUEST_URL = "http://192.168.100.5/restoserver/api/updateMenu";
    private Map<String, String> params;

    public UpdateMenuRequest(int id_menu, String name, int price, String description, Response.Listener<String> listener){
        super(Request.Method.POST , UPDATE_MENU_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_menu",id_menu+"");
        params.put("name",name);
        params.put("price",price+"");
        params.put("description",description);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

package com.bymankind.restaurant.Menu;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 8/2/2016.
 */
public class DetailMenuRequest extends StringRequest{
    private final static String DETAIL_MENU_REQUEST_URL = "http://192.168.100.4/restoserver/api/getMenu";
    private Map<String, String> params;

    public DetailMenuRequest(String id_menu, Response.Listener<String> listener){
        super(Method.POST , DETAIL_MENU_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_menu",id_menu);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

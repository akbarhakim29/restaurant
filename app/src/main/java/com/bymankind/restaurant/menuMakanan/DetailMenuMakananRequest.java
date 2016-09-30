package com.bymankind.restaurant.menuMakanan;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 8/2/2016.
 */
public class DetailMenuMakananRequest extends StringRequest{
    private final static String DETAIL_MENU_REQUEST_URL = "http://192.168.100.9/restoserver/api/getMenu";
    private Map<String, String> params;

    public DetailMenuMakananRequest(String id_makanan, Response.Listener<String> listener){
        super(Method.POST , DETAIL_MENU_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_makanan",id_makanan);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

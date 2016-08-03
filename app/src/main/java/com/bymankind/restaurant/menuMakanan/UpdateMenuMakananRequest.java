package com.bymankind.restaurant.menuMakanan;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 8/3/2016.
 */
public class UpdateMenuMakananRequest extends StringRequest{
    private final static String UPDATE_REQUEST_URL = "http://192.168.100.4/login/sql_restaurant.php?operasi=update_menu_makanan";
    private Map<String, String> params;

    public UpdateMenuMakananRequest(int id_makanan, String nama, String harga,String deskripsi, Response.Listener<String> listener){
        super(Request.Method.POST , UPDATE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("id_makanan",id_makanan+"");
        params.put("nama",nama);
        params.put("harga",harga);
        params.put("deskripsi",deskripsi);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

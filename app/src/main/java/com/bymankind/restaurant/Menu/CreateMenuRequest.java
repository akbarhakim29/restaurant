package com.bymankind.restaurant.Menu;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Server-Panduit on 7/27/2016.
 */
public class CreateMenuRequest extends StringRequest{
    private final static String CREATE_MENU_REQUEST_URL = "http://192.168.100.37/restoserver/api/insertMenu";
    private Map<String, String> params;

     public CreateMenuRequest(int id_types_of_menu, String name, int price, String namePicture,String encodedImage, String description, Response.Listener<String> listener){
         super(Method.POST , CREATE_MENU_REQUEST_URL, listener , null);
         params = new HashMap<>();
         params.put("id_types_of_menu",id_types_of_menu+"");
         params.put("name",name);
         params.put("price",price+"");
         params.put("namepic",namePicture);
         params.put("picture",encodedImage);
         params.put("description",description);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

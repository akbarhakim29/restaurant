package com.bymankind.restaurant.Menu;

/**
 * Created by Server-Panduit on 10/28/2016.
 */

public class ImageRecord {

    private String id_menu;
    private String name;
    private String price;
    private String picture;
    private String description;

    public ImageRecord(String id_menu, String name, String price, String picture, String description) {
        this.id_menu = id_menu;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.description = description;
    }

    public String getIDMenu() {
        return id_menu;
    }

    public String getUrl() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}

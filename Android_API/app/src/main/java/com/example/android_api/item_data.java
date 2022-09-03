package com.example.android_api;

public class item_data {
    private String description;
    private String sell_price;
    private String image_url;

    public item_data(String description, String sell_price,String image_url) {
        this.description = description;
        this.sell_price = sell_price;
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public String getSell_price() {
        return sell_price;
    }

    public String getImage() {
        return image_url;
    }
}

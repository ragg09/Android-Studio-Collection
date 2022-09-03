package com.example.listintentnewactivity;

import android.app.Application;

public class MyApplication extends Application {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name = "rene";

}

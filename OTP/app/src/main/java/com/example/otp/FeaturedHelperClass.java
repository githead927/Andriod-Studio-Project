package com.example.otp;

public class FeaturedHelperClass {
    int image;
    String names,desc;

    public FeaturedHelperClass(int image, String names, String desc) {
        this.image = image;
        this.names = names;
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public String getNames() {
        return names;
    }

    public String getDesc() {
        return desc;
    }
}

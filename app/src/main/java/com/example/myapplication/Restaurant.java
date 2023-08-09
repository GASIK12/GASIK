package com.example.myapplication;

public class Restaurant {
    String res_img; // 가게 사진 변수명 바꾸기
    String res_name; // 가게 이름 변수명 바꾸기
    String res_kind;
    int res_price;

    public Restaurant() {
    }

    public Restaurant(String res_img, String res_name, String res_kind, int res_price) {
        this.res_img = res_img;
        this.res_name = res_name;
        this.res_kind = res_kind;
        this.res_price = res_price;
    }

    public String getRes_img() {
        return res_img;
    }

    public void setRes_img(String res_img) {
        this.res_img = res_img;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getRes_kind() {
        return res_kind;
    }

    public void setRes_kind(String res_kind) {
        this.res_kind = res_kind;
    }
    public int getRes_price() {
        return res_price;
    }

    public void setRes_price(int res_price) {
        this.res_price = res_price;
    }


}

package com.hsh.pcroom_customer;

public class RateplanVO {
    private String id;
    private int apply_time;
    private int price;
    private String role;

    public RateplanVO(String id,  int apply_time, int price, String role) {
        this.id = id;
        this.apply_time = apply_time;
        this.price = price;
        this.role = role;
    }

    public RateplanVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getApply_time() {
        return apply_time;
    }

    public void setApply_time(int apply_time) {
        this.apply_time = apply_time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

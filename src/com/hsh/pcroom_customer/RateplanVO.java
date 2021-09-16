package com.hsh.pcroom_customer;

public class RateplanVO {
    private int paln_id;
    private String name;
    private int apply_time;
    private int price;

    public RateplanVO(int paln_id, String name, int apply_time, int price) {
        this.paln_id = paln_id;
        this.name = name;
        this.apply_time = apply_time;
        this.price = price;
    }

    public int getPaln_id() {
        return paln_id;
    }

    public void setPaln_id(int paln_id) {
        this.paln_id = paln_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

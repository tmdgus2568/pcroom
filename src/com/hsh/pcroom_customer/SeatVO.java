package com.hsh.pcroom_customer;

public class SeatVO {
    private int id;
    private String is_usable;

    public SeatVO(int id, String is_usable) {
        this.id = id;
        this.is_usable = is_usable;
    }

    public SeatVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIs_usable() {
        return is_usable;
    }

    public void setIs_usable(String is_usable) {
        this.is_usable = is_usable;
    }
}

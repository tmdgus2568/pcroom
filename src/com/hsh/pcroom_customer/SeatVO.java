package com.hsh.pcroom_customer;

public class SeatVO {
    private int id;
    private char is_usable;

    public SeatVO(int id, char is_usable) {
        this.id = id;
        this.is_usable = is_usable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getIs_usable() {
        return is_usable;
    }

    public void setIs_usable(char is_usable) {
        this.is_usable = is_usable;
    }
}

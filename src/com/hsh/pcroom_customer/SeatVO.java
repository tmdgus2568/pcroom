package com.hsh.pcroom_customer;

public class SeatVO {
    private int id;
    private String is_usable;
    private String customer_id;

    public SeatVO(int id, String is_usable, String customer_id) {
        this.id = id;
        this.is_usable = is_usable;
        this.customer_id = customer_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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

    @Override
    public String toString() {
        return "좌석번호: "+id + " / 사용가능여부: "+(is_usable.equals("N")?"사용중":"빈좌석") +
                " / 아이디: "+(customer_id!=null?customer_id:"");

    }
}

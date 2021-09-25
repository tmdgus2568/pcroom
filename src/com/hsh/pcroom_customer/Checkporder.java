package com.hsh.pcroom_customer;

import java.sql.Date;

public class Checkporder {
    private int porder_id;
    private String customer_id;
    private String payment_way;
    private String request;
    private Date payment_date;
    private int seat_id;
    private int price_sum;
    private int product_id;
    private int num;
    private int price;
    private String name;

    public Checkporder(int porder_id, String customer_id, String payment_way, String request, Date payment_date, int seat_id, int price_sum, int product_id, int num, int price, String name) {
        this.porder_id = porder_id;
        this.customer_id = customer_id;
        this.payment_way = payment_way;
        this.request = request;
        this.payment_date = payment_date;
        this.seat_id = seat_id;
        this.price_sum = price_sum;
        this.product_id = product_id;
        this.num = num;
        this.price = price;
        this.name = name;
    }

    public Checkporder() {
    }

    public int getPorder_id() {
        return porder_id;
    }

    public void setPorder_id(int porder_id) {
        this.porder_id = porder_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getPayment_way() {
        return payment_way;
    }

    public void setPayment_way(String payment_way) {
        this.payment_way = payment_way;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getPrice_sum() {
        return price_sum;
    }

    public void setPrice_sum(int price_sum) {
        this.price_sum = price_sum;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "상품이름: "+name + " / 가격: "+price + " / 수량: "+num;
    }
}

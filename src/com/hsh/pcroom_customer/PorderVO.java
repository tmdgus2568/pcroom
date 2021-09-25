package com.hsh.pcroom_customer;

import java.sql.Date;

public class PorderVO {
    private int id;
    private String customer_id;
    private String payment_way;
    private String payment_status;
    private String request;
    private Date payment_date;
    private int seat_id;
    private String product_name;
    private int price_sum;

    public PorderVO(int id, String customer_id, String payment_way, String payment_status, String request, Date payment_date, int seat_id, int price_sum) {
        this.id = id;
        this.customer_id = customer_id;
        this.payment_way = payment_way;
        this.payment_status = payment_status;
        this.request = request;
        this.payment_date = payment_date;
        this.seat_id = seat_id;
        this.price_sum = price_sum;
    }

    public PorderVO() {
    }

    public int getPrice_sum() {
        return price_sum;
    }

    public void setPrice_sum(int price_sum) {
        this.price_sum = price_sum;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
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

    @Override
    public String toString() {
        return id + "\t" +
                customer_id + "\t"+
                product_name + "\t" +
                payment_way + "\t" +
                (request==null?"없음":request) + "\t" +
                payment_date + "\t" +
                seat_id;
    }
}

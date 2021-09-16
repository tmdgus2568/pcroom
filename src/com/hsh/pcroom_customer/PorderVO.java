package com.hsh.pcroom_customer;

import java.sql.Date;

public class PorderVO {
    private int id;
    private int product_id;
    private String customer_id;
    private String payment_way;
    private char payment_status;
    private String request;
    private Date payment_date;

    public PorderVO(int id, int product_id, String customer_id, String payment_way, char payment_status, String request, Date payment_date) {
        this.id = id;
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.payment_way = payment_way;
        this.payment_status = payment_status;
        this.request = request;
        this.payment_date = payment_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public char getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(char payment_status) {
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
}

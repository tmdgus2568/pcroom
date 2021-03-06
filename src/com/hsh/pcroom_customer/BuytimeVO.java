package com.hsh.pcroom_customer;

import java.sql.Date;

public class BuytimeVO {
    private int id;
    private int plan_id;
    private String customer_id;
    private Date payment_date;

    public BuytimeVO(int id, int plan_id, String customer_id, Date payment_date) {
        this.id = id;
        this.plan_id = plan_id;
        this.customer_id = customer_id;
        this.payment_date = payment_date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }


}

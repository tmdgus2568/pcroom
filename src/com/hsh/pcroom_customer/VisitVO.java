package com.hsh.pcroom_customer;

import java.sql.Date;

public class VisitVO {
    private int id;
    private String customer_id;
    private int seat_id;
    private Date visit_date;
    private Date exit_date;

    public VisitVO(int id, String customer_id, int seat_id, Date visit_date, Date exit_date) {
        this.id = id;
        this.customer_id = customer_id;
        this.seat_id = seat_id;
        this.visit_date = visit_date;
        this.exit_date = exit_date;
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

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public Date getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(Date visit_date) {
        this.visit_date = visit_date;
    }

    public Date getExit_date() {
        return exit_date;
    }

    public void setExit_date(Date exit_date) {
        this.exit_date = exit_date;
    }
}

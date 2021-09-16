package com.hsh.pcroom_customer;

import java.sql.Date;
import java.sql.Timestamp;

public class CustomerVO {
    private String id;
    private String password;
    private String role;
    private Date join_date;
    private int remain_time;
    private String phone;
    private String name;
    private Date birthday;
    private String address;

    // 회원 등록
    public CustomerVO(String id, String password, String phone, String name, Date birthday, String address) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.role = "회원";
    }

    // 비회원 등록
    public CustomerVO(String id) {
        this.id = id;
        this.role = "비회원";
    }

    public CustomerVO(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    public int getRemain_time() {
        return remain_time;
    }

    public void setRemain_time(int remain_time) {
        this.remain_time = remain_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

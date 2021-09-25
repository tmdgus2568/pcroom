package com.hsh.pcroom_customer;

public class PorderdetailVO {
    private int porder_id;
    private int product_id;
    private int num;
    private int price;
    private String name;

    public PorderdetailVO() {
    }

    public PorderdetailVO(int porder_id, int product_id, int num, int price, String name) {
        this.porder_id = porder_id;
        this.product_id = product_id;
        this.num = num;
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPorder_id() {
        return porder_id;
    }

    public void setPorder_id(int porder_id) {
        this.porder_id = porder_id;
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

    @Override
    public String toString() {
        return "이름: " + name + '\t' +
                "가격: " + price + "원" + '\t' +
                "수량: " + num + "개";
    }
}

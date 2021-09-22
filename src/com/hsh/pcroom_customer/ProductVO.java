package com.hsh.pcroom_customer;

public class ProductVO {
    private int id;
    private String name;
    private int price;
    private String kinds;

    public ProductVO(int id, String name, int price, String kinds) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.kinds = kinds;
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

    public ProductVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "이름 : " + name + '\t' +
                "가격 : " + price + "원";
    }
}

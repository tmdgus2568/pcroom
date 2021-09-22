package com.hsh.pcroom_customer.usercomputer;

import java.util.List;

public class UserComputerView {
    public static void display(String msg){
        System.out.println(msg);
    }
    public static void display2(String msg){
        System.out.print(msg);
    }
    public static void displayList(List<?> list){
        for(int i=1;i<=list.size();i++){
            System.out.println(i + ". " + list.get(i-1).toString());
        }
    }
}

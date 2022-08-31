package controller;

import action.CRUDaction;
import model.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerController implements CRUDaction {
    ArrayList<Customer> customerList;

    Scanner scanner = new Scanner(System.in);

    public CustomerController(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }


    public void addCustomer(){
        customerList.add(new Customer(1,"Huong","20/5/1970","Ha Noi","0972645112"));
    }

    @Override
    public void display() {
        System.out.printf("%s%20s%40s%40s%n","ID","Name","Address","Phone");
        for (Customer customer : customerList) {
            System.out.printf("%d%20s%40s%45s%n", customer.getID(), customer.getName(),customer.getAddress(),customer.getPhone());
        }
    }

    @Override
    public void input() {
        int id;
        if (customerList.size() == 0){
            id = 1;
        }else {
            id = (customerList.get(customerList.size() - 1)).getID() + 1;
        }
        System.out.println("Nhập vào tên khách hàng");
        String name = scanner.nextLine();
        System.out.println("Nhập vào ngày sinh khách hàng");
        String birthDay = scanner.nextLine();
        System.out.println("Nhập vào địa chỉ");
        String address = scanner.nextLine();
        System.out.println("Nhập vào số điện thoại");
        String phoneNumber = scanner.nextLine();
        for (Customer customer : customerList) {
            boolean istrue = true;
            while(istrue) {
                if (customer.getPhone().equals(phoneNumber)) {
                    System.out.println("Username này đã tồn tại, vui lòng nhập username khác");
                    phoneNumber = scanner.nextLine();
                    for (Customer customers : customerList) {
                        if (customers.getPhone().equals(phoneNumber)) {
                            System.out.println("Username này đã tồn tại, vui lòng nhập username khác");
                            phoneNumber = scanner.nextLine();
                        } else {
                            istrue = false;
                        }
                    } 
                }
                else {
                    istrue = false;
                }
            }
        }
        customerList.add(new Customer(id,name,birthDay,address,phoneNumber));
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

}

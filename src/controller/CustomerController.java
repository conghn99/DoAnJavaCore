package controller;

import action.CRUDaction;
import model.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerController implements CRUDaction {
    ArrayList<Customer> customerList;
    Check check = new Check();

    Scanner scanner = new Scanner(System.in);

    public CustomerController(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public void addCustomer(){
        customerList.add(new Customer(1,"Huong","20/5/1993","Ha Noi","0912345678"));
        customerList.add(new Customer(2,"Cuong","20/8/1990","Ha Noi","0987654321"));
    }

    @Override
    public void display() {
        System.out.printf("%s%20s%40s%40s%40s%n","ID","Name","Birthday","Address","Phone");
        for (Customer customer : customerList) {
            System.out.printf("%d%20s%40s%40s%45s%n", customer.getID(), customer.getName(), customer.getBirthDay(),customer.getAddress(),customer.getPhone());
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
        String name = new Scanner(System.in).nextLine();
        System.out.println("Nhập vào ngày sinh khách hàng");
        String birthDay = new Scanner(System.in).nextLine();
        System.out.println("Nhập vào địa chỉ");
        String address = new Scanner(System.in).nextLine();
        System.out.println("Nhập vào số điện thoại");
        String stringPhoneNumber = new Scanner(System.in).nextLine();
        String phoneNumber = check.phoneCheck(stringPhoneNumber, customerList);
        customerList.add(new Customer(id,name,birthDay,address,phoneNumber));
    }

    @Override
    public boolean update() {
        display();
        System.out.println("Nhập id khách hàng muốn sửa");
        String stringCustomerId = new Scanner(System.in).nextLine();
        int customerID;
        while(true) {
            try {
                customerID = check.customerid(stringCustomerId,customerList);
                break;
            } catch (Exception e) {
                System.out.println("ID nhập vào phải là kiểu số, xin hãy nhập lại");
                stringCustomerId = new Scanner(System.in).nextLine();
                continue;
            }
        }
        for (Customer customer : customerList) {
            if (customer.getID() == customerID) {
                System.out.println("Nhập vào ngày sinh mới:");
                String newBirthday = new Scanner(System.in).nextLine();
                System.out.println("Nhập vào địa chỉ mới:");
                String newAddress = new Scanner(System.in).nextLine();
                System.out.println("Nhập vào số điện thoại mới:");
                String newStringPhoneNumber = new Scanner(System.in).nextLine();
                String newPhoneNumber = check.phoneCheck(newStringPhoneNumber, customerList);
                customer.setBirthDay(newBirthday);
                customer.setAddress(newAddress);
                customer.setPhone(newPhoneNumber);
                System.out.println("Cập nhật thành công");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

}

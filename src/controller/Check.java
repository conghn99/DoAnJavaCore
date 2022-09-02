package controller;

import model.Account;
import model.Car;
import model.Customer;

import java.util.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Check {
    private final double minPrice = 100000000;
    private final double maxPrice = 12000000000L;
    Scanner scanner = new Scanner(System.in);

    public boolean checkname(ArrayList<Car> arrayList, String name) {
        for (Car car : arrayList) {
            if (car.getCarName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###" + " VND");
        return df.format(value);
    }

    public Date stringToDate(String value) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public boolean checknumber(String value) {
        String regex = "^[0-9]*$";
        return value.matches(regex);
    }

    public int parseQuantity (String value){
        int result = 0;
        if (checknumber(value)) {
            result = Integer.parseInt(value);
        }else {
            boolean checkInput;
            do {
                try {
                    System.out.print("Số lượng phải là số nguyên , vui lòng nhập lại: ");
                    value = scanner.nextLine();
                    checkInput = checknumber(value);
                    if (checkInput){
                        result = Integer.parseInt(value);
                    }
                } catch (Exception e) {
                    checkInput = checknumber(value);
                    scanner.nextLine();
                }
            } while (!checkInput);
        }
        return  result;
    }

    public double parsePrice (String value){
        double result = 0;
        if (checknumber(value)) {
            result = checkMinMaxprice(value);
        }else {
            boolean checkInput;
            do {
                System.out.print("Giá xe phải là kiểu số , vui lòng nhập lại: ");
                value = scanner.nextLine();
                checkInput = checknumber(value);
                if (checkInput){
                    result = checkMinMaxprice(value);
                }
            } while (!checkInput);
        }
        return result;
    }

    public boolean priceminmax (double value) {
        return !((value < minPrice) || (value > maxPrice));

    }

    public double checkMinMaxprice(String value) {
        double result = 0; 
        double parseValue = Double.parseDouble(value);
        boolean checkmm = priceminmax(parseValue);
        if (checkmm){
            result = parseValue;
        }
        else {
            do{
                System.out.print("Giá của xe phải lớn hơn " + withLargeIntegers(minPrice) + " và nhỏ hơn " + withLargeIntegers(maxPrice) + " vui lòng nhập lại: ");
                parseValue = Double.parseDouble(scanner.nextLine());
                checkmm = priceminmax(parseValue);
                if (checkmm){
                    result = parseValue;
                }
            }while (!checkmm);
        }
        return result;
    }

    public boolean checkCarid(String id, ArrayList<Car> carlist){
        for (Car car : carlist){
            if (Integer.parseInt(id) == car.getCarID()){
                return true;
            }
        }
        return false;
    }

    public int carid(String stringID,ArrayList<Car> carlist){
        int id = 0;
        boolean checkcarid = checkCarid(stringID,carlist);
        if (checkcarid){
            id = Integer.parseInt(stringID);
        }else {
            do {
                System.out.println("Id xe không tồn tại, vui lòng nhập lại:");
                stringID = scanner.nextLine();
                checkcarid = checkCarid(stringID, carlist);
                if (checkcarid){
                    id = Integer.parseInt(stringID);
                }
            }while (!checkcarid);
        }
        return id;
    }
    public boolean checkCustomerId(String stringID, ArrayList<Customer> list){
        for (Customer customer :  list){
            if (Integer.parseInt(stringID) == customer.getID()){
                return true;
            }
        }
        return false;
    }

    public int customerid(String stringID, ArrayList<Customer> list){
        int id = 0;
        boolean checkcarid = checkCustomerId(stringID,list);
        if (checkcarid){
            id = Integer.parseInt(stringID);
        }else {
            do {
                System.out.println("Khách hàng ko tồn tại, vui lòng nhập lại");
                stringID = scanner.nextLine();
                checkcarid = checkCustomerId(stringID, list);
                if (checkcarid){
                    id = Integer.parseInt(stringID);
                }
            }while (!checkcarid);
        }
        return id;
    }

    public boolean checkQuantity(String stringQuantity, int id, ArrayList<Car> cars){
        for (Car car : cars) {
            if (id == car.getCarID()) {
                if (Integer.parseInt(stringQuantity) >= car.getQuantity()) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    public int quantityCheck(String stringQuantity, int id, ArrayList<Car> cars){
        int quantity = 0;
        boolean check = checkQuantity(stringQuantity, id, cars);
        if (check){
            quantity = parseQuantity(stringQuantity);
        }else {
            do {
                for (Car car : cars){
                    if (id == car.getCarID()){
                        System.out.println("Số lượng xe được đặt phải nhỏ hơn hoặc bằng " + car.getQuantity());
                        stringQuantity = scanner.nextLine();
                        check = checkQuantity(stringQuantity, id, cars);
                        if(check){
                            quantity = parseQuantity(stringQuantity);
                        }
                    }
                }
            }while (!check);
        }
        return quantity;
    }

    public String carName(ArrayList<Car> cars, int id) {
        String carName = null;
        for (Car car : cars) {
            if (id == car.getCarID()) {
                carName = car.getCarName();
            }
        }
        return carName;
    }

    public String customerName(ArrayList<Customer> customers, int id) {
        String customerName = null;
        for (Customer customer : customers) {
            if (id == customer.getID()) {
                customerName = customer.getName();
            }
        }
        return customerName;
    }

    public String passwordCheck(String password) {
        boolean passwordCheck;
        do {
            String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[.,-_;]).{7,15}$";
            passwordCheck = password.matches(passwordPattern);
            if (!passwordCheck) {
                System.out.println("Password ko hợp lệ, xin hãy nhập lại password");
                password = scanner.nextLine();
            }
        } while (!passwordCheck);
        return password;
    }

    public String userNameCheck(String userName, ArrayList<Account> manages) {
        for (Account acc : manages) {
            boolean istrue = true;
            while(istrue) {
                if (acc.getUserName().equals(userName)) {
                    System.out.println("Username này đã tồn tại, vui lòng nhập username khác");
                    userName = scanner.nextLine();
                    for (Account accs : manages) {
                        if (accs.getUserName().equals(userName)) {
                            System.out.println("Username này đã tồn tại, vui lòng nhập username khác");
                            userName = scanner.nextLine();
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
        return userName;
    }


    public String phoneCheck(String phoneNumber, ArrayList<Customer> customerList) {
        for (Customer customer : customerList) {
            boolean istrue = true;
            while(istrue) {
                if (customer.getPhone().equals(phoneNumber)) {
                    System.out.println("Số điện thoại này đã tồn tại, vui lòng nhập số điện thoại khác");
                    phoneNumber = scanner.nextLine();
                    for (Customer customers : customerList) {
                        if (customers.getPhone().equals(phoneNumber)) {
                            System.out.println("Số điện thoại này đã tồn tại, vui lòng nhập số điện thoại khác");
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
        return phoneNumber;
    }
}

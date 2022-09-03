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
    private final double minPriceBuy = 100000000;
    private final double maxPriceBuy = 12000000000L;
    private final double minPriceHire = 500000;
    private final double maxPriceHire = 2000000;
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

    public double parsePriceBuy (String value){
        double result = 0;
        if (checknumber(value)) {
            result = checkMinMaxpriceBuy(value);
        }else {
            boolean checkInput;
            do {
                System.out.print("Giá xe phải là kiểu số , vui lòng nhập lại: ");
                value = scanner.nextLine();
                checkInput = checknumber(value);
                if (checkInput){
                    result = checkMinMaxpriceBuy(value);
                }
            } while (!checkInput);
        }
        return result;
    }

    public double parsePriceHire (String value){
        double result = 0;
        if (checknumber(value)) {
            result = checkMinMaxpriceHire(value);
        }else {
            boolean checkInput;
            do {
                System.out.print("Giá xe phải là kiểu số , vui lòng nhập lại: ");
                value = scanner.nextLine();
                checkInput = checknumber(value);
                if (checkInput){
                    result = checkMinMaxpriceHire(value);
                }
            } while (!checkInput);
        }
        return result;
    }

    public boolean priceminmaxBuy (double value) {
        return !((value < minPriceBuy) || (value > maxPriceBuy));

    }

    public double checkMinMaxpriceBuy(String value) {
        double result = 0; 
        double parseValue = Double.parseDouble(value);
        boolean checkmm = priceminmaxBuy(parseValue);
        if (checkmm){
            result = parseValue;
        }
        else {
            do{
                System.out.print("Giá bán của xe phải lớn hơn " + withLargeIntegers(minPriceBuy) + " và nhỏ hơn " + withLargeIntegers(maxPriceBuy) + " vui lòng nhập lại: ");
                parseValue = Double.parseDouble(scanner.nextLine());
                checkmm = priceminmaxBuy(parseValue);
                if (checkmm){
                    result = parseValue;
                }
            }while (!checkmm);
        }
        return result;
    }

    public boolean priceminmaxHire (double value) {
        return !((value < minPriceHire) || (value > maxPriceHire));

    }

    public double checkMinMaxpriceHire(String value) {
        double result = 0; 
        double parseValue = Double.parseDouble(value);
        boolean checkmm = priceminmaxHire(parseValue);
        if (checkmm){
            result = parseValue;
        }
        else {
            do{
                System.out.print("Giá thuê của xe phải lớn hơn " + withLargeIntegers(minPriceHire) + " và nhỏ hơn " + withLargeIntegers(maxPriceHire) + " vui lòng nhập lại: ");
                parseValue = Double.parseDouble(scanner.nextLine());
                checkmm = priceminmaxHire(parseValue);
                if (checkmm){
                    result = parseValue;
                }
            }while (!checkmm);
        }
        return result;
    }

    public boolean checkCarid(String id, ArrayList<Car> carList){
        for (Car car : carList){
            if (Integer.parseInt(id) == car.getCarID()){
                return true;
            }
        }
        return false;
    }

    public int carid(String stringID,ArrayList<Car> carList){
        int id = 0;
        boolean checkCarId = checkCarid(stringID, carList);
        if (checkCarId){
            id = Integer.parseInt(stringID);
        }else {
            do {
                System.out.println("Id xe không tồn tại, vui lòng nhập lại:");
                stringID = scanner.nextLine();
                checkCarId = checkCarid(stringID, carList);
                if (checkCarId){
                    id = Integer.parseInt(stringID);
                }
            }while (!checkCarId);
        }
        return id;
    }

    public boolean checkCustomerId(String stringID, ArrayList<Customer> customerList){
        for (Customer customer :  customerList){
            if (Integer.parseInt(stringID) == customer.getID()){
                return true;
            }
        }
        return false;
    }

    public int customerid(String stringID, ArrayList<Customer> customerList){
        int id = 0;
        boolean checkCustomerId = checkCustomerId(stringID,customerList);
        if (checkCustomerId){
            id = Integer.parseInt(stringID);
        }else {
            do {
                System.out.println("Khách hàng ko tồn tại, vui lòng nhập lại");
                stringID = scanner.nextLine();
                checkCustomerId = checkCustomerId(stringID, customerList);
                if (checkCustomerId){
                    id = Integer.parseInt(stringID);
                }
            }while (!checkCustomerId);
        }
        return id;
    }

    public boolean checkEmployeeid(String id, ArrayList<Account> employeeList){
        for (Account account : employeeList){
            if (Integer.parseInt(id) == account.getID() && account.getRole() == 1){
                return true;
            }
        }
        return false;
    }

    public int employeeid(String stringID,ArrayList<Account> employeeList){
        int id = 0;
        boolean checkEmployeeId = checkEmployeeid(stringID,employeeList);
        if (checkEmployeeId){
            id = Integer.parseInt(stringID);
        }else {
            do {
                System.out.println("Không tồn tại nhân viên với ID này, vui lòng nhập lại:");
                stringID = scanner.nextLine();
                checkEmployeeId = checkEmployeeid(stringID, employeeList);
                if (checkEmployeeId){
                    id = Integer.parseInt(stringID);
                }
            }while (!checkEmployeeId);
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

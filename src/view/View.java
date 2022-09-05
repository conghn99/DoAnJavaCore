package view;

import model.Customer;
import controller.CustomerController;
import model.Car;
import controller.CarController;
import model.Order;
import controller.OrderController;
import model.Account;
import controller.AccountController;

import java.util.ArrayList;
import java.util.Scanner;

public class View {

    private ArrayList<Car> carList;
    private ArrayList<Order> orderList;
    private AccountController accountController;
    private ArrayList<Account> accountList;
    private ArrayList<Customer> customerList;

    Scanner scanner = new Scanner(System.in);

    public void main() {
        carList = new ArrayList<>();
        CarController carController = new CarController(carList);
        carController.addCar();

        accountList = new ArrayList<>();
        AccountController accountController = new AccountController(accountList);
        accountController.addAccount();

        orderList = new ArrayList<>();
        OrderController orderController = new OrderController(orderList);

        customerList = new ArrayList<>();
        CustomerController customerController = new CustomerController(customerList);
        customerController.addCustomer();

        while (true) login(carController, orderController,customerList,customerController,orderList);
    }

    public void login(CarController carController, OrderController orderController, ArrayList<Customer> customerList, CustomerController customerController, ArrayList<Order> orderList){
        System.out.println("---- ĐĂNG NHẬP ----");
        accountController = new AccountController(accountList);
        int check = accountController.checkLogin();
        if (check == 1){
            while (true){
                menuEmployee(carController, orderController, customerList, customerController, orderList);
            }
        }else if (check == 2) {
            while (true){
                menuManager(accountController, carController, orderController, customerList, customerController, orderList);
            }
        } else {
            System.out.println("Sai tài khoản hoặc mật khẩu, Hãy nhập lại");
            login(carController, orderController,customerList,customerController,orderList);
        }
    }

    public void menuEmployee (CarController carController, OrderController orderController, ArrayList<Customer> customerList, CustomerController customerController, ArrayList<Order> orderList) {

        System.out.println("---- CHƯƠNG TRÌNH CỦA NHÂN VIÊN ----");
        System.out.println("Chọn chức năng theo số :");
        System.out.println("1 . Xem danh sách xe trong kho ");
        System.out.println("2 . Thêm mới xe");
        System.out.println("3 . Cập nhật xe");
        System.out.println("4 . Xóa xe");
        System.out.println("5 . Tìm kiếm theo tên");
        System.out.println("6 . Tìm kiếm theo giá mua");
        System.out.println("7 . Sắp xếp theo tên");
        System.out.println("8 . Sắp xếp theo giá mua tăng dần");
        System.out.println("9 . Sắp xếp theo giá mua giảm dần");
        System.out.println("10 . Sửa thông tin khách hàng");
        System.out.println("11 . Quản lý đơn hàng mua xe");
        System.out.println("12 . Quản lý đơn hàng thuê xe");
        System.out.println("13 . Đăng xuất");
        System.out.println("0 . Thoát");
        System.out.println("Chọn chức năng :");

        int key = new Scanner(System.in).nextInt();
        switch (key) {
        case 0 :
            System.out.println("GOODBYE!!!");
            System.exit(0);
            break;
        case 1 :
            carController.display();
            break;
        case 2 :
            carController.input();     
            break;
        case 3 :
            carController.update();
            break;
        case 4 :
            carController.delete();
            break;
        case 5 :
            carController.findCarByName();
            break;
        case 6 :
            carController.findCarByPriceBuy();
            break;
        case 7 :
            carController.sortCarByName();
            break;
        case 8 :
            carController.sortCarByPriceBuyAscending();
            break;
        case 9 :
            carController.sortCarByPriceBuyDescending();
            break;
        case 10 :
            customerController.update();
            break;
        case 11 :
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ ĐƠN MUA XE ----");
            System.out.println("Chọn chức năng theo số :");
            System.out.println("1 . Lên đơn mua xe");
            System.out.println("2 . Xem danh sách đơn mua xe");
            System.out.println("3 . Sửa trạng thái đơn mua xe");
            System.out.println("Chọn chức năng :");
            int a = new Scanner(System.in).nextInt();
            switch(a) {
                case 1:
                    orderController.inputBuy(carList, customerList);
                    break;
                case 2:
                    if(orderList.size() == 0) {
                        System.out.println("Chưa có đơn hàng nào");
                    } else {
                        orderController.displayBuy(carList,customerList);
                    }
                    break;
                case 3:
                    if(orderList.size() == 0) {
                        System.out.println("Chưa có đơn hàng nào");
                    } else {
                        orderController.displayBuy(carList,customerList);
                        orderController.updateStatus(carList);
                    }
                    break;
            }
            break;
        case 12 :
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ ĐƠN THUÊ XE ----");
            System.out.println("Chọn chức năng theo số :");
            System.out.println("1 . Lên đơn thuê xe");
            System.out.println("2 . Xem danh sách đơn thuê xe");
            System.out.println("3 . Sửa trạng thái đơn thuê xe");
            System.out.println("Chọn chức năng :");
            int b = new Scanner(System.in).nextInt();
            switch(b) {
                case 1:
                    orderController.inputHire(carList, customerList);
                    break;
                case 2:
                    if(orderList.size() == 0) {
                        System.out.println("Chưa có đơn hàng nào");
                    } else {
                        orderController.displayHire(carList,customerList);
                    }
                    break;
                case 3:
                    if(orderList.size() == 0) {
                        System.out.println("Chưa có đơn hàng nào");
                    } else {
                        orderController.displayHire(carList,customerList);
                        orderController.updateStatus(carList);
                    }
                    break;
            }
            break;
        case 13 :
            login(carController, orderController, customerList, customerController, orderList);
            break;
        }
    }

    public void menuManager(AccountController accountController, CarController carController, OrderController orderController, ArrayList<Customer> customerList, CustomerController customerController, ArrayList<Order> orderList){
        System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ NHÂN VIÊN ----");
        System.out.println("Chọn chức năng theo số :");
        System.out.println("1 . Hiển thị danh sách nhân viên");
        System.out.println("2 . Thêm nhân viên");
        System.out.println("3 . Sửa nhân viên");
        System.out.println("4 . Xoá nhân viên");
        System.out.println("5 . Hiển thị đơn hàng và doanh thu trong khoảng thời gian");
        System.out.println("6 . Đăng xuất");
        System.out.println("0 . Thoát");
        System.out.println("Chọn chức năng :");

        int key = new Scanner(System.in).nextInt();
        switch (key) {
            case 0 :
                System.out.println("GOODBYE!!!");
                System.exit(0);
                break;
            case 1 :
                accountController.display();
                break;
            case 2 :
                accountController.input();
                break;
            case 3 :
                accountController.update();
                break;
            case 4 :
                accountController.delete();
                break;
            case 5 :
                orderController.getListTime();
                orderController.checkTime(carList,customerList);
                break;
            case 6 :
                login(carController, orderController, customerList, customerController, orderList);
                break;
        }
    }
}

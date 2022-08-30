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
        System.out.println("4 . Xóa");
        System.out.println("5 . Tìm kiếm theo tên");
        System.out.println("6 . Tìm kiếm theo giá");
        System.out.println("7 . Sắp xếp theo tên");
        System.out.println("8 . Sắp xếp theo giá tăng dần");
        System.out.println("9 . Sắp xếp theo giá giảm dần");
        System.out.println("10 . Lên đơn hàng");
        System.out.println("11 . Danh sách đơn hàng");
        System.out.println("12 . Sửa trạng thái đơn hàng");
        System.out.println("13 . Hiển thị đơn hàng trong khoảng thời gian");
        System.out.println("14 . Đăng xuất");
        System.out.println("0 . Thoát");
        System.out.println("Chọn chức năng :");

        int key = scanner.nextInt();

        switch (key) {
            case 0 :
                System.out.println("GOODBYE!!!");
                System.exit(0);
                break;
            case 1 :
                carController.display();
                break;
            case 2 :
                scanner.nextLine();
                carController.input();
                System.out.println("Thêm xe mới thành công");
                break;
            case 3 :
                if (carController.update()){
                    System.out.println("Sửa thành công");
                }else {
                    System.out.println("Không tìm thấy id xe");
                }
                break;
            case 4 :
                if (carController.delete()){
                    System.out.println("Xoá thành công");
                }else {
                    System.out.println("Không tìm thấy id xe");
                }
                break;
            case 5 :
                carController.findCarByName();
                break;
            case 6 :
                carController.findCarByPrice();
                break;
            case 7 :
                carController.sortCarByName();
                break;
            case 8 :
                carController.sortCarByPriceAscending();
                break;
            case 9 :
                carController.sortCarByPriceDescending();
                break;
            case 10 :
                orderController.input(carList,customerList);
                break;
            case 11 :
                if(orderList.size() == 0) {
                    System.out.println("Chưa có đơn hàng nào");
                } else {
                    orderController.display(carList,customerList);
                }
                break;
            case 12 :
                orderController.display(carList,customerList);
                if (orderController.updateStatus()){
                    System.out.println("Cập nhật thành công");
                    orderController.display(carList,customerList);
                }else {
                    System.out.println("Không tìm thấy id đơn hàng");
                }
                break;
            case 13 :
                orderController.getListTime();
                orderController.checkTime(carList,customerList);
                break;
            case 14 :
                login(carController, orderController, customerList, customerController, orderList);
                break;
        }
    }

    public void menuManager(AccountController accountController, CarController carController, OrderController orderController, ArrayList<Customer> customerList, CustomerController customerController, ArrayList<Order> orderList){
        System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ NHÂN VIÊN ----");
        System.out.println("Chọn chức năng theo số :");
        System.out.println("1 . Hiển thị danh sách nhân viên ");
        System.out.println("2 . Thêm nhân viên ");
        System.out.println("3 . Sửa nhân viên");
        System.out.println("4 . Xoá nhân viên");
        System.out.println("5 . Đăng xuất");
        System.out.println("0 . Thoát");
        System.out.println("Chọn chức năng :");

        int key = scanner.nextInt();

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
                if (accountController.update()){
                    System.out.println("Sửa thành công");
                }else {
                    System.out.println("Id không tồn tại");
                }
                break;
            case 4 :
                if (accountController.delete()){
                    System.out.println("Xoá thành công");
                }else {
                    System.out.println("Id không tồn tại");
                }
                break;
            case 5 :
                login(carController, orderController, customerList, customerController, orderList);
                break;
        }
    }
}

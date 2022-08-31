package controller;

import model.Car;
import model.Order;
import model.Order.Status;
import model.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OrderController {
    ArrayList<Order> oderlist;
    ArrayList<Date> listDate;
    Check check = new Check();
    Scanner scanner = new Scanner(System.in);

    public OrderController(ArrayList<Order> oderlist) {
        this.oderlist = oderlist;
    }

    public void display(ArrayList<Car> arrayList, ArrayList<Customer> customerList) {
        double estimateRevenue = 0;
        double actualRevenue = 0;
        System.out.printf("%s%20s%20s%20s%20s%20s%20s%n","ID","Car Name","Customer Name","Quantity","Total","Status","Date");
        for (Order order : oderlist) {
            String date = order.getDay() + " / " + order.getMonth() + " / " + "2022";
            System.out.printf("%d%20s%20s%20d%20s%20s%20s%n", order.getOderID(), check.carName(arrayList,order.getCarID()), check.customerName(customerList,order.getCustomerID()), order.getQuantity(), check.withLargeIntegers(order.getTotal()),order.getStatus(),date);
            if (order.getStatus() == Status.ORDER) {
                estimateRevenue += order.getTotal();
            } else if (order.getStatus() == Status.PAID) {
                actualRevenue += order.getTotal();
            }
            
        }
        System.out.println("Doanh thu ước tính: " + check.withLargeIntegers(estimateRevenue));
        System.out.println("Doanh thu thực tế: " + check.withLargeIntegers(actualRevenue));
        System.out.println();
    }

    public void input(ArrayList<Car> arrayList, ArrayList<Customer> customerList) {
        CarController carController = new CarController(arrayList);
        CustomerController customerController = new CustomerController(customerList);
        int oderID;
        if (oderlist.size() == 0){
            oderID = 1;
        }else {
            oderID = oderlist.get(oderlist.size() - 1).getOderID() + 1;
        }
        carController.displayCar();
        System.out.println("Mời nhập id sản phẩm muốn thêm:");
        String stringcarID = new Scanner(System.in).nextLine();
        int carID = check.carid(stringcarID,arrayList);
        customerController.display();
        System.out.println("Mời nhập id khách hàng:");
        String stringagencyID = new Scanner(System.in).nextLine();
        int agencyID = check.customerid(stringagencyID,customerList);
        System.out.println("Mời nhập số lượng");
        String quantity = new Scanner(System.in).nextLine();
        int quantityInt = check.quantityCheck(quantity,carID,arrayList);
        System.out.println("Mời nhập ngày đặt hàng ");
        int day = scanner.nextInt();
        System.out.println("Mời nhập tháng đặt hàng ");
        int month = scanner.nextInt();
        double total = 0;
        for (Car car : arrayList) {
            if (carID != car.getCarID()) continue;
            total = Integer.parseInt(quantity) * car.getPrice();
            car.setQuantity(car.getQuantity() - quantityInt);
            System.out.printf("Xe %s còn lại %d chiếc trong kho", car.getCarName(), car.getQuantity());
            System.out.println();
            System.out.println();
            break;
        }
        Order order = new Order(oderID,carID,agencyID,quantityInt,day,month,total, Order.Status.ORDER);
        oderlist.add(order);
    }

    public boolean updateStatus(ArrayList<Car> arrayList){
        System.out.println("Nhập vào id đơn hàng");
        int id = scanner.nextInt();
        for (Order order : oderlist){
            if (order.getOderID() == id){
                System.out.println("Chọn trạng thái mới (1. Thanh toán - 2. Huỷ bỏ)");
                int status = scanner.nextInt();
                switch (status){
                    case 1 :
                        order.setStatus(Order.Status.PAID);
                        break;
                    case 2 :
                        order.setStatus(Order.Status.CANCEL);
                        for (Car car : arrayList) {
                            if(car.getCarName().equals(check.carName(arrayList,order.getCarID()))) {
                                car.setQuantity(car.getQuantity() + order.getQuantity());
                            }
                        }
                        break;
                }
                return true;
            }
        }
        return false;
    }

    public void getListTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        listDate = new ArrayList<>();
        for (Order order : oderlist){
            String dateInString = order.getDay() + "/" + order.getMonth() + "/" + "2022";
            try {
                Date date = formatter.parse(dateInString);
                listDate.add(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkTime(ArrayList<Car> arrayList, ArrayList<Customer> customerList){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int check1 = 0;
        double estimateRevenues = 0;
        double actualRevenues = 0;
        System.out.println("Nhập vào ngày bắt đầu");
        int dayStart = scanner.nextInt();
        System.out.println("Nhập vào tháng bắt đầu");
        int monthStart = scanner.nextInt();
        System.out.println("Nhập vào ngày kết thúc");
        int dayEnd = scanner.nextInt();
        System.out.println("Nhập vào tháng kết thúc");
        int monthEnd = scanner.nextInt();
        String start = dayStart + "/" + monthStart + "/" + "2022";
        String end = dayEnd + "/" + monthEnd + "/" + "2022";
        try {
            Date dateStart = formatter.parse(start);
            Date dateEnd = formatter.parse(end);
            System.out.printf("%s%20s%20s%20s%20s%20s%20s%n","ID","Car Name","Customer Name","Quantity","Total","Status","Date");
            for (Date date : listDate){
                if (dateStart.before(date) && dateEnd.after(date)){
                    String dateString = oderlist.get(check1).getDay() + " / " + oderlist.get(check1).getMonth() + " / 2022";
                    System.out.printf("%d%20s%20s%20d%20s%20s%20s%n", oderlist.get(check1).getOderID(), check.carName(arrayList,oderlist.get(check1).getCarID()), check.customerName(customerList,oderlist.get(check1).getCustomerID()), oderlist.get(check1).getQuantity(), check.withLargeIntegers(oderlist.get(check1).getTotal()),oderlist.get(check1).getStatus(),dateString);
                    if (oderlist.get(check1).getStatus() == Status.ORDER) {
                        estimateRevenues += oderlist.get(check1).getTotal();
                    } else if (oderlist.get(check1).getStatus() == Status.PAID) {
                        actualRevenues += oderlist.get(check1).getTotal();
                    }
                    
                }
                check1++;
            }
            System.out.println();
            System.out.println("Doanh thu ước tính: " + check.withLargeIntegers(estimateRevenues) + ".");
            System.out.println("Doanh thu thực tế: " + check.withLargeIntegers(actualRevenues) + ".");
            System.out.println();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
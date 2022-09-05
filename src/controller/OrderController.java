package controller;

import model.Car;
import model.Order;
import model.Order.Status;
import model.Customer;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderController {
    ArrayList<Order> oderList;
    ArrayList<Date> listDate;
    
    Check check = new Check();
    Scanner scanner = new Scanner(System.in);

    public OrderController(ArrayList<Order> oderList) {
        this.oderList = oderList;
    }

    public void displayBuy(ArrayList<Car> arrayList, ArrayList<Customer> customerList) {
        double estimateRevenue = 0;
        double actualRevenue = 0;
        System.out.printf("%s%20s%20s%20s%20s%20s%20s%20s%n","ID","Car Name","Car Status","Customer Name","Quantity","Total","Order Status","Date Buy");
        for (Order order : oderList) {
            if(order.getCarStatus().equals("CarForBuy")) {
                String date = order.getDayBuy() + " / " + order.getMonthBuy() + " / " + order.getYearBuy();
                System.out.printf("%d%20s%20s%20s%20d%20s%20s%20s%n", order.getOderID(), check.carName(arrayList,order.getCarID()), order.getCarStatus(), check.customerName(customerList,order.getCustomerID()), order.getQuantity(), check.withLargeIntegers(order.getTotal()),order.getOrderStatus(),date);
                if (order.getOrderStatus() == Status.ORDER) {
                    estimateRevenue += order.getTotal();
                } else if (order.getOrderStatus() == Status.PAID) {
                    actualRevenue += order.getTotal();
                }
            }
        }

        System.out.println("Doanh thu ước tính: " + check.withLargeIntegers(estimateRevenue));
        System.out.println("Doanh thu thực tế: " + check.withLargeIntegers(actualRevenue));
        System.out.println();
    }

    public void displayHire(ArrayList<Car> arrayList, ArrayList<Customer> customerList) {
        double estimateRevenue = 0;
        double actualRevenue = 0;
        System.out.printf("%s%20s%20s%20s%20s%20s%20s%20s%25s%n","ID","Car Name","Car Status","Customer Name","Quantity","Total","Order Status","Date Start Hire","Date End Hire");
        for (Order order : oderList) {
            if (order.getCarStatus().equals("CarForHire")) {
                String dateStart = order.getDayStartHire() + " / " + order.getMonthStartHire() + " / " + order.getYearStartHire();
                String dateEnd = order.getDayEndHire() + " / " + order.getMonthEndHire() + " / " + order.getYearEndHire();
                System.out.printf("%d%20s%20s%20s%20d%20s%20s%20s%25s%n", order.getOderID(), check.carName(arrayList,order.getCarID()), order.getCarStatus(), check.customerName(customerList,order.getCustomerID()), order.getQuantity(), check.withLargeIntegers(order.getTotal()),order.getOrderStatus(),dateStart,dateEnd);
                if (order.getOrderStatus() == Status.ORDER) {
                    estimateRevenue += order.getTotal();
                } else if (order.getOrderStatus() == Status.PAID) {
                    actualRevenue += order.getTotal();
                }
            }
        }

        System.out.println("Doanh thu ước tính: " + check.withLargeIntegers(estimateRevenue));
        System.out.println("Doanh thu thực tế: " + check.withLargeIntegers(actualRevenue));
        System.out.println();
    }

    public void inputBuy(ArrayList<Car> arrayList, ArrayList<Customer> customerList) {
        
        CustomerController customerController = new CustomerController(customerList);
        int oderID;
        if (oderList.size() == 0){
            oderID = 1;
        }else {
            oderID = oderList.get(oderList.size() - 1).getOderID() + 1;
        }
        System.out.println("Chọn hành động");
        System.out.println("1 . Nhập khách hàng mới");
        System.out.println("2 . Chọn khách hàng cũ");
        int a = new Scanner(System.in).nextInt();
        switch (a) {
            case 1: {
                customerController.input();
                customerController.display();
                inputOrderBuy(arrayList, customerList, oderID);
                break;
            }
            case 2: {
                customerController.display();
                inputOrderBuy(arrayList, customerList, oderID);
                break;
            }
        }
    }

    public void inputHire(ArrayList<Car> arrayList, ArrayList<Customer> customerList) {
        
        CustomerController customerController = new CustomerController(customerList);
        int oderID;
        if (oderList.size() == 0){
            oderID = 1;
        }else {
            oderID = oderList.get(oderList.size() - 1).getOderID() + 1;
        }
        System.out.println("Chọn hành động");
        System.out.println("1 . Nhập khách hàng mới");
        System.out.println("2 . Chọn khách hàng cũ");
        int a = new Scanner(System.in).nextInt();
        switch (a) {
            case 1: {
                customerController.input();
                customerController.display();
                inputOrderHire(arrayList, customerList, oderID);
                break;
            }
            case 2: {
                customerController.display();
                inputOrderHire(arrayList, customerList, oderID);
                break;
            }
        }
    }

    public boolean updateStatus(ArrayList<Car> arrayList){
        System.out.println("Nhập vào id đơn hàng");
        String stringId = new Scanner(System.in).nextLine();
        int orderID;
        while(true) {
            try {
                orderID = check.orderid(stringId,oderList);
                break;
            } catch (Exception e) {
                System.out.println("ID nhập vào phải là kiểu số, xin hãy nhập lại");
                stringId = scanner.nextLine();
                continue;
            }
        }
        for (Order order : oderList){
            if (order.getOderID() == orderID){
                System.out.println("Chọn trạng thái mới (1. Thanh toán - 2. Huỷ bỏ)");
                int status = scanner.nextInt();
                switch (status){
                    case 1 :
                        order.setStatus(Order.Status.PAID);
                        System.out.println("Cập nhật thành công");
                        break;
                    case 2 :
                        order.setStatus(Order.Status.CANCEL);
                        for (Car car : arrayList) {
                            if(car.getCarName().equals(check.carName(arrayList,order.getCarID())) && car.getCarStatus().equals(order.getCarStatus())) {
                                car.setQuantity(car.getQuantity() + order.getQuantity());
                            }
                        }
                        System.out.println("Cập nhật thành công");
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
        for (Order order : oderList){
            String dateInString = order.getDayBuy() + "/" + order.getMonthBuy() + "/" + order.getYearBuy();
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
        System.out.println("Nhập vào năm bắt đầu");
        int yearStart = scanner.nextInt();
        System.out.println("Nhập vào ngày kết thúc");
        int dayEnd = scanner.nextInt();
        System.out.println("Nhập vào tháng kết thúc");
        int monthEnd = scanner.nextInt();
        System.out.println("Nhập vào năm kết thúc");
        int yearEnd = scanner.nextInt();
        String start = dayStart + "/" + monthStart + "/" + yearStart;
        String end = dayEnd + "/" + monthEnd + "/" + yearEnd;
        try {
            Date dateStart = formatter.parse(start);
            Date dateEnd = formatter.parse(end);
            System.out.printf("%s%20s%20s%20s%20s%20s%20s%20s%n","ID","Car Name","Car Status","Customer Name","Quantity","Total","Order Status","Date Buy");
            for (Date date : listDate){
                if (dateStart.before(date) && dateEnd.after(date)){
                    String dateString = oderList.get(check1).getDayBuy() + " / " + oderList.get(check1).getMonthBuy() + " / " + oderList.get(check1).getYearBuy();
                    System.out.printf("%d%20s%20s%20s%20d%20s%20s%20s%n", oderList.get(check1).getOderID(), check.carName(arrayList,oderList.get(check1).getCarID()), oderList.get(check1).getCarStatus(), check.customerName(customerList,oderList.get(check1).getCustomerID()), oderList.get(check1).getQuantity(), check.withLargeIntegers(oderList.get(check1).getTotal()),oderList.get(check1).getOrderStatus(),dateString);
                    if (oderList.get(check1).getOrderStatus() == Status.ORDER) {
                        estimateRevenues += oderList.get(check1).getTotal();
                    } else if (oderList.get(check1).getOrderStatus() == Status.PAID) {
                        actualRevenues += oderList.get(check1).getTotal();
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

    public void inputOrderBuy(ArrayList<Car> arrayList, ArrayList<Customer> customerList, int oderID) {
        CarController carController = new CarController(arrayList);
        System.out.println("Mời nhập id khách hàng:");
        String stringCustomerID = new Scanner(System.in).nextLine();
        int customerID;
        while(true) {
            try {
                customerID = check.customerid(stringCustomerID,customerList);
                break;
            } catch (Exception e) {
                System.out.println("ID nhập vào phải là kiểu số, xin hãy nhập lại");
                stringCustomerID = scanner.nextLine();
                continue;
            }
        }
        carController.displayCarBuy();
        String carStatus = "CarForBuy";
        System.out.println("Mời nhập id sản phẩm muốn thêm:");
        String stringcarID = new Scanner(System.in).nextLine();
        int carID;
        while(true) {
            try {
                carID = check.carid(stringcarID,arrayList);
                break;
            } catch (Exception e) {
                System.out.println("ID nhập vào phải là kiểu số, xin hãy nhập lại");
                stringcarID = scanner.nextLine();
                continue;
            }
        }
        System.out.println("Mời nhập số lượng");
        String quantity = new Scanner(System.in).nextLine();
        int quantityInt;
        while(true) {
            try {
                quantityInt = check.quantityCheck(quantity,carID,arrayList);
                break;
            } catch (Exception e) {
                System.out.println("Số lượng nhập vào phải là kiểu số, xin hãy nhập lại");
                quantity = scanner.nextLine();
                continue;
            }
        }
        System.out.println("Mời nhập ngày đặt hàng ");
        int day;
        while(true) {
            try {
                day = new Scanner(System.in).nextInt();
                if (day > 31 || day < 1) {
                    System.out.println("Ngày phải nằm trong khoảng từ 1-31, xin hãy nhập lại");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ngày nhập vào phải là kiểu số, xin hãy nhập lại");
                continue;
            }
        }
        System.out.println("Mời nhập tháng đặt hàng ");
        int month;
        while(true) {
            try {
                month = new Scanner(System.in).nextInt();
                if (month > 12 || month < 1) {
                    System.out.println("Tháng phải nằm trong khoảng từ 1-12, xin hãy nhập lại");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Tháng nhập vào phải là kiểu số, xin hãy nhập lại");
                continue;
            }
        }
        System.out.println("Mời nhập năm đặt hàng ");
        int year;
        while(true) {
            try {
                year = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Năm nhập vào phải là kiểu số, xin hãy nhập lại");
                continue;
            }
        }
        double total = 0;
        for (Car car : arrayList) {
            if (carID != car.getCarID()) continue;
            total = Integer.valueOf(quantityInt) * car.getPriceBuy();
            car.setQuantity(car.getQuantity() - quantityInt);
            System.out.printf("Xe %s để mua còn lại %d chiếc trong kho", car.getCarName(), car.getQuantity());
            System.out.println();
            System.out.println();
            break;
        }
        Order order = new Order(oderID,carID,carStatus,customerID,quantityInt,day,month,year,total, Order.Status.ORDER);
        oderList.add(order);
    }

    public void inputOrderHire(ArrayList<Car> arrayList, ArrayList<Customer> customerList, int oderID) {
        CarController carController = new CarController(arrayList);
        System.out.println("Mời nhập id khách hàng:");
        String stringCustomerID = new Scanner(System.in).nextLine();
        int customerID;
        while(true) {
            try {
                customerID = check.customerid(stringCustomerID,customerList);
                break;
            } catch (Exception e) {
                System.out.println("ID nhập vào phải là kiểu số, xin hãy nhập lại");
                stringCustomerID = scanner.nextLine();
                continue;
            }
        }
        carController.displayCarHire();
        String carStatus = "CarForHire";
        System.out.println("Mời nhập id sản phẩm muốn thêm:");
        String stringcarID = new Scanner(System.in).nextLine();
        int carID;
        while(true) {
            try {
                carID = check.carid(stringcarID,arrayList);
                break;
            } catch (Exception e) {
                System.out.println("ID nhập vào phải là kiểu số, xin hãy nhập lại");
                stringcarID = scanner.nextLine();
                continue;
            }
        }
        System.out.println("Mời nhập số lượng thuê");
        String quantity = new Scanner(System.in).nextLine();
        int quantityInt;
        while(true) {
            try {
                quantityInt = check.quantityCheck(quantity,carID,arrayList);
                break;
            } catch (Exception e) {
                System.out.println("Số lượng nhập vào phải là kiểu số, xin hãy nhập lại");
                quantity = scanner.nextLine();
                continue;
            }
        }
        System.out.println("Mời nhập ngày thuê xe ");
        int dayStart;
        while(true) {
            try {
                dayStart = new Scanner(System.in).nextInt();
                if (dayStart > 31 || dayStart < 1) {
                    System.out.println("Ngày phải nằm trong khoảng từ 1-31, xin hãy nhập lại");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ngày nhập vào phải là kiểu số, xin hãy nhập lại");
                continue;
            }
        }
        System.out.println("Mời nhập tháng thuê xe ");
        int monthStart;
        while(true) {
            try {
                monthStart = new Scanner(System.in).nextInt();
                if (monthStart > 12 || monthStart < 1) {
                    System.out.println("Tháng phải nằm trong khoảng từ 1-12, xin hãy nhập lại");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Tháng nhập vào phải là kiểu số, xin hãy nhập lại");
                continue;
            }
        }
        System.out.println("Mời nhập năm thuê xe ");
        int yearStart;
        while(true) {
            try {
                yearStart = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Năm nhập vào phải là kiểu số, xin hãy nhập lại");
                continue;
            }
        }
        System.out.println("Mời nhập ngày trả xe ");
        int dayEnd;
        while(true) {
            try {
                dayEnd = new Scanner(System.in).nextInt();
                if (dayEnd > 31 || dayEnd < 1) {
                    System.out.println("Ngày phải nằm trong khoảng từ 1-31, xin hãy nhập lại");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ngày nhập vào phải là kiểu số, xin hãy nhập lại");
                continue;
            }
        }
        System.out.println("Mời nhập tháng trả xe ");
        int monthEnd;
        while(true) {
            try {
                monthEnd = new Scanner(System.in).nextInt();
                if (monthEnd > 12 || monthEnd < 1) {
                    System.out.println("Tháng phải nằm trong khoảng từ 1-12, xin hãy nhập lại");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Tháng nhập vào phải là kiểu số, xin hãy nhập lại");
                continue;
            }
        }
        System.out.println("Mời nhập năm trả xe ");
        int yearEnd;
        while(true) {
            try {
                yearEnd = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Năm nhập vào phải là kiểu số, xin hãy nhập lại");
                continue;
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = dayStart + "/" + monthStart + "/" + yearStart;
        String endDate = dayEnd + "/" + monthEnd + "/" + yearEnd;
        Date date1 = null;
        Date date2 = null;
        long getDaysDiff = 0;
        try {
            date1 = simpleDateFormat.parse(startDate);
            date2 = simpleDateFormat.parse(endDate);
            long getDiff = date2.getTime() - date1.getTime();
            getDaysDiff = getDiff / (24 * 60 * 60 * 1000) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double total = 0;
        for (Car car : arrayList) {
            if (carID != car.getCarID()) continue;
            total = Integer.valueOf(quantityInt) * car.getPriceHire() * getDaysDiff;
            car.setQuantity(car.getQuantity() - quantityInt);
            System.out.printf("Xe %s để thuê còn lại %d chiếc trong kho", car.getCarName(), car.getQuantity());
            System.out.println();
            System.out.println();
            break;
        }
        Order order = new Order(oderID,carID,carStatus,customerID,quantityInt,dayStart,monthStart,yearStart,dayEnd,monthEnd,yearEnd,total, Order.Status.ORDER);
        oderList.add(order);
    }

}
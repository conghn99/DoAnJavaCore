package controller;

import action.CRUDaction;
import model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CarController implements CRUDaction {
    Check check = new Check();
    ArrayList<Car> carArrayList = new ArrayList<>();
    
    Scanner scanner = new Scanner(System.in);

    public CarController(ArrayList<Car> carArrayList) {
        this.carArrayList = carArrayList;
    }

    public void addCar(){
        carArrayList.add(new Car(1, "Lamborghini", 50, 1000000000, 1000000));
        carArrayList.add(new Car(2, "Ferrari", 30, 3000000000L, 1200000));
        carArrayList.add(new Car(3, "Limousine", 100, 700000000,800000));
    }

    @Override
    public void display() {
        
        System.out.printf("%s%20s%20s%20s%30s%n","ID","Name","Quantity","Price Buy","Price Hire Per Day");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25s%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(), check.withLargeIntegers(car.getPriceBuy()), check.withLargeIntegers(car.getPriceHirePerDay()));
        }
    }

    @Override
    public void input() {
        int id;
        if (carArrayList.size() == 0){
            id = 1;
        }else {
             id = carArrayList.get(carArrayList.size() - 1).getCarID() + 1;
        }
        System.out.println("Vui lòng nhập tên: ");
        String name = scanner.nextLine();
        boolean isTrue = true;
        while(isTrue) {
            if (!check.checkname(carArrayList,name)){
                System.out.println("Tên sản phẩm đã tồn tại. Vui lòng nhập lại");
                input();
            }
            else {
                isTrue = false;
            }
        }
        System.out.println("Vui lòng nhập số lượng");
        String stringQuantity = scanner.nextLine();
        int quantity = check.parseQuantity(stringQuantity);
        System.out.println("Vui lòng nhập giá xe cho mua");
        String stringPriceBuy = scanner.nextLine();
        double priceBuy = check.parsePriceBuy(stringPriceBuy);
        System.out.println("Vui lòng nhập giá xe cho thuê");
        String stringPriceHire = scanner.nextLine();
        double priceHire = check.parsePriceHire(stringPriceHire);
        carArrayList.add(new Car(id,name,quantity,priceBuy,priceHire));
        System.out.println("Thêm xe mới thành công");
    }

    @Override
    public boolean update(){
        display();
        System.out.println("Nhập id xe muốn sửa");
        String stringIdCar = new Scanner(System.in).nextLine();
        int carID;
        while(true) {
            try {
                carID = check.carid(stringIdCar,carArrayList);
                break;
            } catch (Exception e) {
                System.out.println("ID nhập vào phải là kiểu số, xin hãy nhập lại");
                stringIdCar = scanner.nextLine();
                continue;
            }
        }
        for (Car car : carArrayList) {
            if (car.getCarID() == carID) {
                System.out.println("Nhập số lượng mới");
                String stringQuantity = scanner.nextLine();
                int quantity = check.parseQuantity(stringQuantity);
                System.out.println("Nhập giá tiền bán mới");
                String stringPriceBuy = scanner.nextLine();
                double priceBuy = check.parsePriceBuy(stringPriceBuy);
                System.out.println("Nhập giá tiền thuê mới");
                String stringPriceHire = scanner.nextLine();
                double priceHire = check.parsePriceBuy(stringPriceHire);
                car.setQuantity(quantity);
                car.setPriceBuy(priceBuy);
                car.setPriceHirePerDay(priceHire);
                System.out.println("Cập nhật thành công");
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean delete(){
        display();
        System.out.println("Nhập vào id xe muốn xoá");
        String stringCarId = new Scanner(System.in).nextLine();
        int carID;
        while(true) {
            try {
                carID = check.carid(stringCarId,carArrayList);
                break;
            } catch (Exception e) {
                System.out.println("ID nhập vào phải là kiểu số, xin hãy nhập lại");
                stringCarId = scanner.nextLine();
                continue;
            }
        }
        for (int i = 0; i < carArrayList.size(); i++){
            if (carArrayList.get(i).getCarID() == carID){
                carArrayList.remove(i);
                System.out.println("Xoá thành công");
                return true;
            }
        }
        return false;
    }

    public void findCarByName(){
        System.out.println("Nhập vào tên xe muốn tìm");
        String nameCar = scanner.nextLine();
        boolean isTrue = false;
        System.out.printf("%s%20s%20s%20s%30s%n","ID","Name","Quantity","Price","Price Hire Per Day");
        for (Car car : carArrayList) {
            if (car.getCarName().contains(nameCar)) {
                System.out.printf("%d%20s%17d%25s%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPriceBuy()),check.withLargeIntegers(car.getPriceHirePerDay()));
                isTrue = true;
            }
        }
        if (!isTrue){
            System.out.println("Không tìm thấy xe");
        }
    }

    public void findCarByPriceBuy(){
        System.out.println("Nhập vào giá xe muốn tìm");
        double priceCar = scanner.nextDouble();
        boolean isTrue = false;
        System.out.printf("%s%20s%20s%20s%30s%n","ID","Name","Quantity","Price Buy","Price Hire Per Day");
        for (Car car : carArrayList) {
            if (car.getPriceBuy() == priceCar) {
                System.out.printf("%d%20s%17d%25s%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPriceBuy()),check.withLargeIntegers(car.getPriceHirePerDay()));
                isTrue = true;
            }
        }
        if (!isTrue){
            System.out.println("Không tìm thấy xe");
        }
    }

    public void sortCarByPriceBuyDescending(){
        Collections.sort(carArrayList, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o2.getPriceBuy() - o1.getPriceBuy() > 0 ? 1 : -1;
            }
        });
        System.out.printf("%s%20s%20s%20s%30s%n","ID","Name","Quantity","Price","Price Hire Per Day");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25s%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPriceBuy()),check.withLargeIntegers(car.getPriceHirePerDay()));
        }
    }

    public void sortCarByPriceBuyAscending(){
        Collections.sort(carArrayList, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getPriceBuy() - o2.getPriceBuy() > 0 ? 1 : -1;
            }
        });
        System.out.printf("%s%20s%20s%20s%30s%n","ID","Name","Quantity","Price","Price Hire Per Day");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25s%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPriceBuy()),check.withLargeIntegers(car.getPriceHirePerDay()));
        }
    }

    public void sortCarByName(){
        Collections.sort(carArrayList, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getCarName().compareTo(o2.getCarName());
            }
        });
        System.out.printf("%s%20s%20s%20s%30s%n","ID","Name","Quantity","Price","Price Hire Per Day");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25s%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPriceBuy()),check.withLargeIntegers(car.getPriceHirePerDay()));
        }
    }

    public void displayCar(){
        System.out.printf("%s%20s%20s%20s%30s%n","ID","Name","Quantity","Price","Price Hire Per Day");
        for (Car car : carArrayList){
            if (car.getQuantity() > 0) {
                System.out.printf("%d%20s%17d%25s%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(), check.withLargeIntegers(car.getPriceBuy()),check.withLargeIntegers(car.getPriceHirePerDay()));
            }
        }
    }

}

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
        carArrayList.add(new Car(1, "Lamborghini", 50, 1000000000));
        carArrayList.add(new Car(2, "Ferrari", 30, 3000000000L));
        carArrayList.add(new Car(3, "Limousine", 100, 700000000));
    }

    @Override
    public void display() {
        
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Price");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(), check.withLargeIntegers(car.getPrice()));
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
        double price = check.parsePrice(stringPriceBuy);
        carArrayList.add(new Car(id,name,quantity,price));
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
                System.out.println("Nhập giá tiền mới");
                String stringPriceBuy = scanner.nextLine();
                double price = check.parsePrice(stringPriceBuy);
                car.setQuantity(quantity);
                car.setPrice(price);
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
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Price");
        for (Car car : carArrayList) {
            if (car.getCarName().contains(nameCar)) {
                System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPrice()));
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
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Price");
        for (Car car : carArrayList) {
            if (car.getPrice() == priceCar) {
                System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPrice()));
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
                return o2.getPrice() - o1.getPrice() > 0 ? 1 : -1;
            }
        });
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Price");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPrice()));
        }
    }

    public void sortCarByPriceBuyAscending(){
        Collections.sort(carArrayList, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getPrice() - o2.getPrice() > 0 ? 1 : -1;
            }
        });
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Price");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPrice()));
        }
    }

    public void sortCarByName(){
        Collections.sort(carArrayList, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getCarName().compareTo(o2.getCarName());
            }
        });
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Price");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPrice()));
        }
    }

    public void displayCar(){
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Price");
        for (Car car : carArrayList){
            if (car.getQuantity() > 0) {
                System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(), check.withLargeIntegers(car.getPrice()));
            }
        }
    }

}

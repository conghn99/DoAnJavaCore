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

    public CarController(ArrayList<Car> carArrayList) {
        this.carArrayList = carArrayList;
    }

    public void addCar(){
        carArrayList.add(new Car(1, "Lamborghini", 50, 1000000000,0,"CarForBuy"));
        carArrayList.add(new Car(2, "Ferrari", 30, 3000000000L,0,"CarForBuy"));
        carArrayList.add(new Car(3, "Limousine", 100, 700000000,0,"CarForBuy"));
        carArrayList.add(new Car(4, "Lamborghini", 100, 0,1000000,"CarForHire"));
        carArrayList.add(new Car(5, "Ferrari", 100, 0,1200000,"CarForHire"));
        carArrayList.add(new Car(6, "Limousine", 100, 0,700000,"CarForHire"));
    }

    @Override
    public void display() {
        System.out.printf("%s%20s%20s%15s%25s%30s%n","ID","Name","Quantity","Status","Buy Price", "Hire Price");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%20s%25s%30s%n",car.getCarID(),car.getCarName(),car.getQuantity(),car.getCarStatus(), check.withLargeIntegers(car.getPriceBuy()), check.withLargeIntegers(car.getPriceHire()));
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
        String name = new Scanner(System.in).nextLine();
        System.out.println("Vui lòng nhập trạng thái xe theo số");
        String carStatus = null;
        System.out.println("1 . Xe để bán");
        System.out.println("2 . Xe cho thuê");
        int a = new Scanner(System.in).nextInt();
        switch (a) {
            case 1:
                carStatus = "CarForBuy";
                break;
            case 2:
                carStatus = "CarForHire";
                break;
        }
        boolean isTrue = true;
        while(isTrue) {
            if (!check.checkname(carArrayList,name,carStatus)){
                System.out.println("Xe đã tồn tại. Vui lòng nhập lại");
                name = new Scanner(System.in).nextLine();
                System.out.println("Vui lòng nhập trạng thái xe theo số");
                System.out.println("1 . Xe để bán");
                System.out.println("2 . Xe cho thuê");
                a = new Scanner(System.in).nextInt();
                switch (a) {
                    case 1:
                        carStatus = "CarForBuy";
                        break;
                    case 2:
                        carStatus = "CarForHire";
                        break;
                }
            }
            else {
                isTrue = false;
            }
        }
        System.out.println("Vui lòng nhập số lượng");
        String stringQuantity = new Scanner(System.in).nextLine();
        int quantity = check.parseQuantity(stringQuantity);
        double priceBuy = 0;
        double priceHire = 0;
        if (carStatus.equals("CarForBuy")) {
            System.out.println("Vui lòng nhập giá xe cho mua");
            String stringPriceBuy = new Scanner(System.in).nextLine();
            priceBuy = check.parsePrice(stringPriceBuy);
        } else if (carStatus.equals("CarForHire")) {
            System.out.println("Vui lòng nhập giá xe cho thuê");
            priceHire = new Scanner(System.in).nextDouble();
        }
        carArrayList.add(new Car(id,name,quantity,priceBuy,priceHire,carStatus));
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
                stringIdCar = new Scanner(System.in).nextLine();
                continue;
            }
        }
        for (Car car : carArrayList) {
            if (car.getCarID() == carID) {
                System.out.println("Nhập số lượng mới");
                String stringQuantity = new Scanner(System.in).nextLine();
                int quantity = check.parseQuantity(stringQuantity);
                String carStatus = car.getCarStatus();
                double priceBuy = 0;
                double priceHire = 0;
                if (carStatus.equals("CarForBuy")) {
                    System.out.println("Vui lòng nhập giá xe cho mua");
                    String stringPriceBuy = new Scanner(System.in).nextLine();
                    priceBuy = check.parsePrice(stringPriceBuy);
                } else if (carStatus.equals("CarForHire")) {
                    System.out.println("Vui lòng nhập giá xe cho thuê");
                    priceHire = new Scanner(System.in).nextDouble();
                }
                car.setQuantity(quantity);
                car.setPriceBuy(priceBuy);
                car.setPriceHire(priceHire);
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
                stringCarId = new Scanner(System.in).nextLine();
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
        String nameCar = new Scanner(System.in).nextLine();
        boolean isTrue = false;
        System.out.printf("%s%20s%20s%15s%25s%30s%n","ID","Name","Quantity","Status","Buy Price", "Hire Price");
        for (Car car : carArrayList) {
            if (car.getCarName().contains(nameCar)) {
                System.out.printf("%d%20s%17d%20s%25s%30s%n",car.getCarID(),car.getCarName(),car.getQuantity(),car.getCarStatus(), check.withLargeIntegers(car.getPriceBuy()), check.withLargeIntegers(car.getPriceHire()));
                isTrue = true;
            }
        }
        if (!isTrue){
            System.out.println("Không tìm thấy xe");
        }
    }

    public void findCarByPriceBuy(){
        System.out.println("Nhập vào giá mua xe muốn tìm");
        double priceCar = new Scanner(System.in).nextDouble();
        boolean isTrue = false;
        System.out.printf("%s%20s%20s%20s%30s%n","ID","Name","Quantity","Buy Price","Hire Price");
        for (Car car : carArrayList) {
            if (car.getPriceBuy() == priceCar && car.getCarStatus().equals("CarForBuy")) {
                System.out.printf("%d%20s%17d%25s%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPriceBuy()));
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
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Buy Price");
        for (Car car : carArrayList) {
            if (car.getCarStatus().equals("CarForBuy")) {
                System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPriceBuy()));
            }
        }
    }

    public void sortCarByPriceBuyAscending(){
        Collections.sort(carArrayList, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getPriceBuy() - o2.getPriceBuy() > 0 ? 1 : -1;
            }
        });
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Buy Price");
        for (Car car : carArrayList) {
            if (car.getCarStatus().equals("CarForBuy")) {
                System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(),check.withLargeIntegers(car.getPriceBuy()));
            }
        }
    }

    public void sortCarByName(){
        Collections.sort(carArrayList, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getCarName().compareTo(o2.getCarName());
            }
        });
        System.out.printf("%s%20s%20s%20s%30s%n","ID","Name","Quantity","Buy Price","Hire Price");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%20s%25s%30s%n",car.getCarID(),car.getCarName(),car.getQuantity(),car.getCarStatus(), check.withLargeIntegers(car.getPriceBuy()), check.withLargeIntegers(car.getPriceHire()));
        }
    }

    public void displayCarBuy(){
        System.out.printf("%s%20s%20s%20s%20s%n","ID","Name","Car Status","Quantity","Buy Price");
        for (Car car : carArrayList){
            if (car.getQuantity() > 0 && car.carStatus.equals("CarForBuy")) {
                System.out.printf("%d%20s%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getCarStatus(),car.getQuantity(), check.withLargeIntegers(car.getPriceBuy()));
            }
        }
    }

    public void displayCarHire(){
        System.out.printf("%s%20s%20s%20s%20s%n","ID","Name","Car Status","Quantity","Hire Price");
        for (Car car : carArrayList){
            if (car.getQuantity() > 0 && car.carStatus.equals("CarForHire")) {
                System.out.printf("%d%20s%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getCarStatus(),car.getQuantity(), check.withLargeIntegers(car.getPriceHire()));
            }
        }
    }

}

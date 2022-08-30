package model;

public class Car{
    private int carID;
    private String carName;
    private int quantity;
    private double price;

    public Car(int carID, String carName, int quantity, double price) {
        this.carID = carID;
        this.carName = carName;
        this.quantity = quantity;
        this.price = price;
    }


    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carID=" + carID +
                ", carName='" + carName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

}


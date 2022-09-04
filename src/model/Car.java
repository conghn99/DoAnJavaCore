package model;

public class Car{
    private int carID;
    private String carName;
    private int quantity;
    private double priceBuy;
    private double priceHire;
    public String carStatus;

    public Car(int carID, String carName, int quantity, double priceBuy, double priceHire, String carStatus) {
        this.carID = carID;
        this.carName = carName;
        this.quantity = quantity;
        this.priceBuy = priceBuy;
        this.priceHire = priceHire;
        this.carStatus = carStatus;
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

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public double getPriceHire() {
        return priceHire;
    }

    public void setPriceHire(double priceHire) {
        this.priceHire = priceHire;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carID=" + carID +
                ", carName='" + carName + '\'' +
                ", quantity=" + quantity +
                ", priceBuy=" + priceBuy +
                '}';
    }

}


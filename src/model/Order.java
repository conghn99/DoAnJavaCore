package model;

public class Order{
    private int oderID;
    private int carID;
    private int customerID;
    private int quantity;
    private int day;
    private int month;
    private int year;
    private double total;
    public Status status;
    public enum Status {
        ORDER,PAID,CANCEL
    }


    public Order(int oderID, int carID, int customerID, int quantity, int day, int month, int year, double total, Status status) {
        this.oderID = oderID;
        this.carID = carID;
        this.customerID = customerID;
        this.quantity = quantity;
        this.day = day;
        this.month = month;
        this.year = year;
        this.total = total;
        this.status = status;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Order() {
    }

    public int getOderID() {
        return oderID;
    }

    public void setOderID(int oderID) {
        this.oderID = oderID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oderID=" + oderID +
                ", carID=" + carID +
                ", agencyID=" + customerID +
                ", quantity=" + quantity +
                ", day=" + day +
                ", month=" + month +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}

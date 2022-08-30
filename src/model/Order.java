package model;

public class Order{
    private int oderID;
    private int carID;
    private int agencyID;
    private int quantity;
    private int day;
    private int month;
    private double total;
    public Status status;
    public enum Status {
        ORDER,PAID,CANCEL
    }


    public Order(int oderID, int carID, int agencyID, int quantity, int day, int month, double total, Status status) {
        this.oderID = oderID;
        this.carID = carID;
        this.agencyID = agencyID;
        this.quantity = quantity;
        this.day = day;
        this.month = month;
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

    public int getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(int agencyID) {
        this.agencyID = agencyID;
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
                ", agencyID=" + agencyID +
                ", quantity=" + quantity +
                ", day=" + day +
                ", month=" + month +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}

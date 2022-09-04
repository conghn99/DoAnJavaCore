package model;

public class Order{
    private int oderID;
    private int carID;
    private String carStatus;
    private int customerID;
    private int quantity;
    private int dayBuy;
    private int monthBuy;
    private int yearBuy;
    private int dayStartHire;
    private int monthStartHire;
    private int yearStartHire;
    private int dayEndHire;
    private int monthEndHire;
    private int yearEndHire;
    private double total;
    public Status orderStatus;
    public enum Status {
        ORDER,PAID,CANCEL
    }

    public Order(int oderID, int carID, String carStatus, int customerID, int quantity, int dayStartHire, int monthStartHire, int yearStartHire, int dayEndHire, int monthEndHire, int yearEndHire, double total, Status orderStatus) {
        this.oderID = oderID;
        this.carID = carID;
        this.carStatus = carStatus;
        this.customerID = customerID;
        this.quantity = quantity;
        this.dayStartHire = dayStartHire;
        this.monthStartHire = monthStartHire;
        this.yearStartHire = yearStartHire;
        this.dayEndHire = dayEndHire;
        this.monthEndHire = monthEndHire;
        this.yearEndHire = yearEndHire;
        this.total = total;
        this.orderStatus = orderStatus;
    }

    public Order(int oderID, int carID, String carStatus, int customerID, int quantity, int dayBuy, int monthBuy, int yearBuy, double total, Status orderStatus) {
        this.oderID = oderID;
        this.carID = carID;
        this.carStatus = carStatus;
        this.customerID = customerID;
        this.quantity = quantity;
        this.dayBuy = dayBuy;
        this.monthBuy = monthBuy;
        this.yearBuy = yearBuy;
        this.total = total;
        this.orderStatus = orderStatus;
    }

    public int getDayBuy() {
        return dayBuy;
    }

    public void setDayBuy(int dayBuy) {
        this.dayBuy = dayBuy;
    }

    public int getMonthBuy() {
        return monthBuy;
    }

    public void setMonthBuy(int monthBuy) {
        this.monthBuy = monthBuy;
    }

    public int getYearBuy() {
        return yearBuy;
    }

    public void setYearBuy(int yearBuy) {
        this.yearBuy = yearBuy;
    }

    public int getDayStartHire() {
        return dayStartHire;
    }

    public void setDayStartHire(int dayStartHire) {
        this.dayStartHire = dayStartHire;
    }
    
    public int getMonthStartHire() {
        return monthStartHire;
    }

    public void setMonthStartHire(int monthStartHire) {
        this.monthStartHire = monthStartHire;
    }
    
    public int getYearStartHire() {
        return yearStartHire;
    }

    public void setYearStartHire(int yearStartHire) {
        this.yearStartHire = yearStartHire;
    }

    public int getDayEndHire() {
        return dayEndHire;
    }

    public void setYearEndHire(int yearEndHire) {
        this.yearEndHire = yearEndHire;
    }

    public int getMonthEndHire() {
        return monthEndHire;
    }

    public void setMonthEndHire(int monthEndHire) {
        this.monthEndHire = monthEndHire;
    }

    public void setDayEndHire(int dayEndHire) {
        this.dayEndHire = dayEndHire;
    }

    public int getYearEndHire() {
        return yearEndHire;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oderID=" + oderID +
                ", carID=" + carID +
                ", agencyID=" + customerID +
                ", quantity=" + quantity +
                ", total=" + total +
                ", status=" + orderStatus +
                '}';
    }
}

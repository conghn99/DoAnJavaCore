package model;

public class Customer extends Person {
    private String address;
    private String phone;

    public Customer(){

    }
    public Customer(int id, String name, String birthDay, String address, String phone) {
        super(id, name, birthDay);
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "customerID=" + getID() +
                ", customerName='" + getName() + '\'' +
                ", customerAddress='" + address + '\'' +
                ", customerPhone='" + phone + '\'' +
                '}';
    }
}

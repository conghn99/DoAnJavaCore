package model;

public class Admin extends Account {
    private String email;

    public Admin() {

    }

    public Admin(int ID, String name, String birthDay, String userName, String passWord, Role role, String email) {
        super(ID, name, birthDay, userName, passWord, role);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

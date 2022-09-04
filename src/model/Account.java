package model;

public class Account extends Person {
    private String userName;
    private String passWord;
    public Role role;
    public enum Role {
        MANAGER, EMPLOYEE
    }

    public Account() {
    }

    public Account(int ID, String name, String birthDay, String userName, String passWord, Role role) {
        super(ID, name, birthDay);
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

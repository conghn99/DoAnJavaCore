package model;

public class Account extends Person {
    private String userName;
    private String passWord;
    private int role;

    public Account() {
    }

    public Account(int ID, String name, String birthDay, String userName, String passWord, int role) {
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}

package controller;

import action.CRUDaction;
import model.Account;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountController implements CRUDaction {
    Scanner scanner = new Scanner(System.in);

    ArrayList<Account> manages = new ArrayList<>();
    String userNameInput = null;
    String passWordInput = null;
    public AccountController(ArrayList<Account> manages) {
        this.manages = manages;
    }

    public void addAccount(){
        manages.add(new Account(1,"Tring Quang Dung","13/12/1996","dung","1234",1));
        manages.add(new Account(2,"Nguyen Viet Long","13/5/2002","long","1234",1));
        manages.add(new Account(3,"Do Thanh Cong","13/11/1999","cong","1234",1));
        manages.add(new Account(1,"Dat","13/12/1998","dat","1234", 2));
    }

    public int checkLogin(){
        System.out.println("Nhập vào tài khoản");
        userNameInput = scanner.nextLine();
        System.out.println("Nhập vào mật khẩu");
        passWordInput = scanner.nextLine();
        for (Account manage : manages){
            if (manage.getUserName().equals(userNameInput) && manage.getPassWord().equals(passWordInput) && manage.getRole() == 1){
                return 1;
            } else if (manage.getUserName().equals(userNameInput) && manage.getPassWord().equals(passWordInput) && manage.getRole() == 2) {
                return 2;
            }
        }
        return 3;
    }

    @Override
    public void display() {
        System.out.printf("%s%20s%20s%20s%20s%20s%n","ID","Name","Age","BirthDay","UserName","Password");
        for (Account manage : manages) {
            if(manage.getRole() == 1) {
                System.out.printf("%d%20s%20d%20s%17s%20s%n",manage.getID(),manage.getName(),manage.getBirthDay(),manage.getUserName(),manage.getPassWord());
            } 
        }
    }

    @Override
    public void input() {
        int id;
        if (manages.size() == 0){
            id = 1;
        }else {
            id = (manages.get(manages.size() - 1)).getID() + 1;
        }
        System.out.println("Nhập vào tên nhân viên");
        String name = scanner.nextLine();
        System.out.println("Nhập vào ngày sinh nhân viên");
        String birthDay = scanner.nextLine();
        System.out.println("Nhập vào tên đăng nhập");
        String userName = scanner.nextLine();
        System.out.println("Nhập vào mật khẩu");
        String passWord = scanner.nextLine();
        manages.add(new Account(id,name,birthDay,userName,passWord,1));
    }

    @Override
    public boolean update() {
        System.out.println("Nhập id nhân viên muốn sửa");
        int idEmployee = scanner.nextInt();
        for (Account manage : manages) {
            if (manage.getID() == idEmployee && manage.getRole() == 1) {
                System.out.println("Nhập vào ngày sinh nhân viên mới");
                String birthDay = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Nhập vào tên đăng nhập mới");
                String userName = scanner.nextLine();
                System.out.println("Nhập vào mật khẩu mới");
                String passWord = scanner.nextLine();
                manage.setBirthDay(birthDay);
                manage.setUserName(userName);
                manage.setPassWord(passWord);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete() {
        System.out.println("Nhập vào id quản lý muốn xoá");
        int idEmployee = scanner.nextInt();
        for (int i = 0; i < manages.size(); i++){
            if (manages.get(i).getID() == idEmployee && manages.get(i).getRole() == 1){
                manages.remove(i);
                return true;
            }
        }
        return false;
    }
}

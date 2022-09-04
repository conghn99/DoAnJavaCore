package controller;

import action.CRUDaction;
import model.Account;
import model.Admin;
import model.Account.Role;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountController implements CRUDaction {
    Scanner scanner = new Scanner(System.in);
    Check check = new Check();

    ArrayList<Account> manages = new ArrayList<>();
    String userNameInput = null;
    String passWordInput = null;
    public AccountController(ArrayList<Account> manages) {
        this.manages = manages;
    }

    public void addAccount(){
        manages.add(new Admin(1,"Dat","13/12/1998","dat","1234", Role.MANAGER, "Dat@gmail.com"));
        manages.add(new Account(1,"Tring Quang Dung","13/12/1996","dung","1234",Role.EMPLOYEE));
        manages.add(new Account(2,"Nguyen Viet Long","13/5/2002","long","1234",Role.EMPLOYEE));
        manages.add(new Account(3,"Do Thanh Cong","13/11/1999","cong","1234",Role.EMPLOYEE));
    }

    public int checkLogin(){
        System.out.println("Nhập vào tài khoản");
        userNameInput = scanner.nextLine();
        System.out.println("Nhập vào mật khẩu");
        passWordInput = scanner.nextLine();
        for (Account manage : manages){
            if (manage.getUserName().equals(userNameInput) && manage.getPassWord().equals(passWordInput) && manage.getRole() == Role.EMPLOYEE){
                return 1;
            } else if (manage.getUserName().equals(userNameInput) && manage.getPassWord().equals(passWordInput) && manage.getRole() == Role.MANAGER) {
                return 2;
            }
        }
        return 3;
    }

    @Override
    public void display() {
        System.out.printf("%s%20s%20s%20s%20s%n","ID","Name","BirthDay","UserName","Password");
        for (Account manage : manages) {
            if(manage.getRole() == Role.EMPLOYEE) {
                System.out.printf("%d%20s%20s%17s%20s%n",manage.getID(),manage.getName(),manage.getBirthDay(),manage.getUserName(),manage.getPassWord());
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
        String stringUserName = scanner.nextLine();
        String userName = check.userNameCheck(stringUserName, manages);
        System.out.println("Nhập vào mật khẩu");
        String stringPassword = scanner.nextLine();
        String password = check.passwordCheck(stringPassword);
        manages.add(new Account(id,name,birthDay,userName,password,Role.EMPLOYEE));
    }

    @Override
    public boolean update() {
        display();
        System.out.println("Nhập vào ID nhân viên muốn sửa");
        String stringEmployeeId = new Scanner(System.in).nextLine();
        int employeeId;
        while(true) {
            try {
                employeeId = check.employeeid(stringEmployeeId, manages);
                break;
            } catch (Exception e) {
                System.out.println("ID nhập vào phải là kiểu số, xin hãy nhập lại");
                stringEmployeeId = scanner.nextLine();
                continue;
            }
        }
        for (Account manage : manages) {
            if (manage.getID() == employeeId && manage.getRole() == Role.EMPLOYEE) {
                System.out.println("Nhập vào ngày sinh nhân viên mới");
                String birthDay = scanner.nextLine();
                System.out.println("Nhập vào tên đăng nhập mới");
                String stringUserName = scanner.nextLine();
                String userName = check.userNameCheck(stringUserName, manages);
                System.out.println("Nhập vào mật khẩu mới");
                String stringPassword = scanner.nextLine();
                String password = check.passwordCheck(stringPassword);
                manage.setBirthDay(birthDay);
                manage.setUserName(userName);
                manage.setPassWord(password);
                System.out.println("Cập nhật thành công");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete() {
        System.out.println("Nhập vào id nhân viên muốn xoá");
        String stringEmployeeId = new Scanner(System.in).nextLine();
        int employeeId;
        while(true) {
            try {
                employeeId = check.employeeid(stringEmployeeId, manages);
                break;
            } catch (Exception e) {
                System.out.println("ID nhập vào phải là kiểu số, xin hãy nhập lại");
                stringEmployeeId = scanner.nextLine();
                continue;
            }
        }
        for (int i = 0; i < manages.size(); i++){
            if (manages.get(i).getID() == employeeId && manages.get(i).getRole() == Role.EMPLOYEE){
                manages.remove(i);
                System.out.println("Xoá thành công");
                return true;
            }
        }
        return false;
    }
}

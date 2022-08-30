package model;

public class Person {
    private int id;
    private String name;
    private String birthDay;

    public Person() {
    }

    public Person(int id, String name, String birthDay) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}

package com.example.luoyangcomputer.newbiji;

public class BiJiNumber {
    private int id;
    private String number;

    public BiJiNumber(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BiJiNumber{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}

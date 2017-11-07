package com.hanyong.hbase.test.entity;

/**
 * Created by Administrator on 2017/10/12.
 */
public class Person {
    private String name;
    private int age;
    private String work;

    public Person() {
    }

    public Person(String name, int age, String work) {
        this.name = name;
        this.age = age;
        this.work = work;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", work='" + work + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}

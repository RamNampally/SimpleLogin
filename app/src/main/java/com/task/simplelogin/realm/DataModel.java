package com.task.simplelogin.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private String age;
    private String city;

    public DataModel(){

    }

    public DataModel(int id,String name,String age,String city){
        this.id = id;
        this.name = name;
        this.age= age;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

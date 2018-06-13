package com.ideacrest.graphql.types;

import java.util.List;
import java.util.Map;

public class User {
    private  String id;
    private  String name;
    private  String email;
    private  String password;
    private List<Address> addressList;

    public User(String name, String email, String password,List<Address> addresses){
        this(null,name,email,password,addresses);
    }

    public User(String id, String name, String email, String password,List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.addressList = addresses;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}

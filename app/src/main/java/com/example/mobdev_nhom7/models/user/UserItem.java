package com.example.mobdev_nhom7.models.user;

public class UserItem {
    private String name;
    private String uid;
    private String email;
    private String phone;
    private String provider;
    private String age;
    private String address;

    public UserItem(String name,
                    String uid,
                    String email,
                    String phone,
                    String provider,
                    String age,
                    String address) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.phone = phone;
        this.provider = provider;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvider() {
        return provider;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}

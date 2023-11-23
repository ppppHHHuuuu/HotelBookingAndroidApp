package com.example.mobdev_nhom7.models.user;

public class UserItem {
    private String name;
    private String user_id;
    private String email;
    private String phone;
    private String provider;
    private String age;
    private String address;

    public UserItem(String name,
                    String user_id,
                    String email,
                    String phone,
                    String provider,
                    String age,
                    String address) {
        this.name = name;
        this.user_id = user_id;
        this.email = email;
        this.phone = phone;
        this.provider = provider;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getuser_id() {
        return user_id;
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

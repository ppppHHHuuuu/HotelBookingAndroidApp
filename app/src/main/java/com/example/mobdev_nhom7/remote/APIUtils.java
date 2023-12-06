package com.example.mobdev_nhom7.remote;


public class APIUtils {
    public static APIService getUserService(){
        return RequestClient.getClient().create(APIService.class);
    }

    public static APIService getTestService(){
        return RequestClient.getTestClient().create(APIService.class);
    }
}

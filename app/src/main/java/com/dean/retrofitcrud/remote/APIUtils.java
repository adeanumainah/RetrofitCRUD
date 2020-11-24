package com.dean.retrofitcrud.remote;

public class APIUtils {

    private APIUtils(){

    }

    public static final String API_URL =
            "http://192.168.100.26/marketplace/index.php/";
    public static ProductServices getProductServices(){
        return RetrofitClient.getClient(API_URL).create(ProductServices.class);
    }
}

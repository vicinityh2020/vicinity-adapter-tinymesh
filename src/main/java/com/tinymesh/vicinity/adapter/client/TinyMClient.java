package com.tinymesh.vicinity.adapter.client;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class TinyMClient {

    public static void main(String[] args) throws Exception{
        String secret = "fhklsjhdfkhf4737y4hchj43y";
        String message = "hello world";

        Mac HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        HMAC.init(secretKey);

        byte [] encoded_msg = HMAC.doFinal(message.getBytes("UTF-8"));
        System.out.println(Arrays.toString(encoded_msg));




    }

}

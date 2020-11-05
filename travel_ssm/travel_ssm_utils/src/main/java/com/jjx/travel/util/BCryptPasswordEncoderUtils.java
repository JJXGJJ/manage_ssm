package com.jjx.travel.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author JJ
 * @Date 2020/10/31 17:27
 * @Version 1.0
 * @Describe
 */
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //进行密码加密，用spring_Security
    public static String encodePassword(String password){
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String jjx = encoder.encode("jjx");
        System.out.println(jjx);
    }

}

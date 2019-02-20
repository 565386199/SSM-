package comitheima.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class BCryptPasswordEncoders {
    public static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
       return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String a ="123";
        String s = encodePassword(a);
        System.out.println(s);
    }
}

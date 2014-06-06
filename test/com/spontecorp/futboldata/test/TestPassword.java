package com.spontecorp.futboldata.test;

import com.spontecorp.futboldata.utilities.SecurePassword;

/**
 *
 * @author Casper
 */
public class TestPassword {
    public static void main(String[] args) {
        String pass = "123456";
        char[] password = pass.toCharArray();
        String psw = SecurePassword.encript(password);
        System.out.println("EL password encriptado es: " + psw);
    }
}

/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.utilities;

import com.spontecorp.futboldata.entity.User;
import com.spontecorp.futboldata.jpacontroller.extensions.UserJpaExt;
import java.security.MessageDigest;
import javax.persistence.NoResultException;

/**
 *
 * @author jgcastillo
 */
public class SecurePassword {
    
    public static String encript(char[] password) {
        String algorithm = "SHA-256";
        MessageDigest md = null;

        // Se obtienen los bytes que componen el password leido
        byte[] bytes = new byte[password.length * 2];
        for (int i = 0; i < password.length; i++) {
            bytes[i * 2] = (byte) (password[i] >> 8);
            bytes[i * 2 + 1] = (byte) password[i];
        }
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        md.reset();
        md.update(bytes);
        byte[] encodedPassword = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                sb.append("0");
            }

            sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }
        return sb.toString();
    }
    
    public static String encript(String password) {        
        return encript(password.toCharArray());
    }
    
    public static User authenticate(String username, char[] password) {
        String pswEncripted = SecurePassword.encript(password);
        UserJpaExt service = new UserJpaExt(Util.getEmf());
        try {
            User usuario = service.findUsuario(username);
            if (!pswEncripted.equals(usuario.getPassword())) {
                return null;
            } else {
                if(usuario.getStatus() == Constantes.INACTIVO){
                    return null;
                } else {
                    return usuario;
                }
            }
        } catch (NoResultException e) {
            return null;
        }
    }
}

import java.io.*;
import javax.crypto.*;
import java.security.*;
import java.util.Arrays;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public class Probando {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        /*KeyGenerator genera = KeyGenerator.getInstance("AES");
        genera.init(128);

        Key clave = genera.generateKey();
        byte[] prueba = clave.getEncoded();
        System.out.println("Tamaño = " + prueba.length);
        System.out.println("Clave = " + clave);*/

        char[] chars = "QWERTYUIOPLKJHGFDSAZXCVBNMabcdefghijklmnopqrstuvwxyz1234567890!\"#$%&'()*+,-./:;<=>?@[]^_`{}|~".toCharArray();
        StringBuilder sb = new StringBuilder(16);
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);

        byte[] b = output.getBytes();
        System.out.println(b.length);
    }
}
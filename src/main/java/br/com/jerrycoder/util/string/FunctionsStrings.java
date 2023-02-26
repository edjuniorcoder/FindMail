/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.util.string;

import java.text.ParseException;
import java.util.Random;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author jerry
 */
public class FunctionsStrings {

    public FunctionsStrings() {
    }

    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }

    public static int randNumb(int min, int max) {
        // create random object
        Random rdn = new Random();

        return rdn.nextInt((max - min) + 1) + min;
    }

    public static String getString(String texto, String inicio, String fim) {

        String response = texto.substring(texto.indexOf(inicio), texto.lastIndexOf(fim));
        response = response.replace(inicio, "");

        return response;
    }

    public static String maskCpf(String cpf) throws ParseException {

        MaskFormatter mf = new MaskFormatter("###.###.###-##");
        mf.setValueContainsLiteralCharacters(false);

        return mf.valueToString(cpf);

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import modeles.musique.Artiste;
import modeles.musique.Morceau;
import modeles.musique.Piste;

public class Parser {

    public static void main(String[] arg) throws FileNotFoundException, IOException {
        Parser pars = new Parser();
        pars.parse(new FileInputStream(".\\web\\resources\\data\\liste.txt"));
    }

    public void parse(InputStream file) throws IOException {
        InputStreamReader lecture = new InputStreamReader(file);
        BufferedReader buff = new BufferedReader(lecture);
        String ligne;

        Artiste a;
        Morceau m = new Morceau();
        Piste p;

        Set<Piste> pistes;
        pistes = new HashSet();

        Pattern tiretPattern = Pattern.compile("(\\s-\\s)");

        while ((ligne = buff.readLine()) != null) {

            if (!Pattern.matches("^.*\\.[0-9a-z]+$", ligne) && !Pattern.matches("^\\s*", ligne)) {

                String[] items = tiretPattern.split(ligne);
                a = new Artiste(items[0]);
                m = new Morceau(items[1].replace(":", ""), "2001", 1.80, a);

                m.setPistes(pistes);
                System.out.println(a.toString());
                System.out.println(m.toString());

                System.out.println(m.getPistes().toString());
                pistes = new HashSet();

            } else if (!Pattern.matches("^\\s*", ligne) && !ligne.contains("mogg") && !ligne.contains("png")) {
                p = new Piste(ligne, 3, m);
                System.out.println(p.getNom());
                pistes.add(p);

            }
        }

    }
}

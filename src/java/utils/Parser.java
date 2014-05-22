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
import java.util.regex.Pattern;
import modeles.musique.Artiste;
import modeles.musique.Morceau;

public class Parser {

    public static void main(String[] arg) throws FileNotFoundException, IOException {
        Parser p = new Parser();
        p.parse(new FileInputStream("C:\\Users\\Bastien\\Documents\\NetBeansProjects\\SpotProject\\web\\resources\\data\\liste.txt"));
    }

    public void parse(InputStream file) throws IOException {
        InputStreamReader lecture = new InputStreamReader(file);
        BufferedReader buff = new BufferedReader(lecture);
        String ligne;

        Pattern tiretPattern = Pattern.compile("(\\s-\\s)");

        while ((ligne = buff.readLine()) != null) {
            Artiste a;
            Morceau m;
            if (!Pattern.matches("^.*\\.[0-9a-z]+$", ligne) && !Pattern.matches("^\\s*", ligne)) {

                String[] items = tiretPattern.split(ligne);

                System.out.println("artiste :   " + items[0]);
                System.out.println("morceau :   " + items[1]);

            } else if (!Pattern.matches("^\\s*", ligne)) {
                System.out.println("piste :   " + ligne);
            }
        }
    }

//    public void parseMoi(GestionnaireMusiques gm) {
//        String titre;
//        Set<Morceau> Morceaux;
//        Set<Piste> pistes;
//        String nomInstru[];
//        boolean ifTitle = true;
//        pistes = new HashSet<>();
//        titre = new String();
//        pistes = new HashSet<>();
//
//        try {
//            InputStream data = new FileInputStream("C:\\Users\\Bastien\\Documents\\NetBeansProjects\\SpotProject\\web\\resources\\data\\liste.txt");
//            InputStreamReader lecture = new InputStreamReader(data);
//            BufferedReader buff = new BufferedReader(lecture);
//            String ligne;
//
//            while ((ligne = buff.readLine()) != null) {
//
//                if (!ligne.contains("mogg")) {
//                    if (ifTitle == true && !ligne.isEmpty()) {
//                        titre = ligne;
//                        ifTitle = false;
//                    } else if (!ligne.isEmpty() && ifTitle == false) {
//
//                        nomInstru = ligne.split("\\s+");
//                        if (!instrument.contains(new Instrument(nomInstru[0])) && !ligne.contains("nfo") && !ligne.contains("png")) {
//
//                            if (!nomInstru[0].matches("\\d+")) {
//                                if (nomInstru[0].contains(".mp3")) {
//                                    instrument.add(new Instrument(nomInstru[0].split(".mp3")[0]));
//                                } else {
//                                    instrument.add(new Instrument(nomInstru[0].split(".ogg")[0]));
//
//                                }
//                            } else if (!instrument.contains(new Instrument(nomInstru[1]))) {
//                                if (nomInstru[0].contains(".mp3")) {
//                                    instrument.add(new Instrument(nomInstru[1].split(".mp3")[0]));
//                                } else {
//                                    instrument.add(new Instrument(nomInstru[1].split(".ogg")[0]));
//
//                                }
//                            }
//                        }
//                        Morceaux.add(new Piste(ligne));
//
//                    } else if (ligne.isEmpty()) {
//                        g2.creerMorceau(titre, Morceaux, instrument);
//                        System.out.println(titre);
//                        for (int i = 0; i < Morceaux.size(); i++) {
//                            System.out.println(Morceaux.get(i));
//
//                        }
//                        for (int i = 0; i < instrument.size(); i++) {
//                            System.out.println(instrument.get(i));
//
//                        }
//                        System.out.println();
//
//                        ifTitle = true;
//                        instrument = new ArrayList<>();
//                        titre = new String();
//                        Morceaux = new ArrayList();
//
//                    }
//                }
//            }
//            buff.close();
//
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }
//
//    }
}

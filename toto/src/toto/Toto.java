package toto;

import java.io.IOException;
import java.util.Scanner;
import toto.totopackage.Fordulok;

public class Toto {

    public static void main(String[] args) throws IOException {
        Fordulok f = new Fordulok("toto.csv");
        System.out.println(f);
        System.out.println("");
        System.out.println("A legnagyobb nyeremény amit rögzítettek: " + f.LegnagyobbNyeremeny() + " Ft");
        System.out.println("Statisztika: #1 csapat nyert: " + Math.round(f.ElsoCsapat()*100)/100.0 + "% #2 csapat nyert: " + Math.round(f.MasodikCsapat()*100)/100.0 + "% döntetlen: " + Math.round(f.Dontetlen()*100)/100.0 + "%");
        Scanner sc = new Scanner(System.in);
        System.out.print("Kérem, adjon meg egy dátumot: ");
        String date = sc.nextLine();
        System.out.print("Kérem, adjon meg egy tippet: ");
        String tipp = sc.nextLine();
        System.out.println(f.Tipp(date, tipp));
    }
    
}

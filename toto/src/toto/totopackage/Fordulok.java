package toto.totopackage;

import toto.totopackage.Talalat;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fordulok {
    private List<Fordulo> fordulok;

    public Fordulok(String filename) throws IOException {
        BufferedReader br = null; 
        try {
            fordulok = new ArrayList(); 
            br = new BufferedReader(new FileReader(filename));
            
            String sor = br.readLine();
            while(sor != null){
                String[] adatok = sor.split(";");
                
                int ev = Integer.parseInt(adatok[0]);
                int het = Integer.parseInt(adatok[1]);
                
                if(adatok[2].equals("")){
                    adatok[2] = "1";
                }
                
                int fordulo = Integer.parseInt(adatok[2]);
                
                Date datum = new Date();
                Calendar cal = Calendar.getInstance();
                if(adatok[3].equals("")){
                    cal.set(Calendar.YEAR, ev);
                    cal.set(Calendar.WEEK_OF_YEAR, het);
                    switch(adatok[2]){
                        case "1":
                            cal.set(Calendar.DAY_OF_WEEK, 1);
                            break;
                        case "2":
                            cal.set(Calendar.DAY_OF_WEEK, 2);
                            break;
                        case "3":
                            cal.set(Calendar.DAY_OF_WEEK, 3);
                            break;
                        case "4":
                            cal.set(Calendar.DAY_OF_WEEK, 4);
                            break;
                        case "5":
                            cal.set(Calendar.DAY_OF_WEEK, 5);
                            break;
                        case "6":
                            cal.set(Calendar.DAY_OF_WEEK, 6);
                            break;
                        case "7":
                            cal.set(Calendar.DAY_OF_WEEK, 7);
                            break;
                    }
                }
                else{
                    cal.set(Calendar.YEAR,Integer.parseInt(adatok[3].split("\\.")[0]));
                    cal.set(Calendar.MONTH,Integer.parseInt(adatok[3].split("\\.")[1])-1);
                    cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(adatok[3].split("\\.")[2]));
                }
                
                datum = cal.getTime();
                
                List<Talalat> talalatok = new ArrayList();
                for(int i = 4; i < 14; i+=2){
                    talalatok.add(new Talalat(Integer.parseInt(adatok[i]),Integer.parseInt(adatok[i+1].replace("Ft", "").replaceAll("\\s+",""))));
                }
                
                List<Type> tipus = new ArrayList();
                for(int i = 14; i<adatok.length; i++){
                    switch(adatok[i]){
                        case "1":
                            tipus.add(Type._1);
                            break;
                        case "2":
                            tipus.add(Type._2);
                            break;
                        case "X":
                            tipus.add(Type.x);
                            break;
                    }
                }
                if(adatok[adatok.length-1].contains("+")){
                    String last = adatok[adatok.length-1];
                    switch(last){
                        case "+1":
                            tipus.add(Type._1);
                            break;
                        case "+2":
                            tipus.add(Type._2);
                            break;
                        case "+X":
                            tipus.add(Type.x);
                            break;
                    }
                }
                
                fordulok.add(new Fordulo(ev,het,fordulo,datum,talalatok,tipus));
                sor = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fordulok.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Fordulok.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public int LegnagyobbNyeremeny(){
        int max = 0;
        for (Fordulo f : fordulok) {
            List<Talalat> talalatok = f.getTalalatok();
            for (Talalat t : talalatok) {
                if(t.getNyeremeny() > max){
                    max = t.getNyeremeny();
                }
            }
        }
        return max;
    }
    
    public double ElsoCsapat(){
        int osszes = 0;
        int elsoDb = 0;
        for (Fordulo f : fordulok) {
            List<Type> tipus = f.getTipus();
            osszes += tipus.size();
            for (Type t : tipus) {
                if(t.equals(Type._1)){
                    elsoDb++;
                }
            }
        }
        return (elsoDb*1.0/osszes)*100;
    }
    
    public double MasodikCsapat(){
        int osszes = 0;
        int masodikDb = 0;
        for (Fordulo f : fordulok) {
            List<Type> tipus = f.getTipus();
            osszes += tipus.size();
            for (Type t : tipus) {
                if(t.equals(Type._2)){
                    masodikDb++;
                }
            }
        }
        return (masodikDb*1.0/osszes)*100;
    }
    
    public double Dontetlen(){
        int osszes = 0;
        int dontetlen = 0;
        for (Fordulo f : fordulok) {
            List<Type> tipus = f.getTipus();
            osszes += tipus.size();
            for (Type t : tipus) {
                if(t.equals(Type.x)){
                    dontetlen++;
                }
            }
        }
        return (dontetlen*1.0/osszes)*100;
    }
    
    private Fordulo Keres(String datum){
        Fordulo keresett = null;
        for (Fordulo f : fordulok) {
            if(f.getDatum().toLocaleString().split(" ")[0].equals(datum)){
                keresett = f;
            }
        }
        return keresett;
    }
    
    public String Tipp(String datum, String tipp){
        try{
            String s = "";
            if(Keres(datum) != null){
                Fordulo elem = Keres(datum);
                List<Type> tipusok = elem.getTipus();
                int i = 0;
                int talalat = 0;
                Type tipus = null;
                for (Type t : tipusok) {
                    switch(tipp.toLowerCase().charAt(i)){
                        case '1':
                            tipus = Type._1;
                            break;
                        case '2':
                            tipus = Type._2;
                            break;
                        case 'x':
                            tipus = Type.x;
                            break;
                    }
                    if(tipus == t){
                        talalat++;
                    }
                    i++;
                }
                switch(talalat){
                    case 14:
                        s+="Eredmény: találat: 14, nyeremény: " + elem.getTalalatok().get(0).getNyeremeny() + " Ft";
                        break;
                    case 13:
                        s+="Eredmény: találat: 14, nyeremény: " + elem.getTalalatok().get(1).getNyeremeny() + " Ft";
                        break;
                    case 12:
                        s+="Eredmény: találat: 14, nyeremény: " + elem.getTalalatok().get(2).getNyeremeny() + " Ft";
                        break;
                    case 11:
                        s+="Eredmény: találat: 14, nyeremény: " + elem.getTalalatok().get(3).getNyeremeny() + " Ft";
                        break;
                    case 10:
                        s+="Eredmény: találat: 14, nyeremény: " + elem.getTalalatok().get(4).getNyeremeny() + " Ft";
                        break;
                    default:
                        s+="Nem nyert!";
                }
            }
            else{
                System.out.println("Nem volt ilyen elem!");
            }
            return s;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Rossz tipp bemenet!");
            return "";
        }
    }
    
    @Override
    public String toString() {
        String s = "";
        for(Fordulo f : fordulok){
            s+=f + "\n";
        }
        return s;
    }
}

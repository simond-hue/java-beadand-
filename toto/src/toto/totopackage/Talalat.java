package toto.totopackage;

public class Talalat {
    private int talalatokSzama;
    private int nyeremeny;
    private int nyertTalalatokSzama;

    public Talalat(int talalatokSzama, int nyeremeny) {
        this.talalatokSzama = talalatokSzama;
        this.nyeremeny = nyeremeny;
    }

    public Talalat(int talalatokSzama, int nyeremeny, int nyertTalalatokSzama) {
        this.talalatokSzama = talalatokSzama;
        this.nyeremeny = nyeremeny;
        this.nyertTalalatokSzama = nyertTalalatokSzama;
    }

    public int getTalalatokSzama() {
        return talalatokSzama;
    }

    public int getNyeremeny() {
        return nyeremeny;
    }

    public int getNyertTalalatokSzama() {
        return nyertTalalatokSzama;
    }
    
    @Override
    public String toString() {
        return String.format(this.talalatokSzama + " | " + this.nyeremeny + " | ");
    }
}

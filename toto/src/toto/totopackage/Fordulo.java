package toto.totopackage;

import java.util.Date;
import java.util.List;

public class Fordulo {
    private int ev;
    private int het;
    private int fordulo;
    private Date datum;
    
    private List<Talalat> talalatok;
    private List<Type> tipus;

    public Fordulo(int ev, int het, int fordulo, Date datum, List<Talalat> talalatok, List<Type> tipus) {
        this.ev = ev;
        this.het = het;
        this.fordulo = fordulo;
        this.datum = datum;
        this.talalatok = talalatok;
        this.tipus = tipus;
    }

    public int getEv() {
        return ev;
    }

    public int getHet() {
        return het;
    }

    public int getFordulo() {
        return fordulo;
    }

    public Date getDatum() {
        return datum;
    }

    public List<Talalat> getTalalatok() {
        return talalatok;
    }

    public List<Type> getTipus() {
        return tipus;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public void setHet(int het) {
        this.het = het;
    }

    public void setFordulo(int fordulo) {
        this.fordulo = fordulo;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setTalalatok(List<Talalat> talalatok) {
        this.talalatok = talalatok;
    }

    public void setTipus(List<Type> tipus) {
        this.tipus = tipus;
    }

    @Override
    public String toString() {
        String s = "";
        
        s += this.ev + " - " + this.het + " - " + this.fordulo + " - " + this.datum.toLocaleString().split(" ")[0] + " - ";
        for (Talalat t : talalatok) {
            s+=t;
        }
        for (Type t : tipus) {
            s+=t.name();
        }
        return s;
    }
}

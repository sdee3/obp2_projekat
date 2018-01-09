public class Porudzbina {
    private String velicinaPice, vrstaPice, adresa, nacinPlacanja, imePrezime,
        brojTelefona, napomena, dodaci;

    Porudzbina(){
        this.adresa = "";
        this.brojTelefona = "";
        this.imePrezime = "";
        this.nacinPlacanja = "";
        this.napomena = "";
        this.velicinaPice = "";
        this.vrstaPice = "";
        this.dodaci = "";
    }

    public void setVelicinaPice(String velicinaPice) {
        this.velicinaPice = velicinaPice;
    }

    public void setVrstaPice(String vrstaPice) {
        this.vrstaPice = vrstaPice;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public void addDodatak(String dodatak) {
        if(this.dodaci.length() == 0)
            this.dodaci = dodatak;
        else
            this.dodaci += ", " + dodatak;
    }

    public String toString(){
        return  "Vrsta pice: " + this.vrstaPice +
                ", Veličina pice: " + this.velicinaPice +
                ", Dodaci: " + this.dodaci +
                "\tIme i prezime: " + this.imePrezime +
                ", Adresa: " + this.adresa +
                ", Broj telefona: " + this.brojTelefona +
                "\tNačin plaćanja: " + this.nacinPlacanja +
                "\tNapomena: " + this.napomena;
    }
}

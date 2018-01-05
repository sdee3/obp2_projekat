public class Porudzbina {
    private String velicinaPice, vrstaPice, adresa, nacinPlacanja, imePrezime,
        brojTelefona, napomena, dodaci;

    public Porudzbina(){
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
                "\nVeličina pice: " + this.velicinaPice +
                "\nDodaci: " + this.dodaci +
                "\nIme i prezime: " + this.imePrezime +
                "\nAdresa: " + this.adresa +
                "\nBroj telefona: " + this.brojTelefona +
                "\nNačin plaćanja: " + this.nacinPlacanja +
                "\nNapomena: " + this.napomena;
    }
}

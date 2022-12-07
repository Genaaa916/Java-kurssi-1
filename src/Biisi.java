public class Biisi {
    public int numero;
    public String nimi;
    public String artisti;
    public Double pituus;

    public Biisi(String laulu, String artisti, Double kesto, int nro) {
        this.nimi = laulu;
        this.artisti = artisti;
        this.pituus = kesto;
        this.numero = nro;
    }

    /*
     * public void lisaaNro(int nro) {
     * this.numero = nro;
     * 
     * }
     */

    public String getNimi() {
        return nimi;
    }

    /*
     * public String allStrings() {
     * return String.valueOf(numero + nimi + artisti + pituus);
     * }
     */

    public String toString() {
        return "Tunniste: " + numero + "\n Nimi: " + nimi + "\n Artisti: " + artisti + "\n Pituus: " + pituus
                + " min";

    }
}

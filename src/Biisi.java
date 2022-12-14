import java.util.Arrays;

public class Biisi {
    private int numero;
    String nimi;
    String artisti;
    private Double pituus;
    private String[] palvelut;

    public Biisi(String laulu, String artisti, Double kesto, int nro) {
        this.nimi = laulu;
        this.artisti = artisti;
        this.pituus = kesto;
        this.numero = nro;
    }

    public void lisaaPalvelu(String[] lista) {
        this.palvelut = lista;
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
                + " min" + "\n Loytyy palveluista: " + Arrays.toString(palvelut);

    }
}

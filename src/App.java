import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class App {
    // validoidaan käyttäjän syöte, otetaan kiinni NFE
    public static boolean onkoLuku(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // printataan kaikki hashmapin biisit
    public static void tulosta(Map<Integer, Object> biisiValikoima) {
        biisiValikoima.forEach((z, y) -> System.out.println((y)));
        if (biisiValikoima.size() < 1) {
            System.out.println("Ei biisejä.");
        }
    }

    // poistetaan yksi biisi ohjelmasta
    public static void poista(Map<Integer, Object> bV) {
        Scanner lukija = new Scanner(System.in);
        bV.forEach((z, y) -> System.out.println((y)));
        System.out.println("Minkä biisin haluat poistaa? \nSyötä tunnistenumero:");
        int poistoNro = Integer.valueOf(lukija.nextLine());
        System.out.println("Poistettiin biisi " + ((Biisi) bV.get(poistoNro)).getNimi());
        bV.remove(poistoNro);
    }

    // poistetaan kaikki biisit ohjelmasta
    public static void tyhjenna(Map<Integer, Object> biisiValikoima) {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Tyhjennetäänkö biisilista?\n1. Kyllä\n2. Ei");
        int x = Integer.parseInt(lukija.nextLine());
        if (x == 1) {
            biisiValikoima.clear();
            System.out.println("Tyhjennetty.");
        } else {
            return;
        }
    }

    // tuo biisit tiedostosta biisit.txt
    public static int tuo(Map<Integer, Object> biisiValikoima, int i) {
        try {
            File filu = new File("Biisit.txt");
            Scanner filuLukija = new Scanner(filu);
            ArrayList tiedot = new ArrayList<>();
            while (filuLukija.hasNextLine()) {
                String rivi = filuLukija.nextLine();
                String[] tietoRivi = rivi.split(": ");
                tiedot.add(tietoRivi[tietoRivi.length - 1]);
                if (rivi.equals(" // ")) {
                    String biisi = String.valueOf(tiedot.get(1));
                    String artisti = String.valueOf(tiedot.get(2));
                    String kestoStr = String.valueOf(tiedot.get(3) + "");
                    String[] biisiPala = kestoStr.split(" ");
                    Double kesto = Double.valueOf(biisiPala[0]);
                    Integer nro = Integer.parseInt(tiedot.get(0) + "");
                    Biisi uusibiisi = new Biisi(biisi, artisti, kesto, nro);
                    biisiValikoima.put(nro, uusibiisi);
                    tiedot.clear();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei löydy.");
            e.printStackTrace();
        }
        return i;
    }

    // viedään biisit tiedostoon biisit.txt
    public static void vie(Map<Integer, Object> biisiValikoima) throws IOException {
        System.out.println("Tallennetaan tiedostoon... ");
        ArrayList<Object> values = new ArrayList<>(biisiValikoima.values());
        FileWriter kirjoittaja = new FileWriter("Biisit.txt");
        for (Object o : values) {
            try {
                kirjoittaja.write(o + "\n // \n");
            } catch (IOException e) {
                System.out.println("Tallennus epäonnistui.");
                e.printStackTrace();
            }
        }
        System.out.println("Tallennettu!");
        kirjoittaja.close();
    }

    // lisätään biisi ohjelmaan
    public static int lisaaBiisi(int i, Map<Integer, Object> bV) {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Minkä nimisen biisin haluat lisätä?");
        String biisi = lukija.nextLine();
        System.out.println("Mikä on artistin nimi?");
        String nimi = lukija.nextLine();
        String kestoStr = "";
        while (true) {
            System.out.println("Mikä on biisin kesto?");
            kestoStr = lukija.nextLine();
            if (!onkoLuku(kestoStr)) {
                System.out.println("Ei ollut luku. Syötä pituus lukuna. ");
            } else {
                break;
            }
        }
        Double kesto = Double.parseDouble(kestoStr);
        Biisi uusibiisi = new Biisi(biisi, nimi, kesto, i);
        System.out.println("Mistä palveluista biisi löytyy?");
        String[] palveluLista = lukija.nextLine().split(", ");
        uusibiisi.lisaaPalvelu(palveluLista);
        bV.put(i, uusibiisi);
        System.out.println(
                String.format("Lisättiin biisi %s", uusibiisi.nimi, " artistilta %s", uusibiisi.artisti, "."));
        return i;
    }

    public static void main(String[] args) throws Exception {
        // luodaan hashmap, ja biisin id-muuttuja
        Map<Integer, Object> biisiValikoima = new HashMap<>();
        Scanner lukija = new Scanner(System.in);
        int i = 1;
        // käyttöliittymän looppi
        while (true) {
            String kayttis = "Mitä haluat tehdä? Valitse 1-7 \n1. Lisää uusi biisi \n2. Näytä nykyiset biisit \n3. Poista biisi\n4. Tyhjennä biisilista \n5. Tallenna tiedostoon \n6. Tuo tiedostosta \n7. Poistu";
            System.out.println(kayttis);
            String s = lukija.nextLine();
            while (!onkoLuku(s)) {
                System.out.println(kayttis);
                s = lukija.nextLine();
            }
            int x = Integer.parseInt(s);

            switch (x) {
                case 1:
                    lisaaBiisi(i, biisiValikoima);
                    i = biisiValikoima.size() + 1;
                    break;
                case 2:
                    tulosta(biisiValikoima);
                    break;
                case 3:
                    poista(biisiValikoima);
                    break;
                case 4:
                    tyhjenna(biisiValikoima);
                    break;
                case 5:
                    vie(biisiValikoima);
                    break;
                case 6:
                    tuo(biisiValikoima, i);
                    i = biisiValikoima.size() + 1;
                    break;
                case 7:
                    System.out.println("Halusit poistua. Hyvää päivänjatkoa!");
                    return;
            }
        }
    }
}
// TODO: iteraattori ei kasva oikein, biisit tallentuu päällekkäin, korjaa bugi
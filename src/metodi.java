import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class metodi {

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

    public static void kayttis() {
        String kayttisStr = "Mitä haluat tehdä? Valitse 1-7 \n1. Lisää uusi biisi \n2. Näytä nykyiset biisit \n3. Poista biisi\n4. Tyhjennä biisilista \n5. Tallenna tiedostoon \n6. Hae artistin nimellä \n7. Tuo tiedostosta \n8. Poistu";
        System.out.println(kayttisStr);
    }

    // poistutaan
    public static void poistu() {
        String poistu = "Halusit poistua. Hyvää päivänjatkoa!";
        System.out.println(poistu);
    }

    // printataan kaikki hashmapin biisit
    public static void tulosta(Map<Integer, Object> bV) {
        bV.forEach((z, y) -> System.out.println(((y)) + " \n"));
        if (bV.size() < 1) {
            System.out.println("Ei biisejä.");
        }
    }

    // haetaan biisiä valikoimasta artistin nimellä
    public static void haeArtistilla(Map<Integer, Object> bV) {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Syötä artistin nimi: ");
        String haku = lukija.nextLine();
        Collection<Object> val = bV.values();
        Boolean loytyi = false;
        for (Object o : val) {
            if (((Biisi) o).getNimi().equals(haku)) {
                System.out.println(o);
                loytyi = true;
            }
        }
        if (!loytyi) {
            System.out.println("Nimellä ei löydy biisiä.");
        }

    }

    // poistetaan yksi biisi ohjelmasta
    public static void poista(Map<Integer, Object> bV) {
        Scanner lukija = new Scanner(System.in);
        bV.forEach((z, y) -> System.out.println(((y)) + " \n"));
        System.out.println("Minkä biisin haluat poistaa? \nSyötä tunnistenumero:");
        int poistoNro = Integer.valueOf(lukija.nextLine());
        if (bV.containsKey(poistoNro)) {
            System.out.println("Poistettiin biisi " + ((Biisi) bV.get(poistoNro)).getNimi());
            bV.remove(poistoNro);
        } else {
            System.out.println("Tunnistenumerolla ei löydy biisiä.");
        }
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
        Random random = new Random();
        int r = random.nextInt(99);
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
                    if (!biisiValikoima.containsKey(nro)) {
                        biisiValikoima.put(nro, uusibiisi);
                    } else {
                        uusibiisi.numero = r;
                        nro = r;
                        biisiValikoima.put(nro, uusibiisi);
                    }
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
        String nimi;
        String biisi;
        System.out.println("Minkä nimisen biisin haluat lisätä?");
        biisi = lukija.nextLine();
        while (biisi.isEmpty()) {
            System.out.println("Nimi ei voi olla tyhjä. ");
            biisi = lukija.nextLine();
        }
        System.out.println("Mikä on artistin nimi?");
        nimi = lukija.nextLine();
        while (nimi.isEmpty()) {
            System.out.println("Nimi ei voi olla tyhjä.");
            nimi = lukija.nextLine();
        }
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
        /*
         * System.out.println("Mistä palveluista biisi löytyy?");
         * String[] palveluLista = lukija.nextLine().split(", ");
         * uusibiisi.lisaaPalvelu(palveluLista);
         */ bV.put(i, uusibiisi);
        System.out.println(
                String.format("Lisättiin biisi %s", uusibiisi.nimi, " artistilta %s", uusibiisi.artisti, "."));
        return i;
    }

}

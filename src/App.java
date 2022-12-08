import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class App {
    // validoidaan käyttäjän syöte, oltava numeerinen string tai ohjelma heittää NFE
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

    public static void main(String[] args) throws Exception {
        // luodaan hashmap, ja iteraattori tunnistenroa varten
        Map<Integer, Object> biisiValikoima = new HashMap<>();
        Scanner lukija = new Scanner(System.in);
        int i = 1;
        // käyttöliittymän looppi
        while (true) {
            System.out.println(
                    "Mitä haluat tehdä? Valitse 1-4 \n1. Lisää uusi biisi \n2. Näytä nykyiset biisit \n3. Poista biisi\n4. Tyhjennä biisilista \n5. Tallenna tiedostoon \n6. Tuo tiedostosta \n7. Poistu");
            int x = Integer.parseInt(lukija.nextLine());
            if (x == 1) {
                System.out.println("Minkä nimisen biisin haluat lisätä?");
                String biisi = lukija.nextLine();
                System.out.println("Mikä on artistin nimi?");
                String nimi = lukija.nextLine();
                String kestoStr = "";
                while (true) {
                    System.out.println("Mikä on biisin kesto?");
                    kestoStr = lukija.nextLine();
                    if (onkoLuku(kestoStr) == false) {
                        System.out.println("Ei ollut luku. Syötä pituus lukuna. ");
                    } else if (onkoLuku(kestoStr)) {
                        break;
                    }
                }
                Double kesto = Double.valueOf(kestoStr);
                // luodaan biisi käyttäjäsyötteen tiedoilla, viedään hashmappiin
                // key on sama kuin olion numeromuuttuja
                Biisi uusibiisi = new Biisi(biisi, nimi, kesto, i);
                biisiValikoima.put(i, uusibiisi);
                // iteraattori kasvaa
                i++;
                System.out.println(
                        String.format("Lisättiin biisi %s", uusibiisi.nimi, " artistilta %s", uusibiisi.artisti, "."));
                /*
                 * System.out.println(uusibiisi.numero);
                 */ } else if (x == 2) {
                // iteroidaan hashmap per joka key/value, printataan pelkkä value toStringillä
                biisiValikoima.forEach((z, y) -> System.out.println((y)));
                // jos tyhjä biisivalkoima
                if (biisiValikoima.size() < 1) {
                    System.out.println("Ei biisejä.");
                }
            } else if (x == 3) {
                biisiValikoima.forEach((z, y) -> System.out.println((y)));
                System.out.println("Minkä biisin haluat poistaa? \nSyötä tunnistenumero:");
                int poistoNro = Integer.valueOf(lukija.nextLine());
                // haetaan nimi hashmapista oliometodilla
                System.out.println("Poistettiin biisi " + ((Biisi) biisiValikoima.get(poistoNro)).getNimi());
                biisiValikoima.remove(poistoNro);
            } else if (x == 7) {
                System.out.println("Halusit poistua. Hyvää päivänjatkoa!");
                break;
            } else if (x == 4) {
                // Resetoidaan biisilista
                System.out.println("Tyhjennetäänkö biisilista?\n1. Kyllä\n2. Ei");
                x = Integer.parseInt(lukija.nextLine());
                if (x == 1) {
                    biisiValikoima.clear();
                    System.out.println("Tyhjennetty.");
                } else {
                    continue;
                }
            } else if (x == 5) {
                System.out.println("Tallennetaan tiedostoon... ");
                // hae oliot hashmapista, vie arraylistiin.
                // ArrayList on yksinkertaisempi iteroida
                ArrayList<Object> values = new ArrayList<>(biisiValikoima.values());
                FileWriter kirjoittaja = new FileWriter("Biisit.txt");
                // kirjoita .txt-tiedostoon olio per olio, ota kiinni IO errori eli jos
                // Biisit.txt ei löydy
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
            } else if (x == 6) {
                try {
                    File filu = new File("Biisit.txt");
                    Scanner filuLukija = new Scanner(filu);
                    ArrayList tiedot = new ArrayList<>();
                    i = biisiValikoima.size() + 1;
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
            }
        }
    }
}

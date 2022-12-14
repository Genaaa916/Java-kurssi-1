import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;

public class App {
    // validoidaan käyttäjän syöte, otetaan kiinni NFE

    public static void main(String[] args) throws Exception {
        // luodaan hashmap, ja biisin id-muuttuja
        Map<Integer, Object> biisiValikoima = new HashMap<>();
        Scanner lukija = new Scanner(System.in);
        Random random = new Random();
        int i = random.nextInt(50);
        // käyttöliittymän looppi
        while (true) {
            String kayttis = "Mitä haluat tehdä? Valitse 1-7 \n1. Lisää uusi biisi \n2. Näytä nykyiset biisit \n3. Poista biisi\n4. Tyhjennä biisilista \n5. Tallenna tiedostoon \n6. Tuo tiedostosta \n7. Poistu";
            System.out.println(kayttis);
            String s = lukija.nextLine();
            while (!metodi.onkoLuku(s)) {
                System.out.println(kayttis);
                s = lukija.nextLine();
            }
            int x = Integer.parseInt(s);

            switch (x) {
                case 1:
                    metodi.lisaaBiisi(i, biisiValikoima);
                    i += (biisiValikoima.size() * 5);
                    break;
                case 2:
                    metodi.tulosta(biisiValikoima);
                    break;
                case 3:
                    metodi.poista(biisiValikoima);
                    break;
                case 4:
                    metodi.tyhjenna(biisiValikoima);
                    break;
                case 5:
                    metodi.vie(biisiValikoima);
                    break;
                case 6:
                    metodi.tuo(biisiValikoima, i);
                    i += (biisiValikoima.size() * 6);
                    break;
                case 7:
                    System.out.println("Halusit poistua. Hyvää päivänjatkoa!");
                    return;
            }
        }
    }
}

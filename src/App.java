import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        // luodaan hashmap, ja biisin id-muuttuja
        Map<Integer, Object> biisiValikoima = new HashMap<>();
        Scanner lukija = new Scanner(System.in);
        // käyttöliittymän looppi
        while (true) {
            metodi.kayttis();
            String s = lukija.nextLine();
            while (!metodi.onkoLuku(s)) {
                metodi.kayttis();
                s = lukija.nextLine();
            }
            int x = Integer.parseInt(s);

            switch (x) {
                case 1:
                    metodi.lisaaBiisi(biisiValikoima);
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
                    metodi.haeBiisilla(biisiValikoima);
                    break;
                case 7:
                    metodi.haeArtistilla(biisiValikoima);
                case 8:
                    metodi.tuo(biisiValikoima);
                    break;
                case 9:
                    metodi.poistu();
                    return;
            }
        }
    }
}


import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Kayttoliittyma{
    private Scanner lukija;
    private Sanalista sanalista;
    private ArvatutKirjaimet arvatutKirjaimet;
    
    public Kayttoliittyma(Scanner lukija, Sanalista sanalista, ArvatutKirjaimet arvatutKirjaimet){
        this.lukija = lukija;
        this.sanalista = sanalista;
        this.arvatutKirjaimet = arvatutKirjaimet;
    }
       
    public void sanojenLisays(){
        Scanner lukija = new Scanner(System.in);
        System.out.println("Kirjoita sana. Kun sanoja on riittävästi, paina vain Enter");
            while(true){
                String sana = lukija.nextLine();
                if(!(sana.equals(""))){
                    this.sanalista.lisaaSana(sana);
                } else{
                    break;
                }               
            }
    }
    
    public void kaynnista(){
        sanalista.lataaSanat();
        
        while(true){
            System.out.println(" ");
            System.out.println("Pelaa - pelaa");
            System.out.println("Lisaa sanoja - lisaa");
            System.out.println("Lopeta - lopeta");
            System.out.println("Näytä sanat - sanat");
            System.out.println("Poista kaikki sanat - poista");
            System.out.println("Poista yksi sana - unohda");
            System.out.println(" ");

            String komento = lukija.nextLine();

            
            if(komento.contains("lopeta")){
                sanalista.tallenna();
                break;
            }
            
            if(komento.contains("poista")){
                sanalista.poistaKaikkiSanat();
                System.out.println("");
            }
              
            if(komento.contains("unohda")){
                sanalista.tulostaSanat();
                System.out.print("Anna sanan numero: ");
                String syote = lukija.nextLine();
                try{
                    int poistettava = Integer.parseInt(syote);
                    sanalista.poistaSana(poistettava);
                } catch (NumberFormatException nfe){
                    System.out.println("Anna numero!");
                }
            }
            
            if(komento.contains("sanat")){
                
                System.out.println("");
                System.out.println("Lisätyt sanat:");
                sanalista.tulostaSanat();
                System.out.println("");
            }
        
            if(komento.contains("lisaa")){
                sanojenLisays();
            }
            
            //tästä alkaa peli
            if(komento.contains("pelaa")){                
                if(sanalista.getKoko() == 0){
                    while(sanalista.getKoko() <= 0){
                        sanojenLisays();
                    }
                System.out.println("Kiitos! Peli alkaa.");
                }
                
                //lukee sanan ja pilkkoo sen
                Sana sana = new Sana(sanalista.getSana());
                String[] osat = sana.etsittySana().split("");

                //apumuuttuja, jonka avulla piilotetaan sana näkyvistä arvauksissa
                String[] arvattu = sana.etsittySana().split("");
                for(int i = 0; i < sana.etsittySana().length();i++){
                    arvattu[i] = " _ ";
                }

                System.out.println("");
                System.out.println("Lopeta peli painamalla Enter.");
                System.out.println("Kun tiedät sanan, kirjoita se ja paina Enter.");
                
                arvatutKirjaimet.tyhjennaArvatutKirjaimet();
                
                while(true){
                    System.out.println("");
                    System.out.println("");
                    
                    for(int a = 0; a < sana.etsittySana().length();a++){
                        System.out.print(arvattu[a] + " ");

                    }
                    System.out.println("");
                    System.out.println("\n" + "Anna kirjain: ");
                    String kirjain = lukija.nextLine();

                    if(kirjain.equals("")){
                        break;               
                    }

                    if(kirjain.equals(sana.etsittySana())){
                        System.out.println("");
                        System.out.println("Hyvä! Sana on oikein!");
                        System.out.println("Kokeilit kirjaimia " + sana.arvatutKerrat() + " kertaa");
                        System.out.println("");
                        break;
                    }

                    if(sana.etsittySana().contains(kirjain)){

                        for(int i = 0; i < sana.etsittySana().length();i++){
                            if(osat[i].contains(kirjain)){
                                    arvattu[i] = osat[i];                                     
                            }
                        }   

                    }else{
                        sana.vaarin();
                        System.out.println("Virheitä: " + sana.virheet());
                        System.out.println("");
                        if(sana.virheet() > 6){
                            System.out.println("Liikaa virheitä. Hävisit pelin.");
                            break;
                        }
                    }
                    arvatutKirjaimet.lisaaListaan(kirjain);
                    System.out.print("Arvatut kirjaimet: "); 
                    arvatutKirjaimet.tulostaArvatutKirjaimet();

                    sana.arvattu();
                }
            }                
        }
    }
}

import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Sanalista {
    private List<String> sanalista;
    private ListView<String> listaus;
    
    public Sanalista(){
        this.sanalista = new ArrayList<>();
        this.listaus = new ListView();
    }
    
    
    public ListView<String> getLista() {
    	return this.listaus;
    }
    
    public Parent poistaRiviListViewista() {
        GridPane asettelu = new GridPane();


        asettelu.setAlignment(Pos.CENTER);
        asettelu.setVgap(10);
        asettelu.setHgap(10);
        asettelu.setPadding(new Insets(10, 10, 10, 10));

        Button poistanappi = new Button("Poista valittu sana");

        asettelu.add(poistanappi, 0, 0);
       
        poistanappi.setOnMouseClicked((event) ->{
        	int poistettava = this.listaus.getSelectionModel().getSelectedIndex();
        	poistaSana(poistettava);
        	this.listaus.getItems().removeAll(this.listaus.getSelectionModel().getSelectedItems());
        });

        return asettelu; 	
    }
    
    public void lisaaSana(String lisattava){           
        if(!(sanalista.contains(lisattava))){
            sanalista.add(lisattava);
            listaus.getItems().add(lisattava);
        }            
    }
    
    public Parent lisaaSanaNakyma() {
        GridPane asettelu = new GridPane();

        Label sanaohje = new Label("Sana");
        TextField sanakentta = new TextField();

        asettelu.setAlignment(Pos.CENTER);
        asettelu.setVgap(10);
        asettelu.setHgap(10);
        asettelu.setPadding(new Insets(10, 10, 10, 10));

        Button lisaanappi = new Button("Lisää sana");

        asettelu.add(sanaohje, 0, 0);
        asettelu.add(sanakentta, 0, 1);
        asettelu.add(lisaanappi, 0, 4);

        lisaanappi.setOnMouseClicked((event) -> {
            String sana = sanakentta.getText();

            lisaaSana(sana);

            sanakentta.clear();
        });
        
        return asettelu; 	
    }
       
    public String getSana(){
        Random rand = new Random();
        int randomNumero = rand.nextInt((sanalista.size()-1) + 1);
        return sanalista.get(randomNumero);
    }
    
    public void tulostaSanat(){
        for(int i = 0 ; i < this.sanalista.size(); i++){
            System.out.println((i + 1) + ". " + this.sanalista.get(i));
        }
    }

    public int getKoko(){
        return sanalista.size();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.sanalista);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sanalista other = (Sanalista) obj;
        if (!Objects.equals(this.sanalista, other.sanalista)) {
            return false;
        }
        return true;
    }
    
    public void lataaSanat(){
        String tiedostonimi = "C:\\Users\\kimjo\\Documents\\NetBeansProjects\\mavenproject3\\Miikan\\sanat.txt";
        try (Scanner tiedostonLukija = new Scanner(Paths.get(tiedostonimi))) {
            
            while (tiedostonLukija.hasNextLine()) {
                String sana = tiedostonLukija.nextLine();
                lisaaSana(sana);
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }        
    }
    
    public void tallenna(){
        String tiedostonimi = "C:\\Users\\kimjo\\Documents\\NetBeansProjects\\mavenproject3\\Miikan\\sanat.txt";
        try(PrintWriter kirjoittaja = new PrintWriter(tiedostonimi)){
            for(int i = 0; i < this.sanalista.size(); i++){
                    kirjoittaja.println(this.sanalista.get(i));                   
                }
            kirjoittaja.close();                  
        }catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());     
        }
    }
    
    public void poistaKaikkiSanat(){
            this.sanalista.clear();
    }
    
    public void poistaSana(int sananNumero){
        this.sanalista.remove(sananNumero);
        
    }
    
}
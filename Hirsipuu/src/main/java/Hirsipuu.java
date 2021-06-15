import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Hirsipuu extends Application{
	public int arvaukset;
	public int vaarat;
	
	public Hirsipuu() {
		this.arvaukset = 0;
		this.vaarat = 0;
	}
    
        @Override
        public void start(Stage ikkuna){
        	// Luodaan tarpeelliset oliot
            
            Sanalista sanalista = new Sanalista();            
            
        	sanalista.lataaSanat();
            
            //asettelu
	        BorderPane asettelu = new BorderPane();
	        
	        HBox painikkeet = new HBox();
	        
	        Button pelaa = new Button("Pelaa");
	        Button lopeta = new Button("Lopeta");
	        Button sanat = new Button("Näytä sanat");
	        Button lisaaSana = new Button("Lisää sana");
	        
	        painikkeet.getChildren().addAll(pelaa, sanat, lisaaSana, lopeta);
	        
	        Label tekstikentta = new Label("");

	        
	        //Painikkeiden komennot
	        sanat.setOnMouseClicked((event) ->{
	        	asettelu.setBottom(null);
	        	asettelu.setLeft(null);
	        	asettelu.setCenter(sanalista.getLista());
	        	asettelu.setRight(sanalista.poistaRiviListViewista());
	        });
	        
	        	        
	        lisaaSana.setOnMouseClicked((event) ->{
	        	asettelu.setBottom(null);
	        	asettelu.setLeft(null);
	        	asettelu.setCenter(sanalista.lisaaSanaNakyma());
	        	asettelu.setRight(tekstikentta);
	        	
	        });
	        
	        lopeta.setOnMouseClicked((event) ->{
	        	sanalista.tallenna();
	        	ikkuna.close();
	        });
	        //Tästä alkaa peli
	        pelaa.setOnMouseClicked((event) ->{
	        	this.arvaukset = 0;
	        	this.vaarat = 0;
	        	
	        	//vasempaan reunaan tulevat muotoilut
	        	VBox vasenReuna = new VBox();
	        	vasenReuna.setAlignment(Pos.CENTER);
	        	Label arvaustenMaara = new Label();
	        	Label virheet = new Label();
	        	Label arvatutKirjaimet = new Label();
	        	
	        	vasenReuna.getChildren().addAll(arvaustenMaara, virheet, arvatutKirjaimet);
	        	asettelu.setLeft(vasenReuna);
	        	arvaustenMaara.setText("Arvauksia " + this.arvaukset);
	        	virheet.setText("Virheitä " + this.vaarat);
	        	
                //lukee sanan ja pilkkoo sen
                Sana sana = new Sana(sanalista.getSana());
                String[] osat = sana.etsittySana().split("");
                
                //apumuuttuja, jonka avulla piilotetaan sana näkyvistä arvauksissa
                String[] arvattu = sana.etsittySana().split("");
                
                StringBuilder piilotettu = new StringBuilder();
                for(int i = 0; i < sana.etsittySana().length();i++){
                    arvattu[i] = " _ ";
                    piilotettu.append(arvattu[i]);
                }
                
    	        Label arvattavaSana = new Label(piilotettu.toString());
                asettelu.setCenter(arvattavaSana);
                
                //kirjaimen syöttö
                GridPane asetteluPelissa = new GridPane();
                

                Label kirjain = new Label("Kirjain");
                TextField sanakentta = new TextField();
                Label tyhjaTekstikentta = new Label("");
                
                asettelu.setRight(tyhjaTekstikentta);

                asetteluPelissa.setAlignment(Pos.CENTER);
                asetteluPelissa.setVgap(10);
                asetteluPelissa.setHgap(10);
                asetteluPelissa.setPadding(new Insets(10, 10, 10, 10));

                Button annaKirjain = new Button("Syötä kirjain");

                asetteluPelissa.add(kirjain, 0, 0);
                asetteluPelissa.add(sanakentta, 0, 1);
                asetteluPelissa.add(annaKirjain, 0, 3);

                //Kirjaimen syöttöpainike
                
                annaKirjain.setOnMouseClicked(ae -> {
                	
                    String kirjainKentasta = sanakentta.getText();
                    arvaukset += 1; 
                    
                    if(arvaukset == 1) {
                    	arvatutKirjaimet.setText(kirjainKentasta);
                    } else {
                    	arvatutKirjaimet.setText(arvatutKirjaimet.getText() + ", " + kirjainKentasta);
                    }
                                                                 
                    if(sana.etsittySana().contains(kirjainKentasta) && !(kirjainKentasta.isBlank()) ){

                    	//Käy läpi, sisältääkö sana kirjaimen
                        StringBuilder nayta = new StringBuilder();
                        for(int i = 0; i < sana.etsittySana().length();i++){
                            if(osat[i].contains(kirjainKentasta)){
                                    arvattu[i] = osat[i];
                                    
                            }
                            //Tämä tulee, jos sanan kaikki kirjaimet ovat oikein
                            nayta.append(arvattu[i]);
                            	if(nayta.toString().equals(sana.etsittySana())) {
                            		Label voitto = new Label("Voitit pelin!");
                            		asetteluPelissa.add(voitto, 0, 1);
                            	}

                        }  

                        arvattavaSana.setText(nayta.toString());                        
                        asettelu.setLeft(vasenReuna);
                        asettelu.setCenter(arvattavaSana);
                        
                    } else {                      
                        	this.vaarat += 1;
                        	if(this.vaarat == 7) {
	                        	Label havisit = new Label("Hävisit pelin!");
	                        	asettelu.setCenter(havisit);
	                        	asettelu.setBottom(null);
                        	}                    	
                    	
                    }
                    
                    arvaustenMaara.setText("Arvauksia " + this.arvaukset);
                    virheet.setText("Virheitä " + this.vaarat);
                    
                    sanakentta.clear();
                });
                asettelu.setBottom(asetteluPelissa);
                
	        });
	        
	        //loppuasettelu
	        
	        asettelu.setTop(painikkeet);
	        
	        Scene nakyma = new Scene(asettelu, 640, 480);
	        
	        ikkuna.setScene(nakyma);
	        ikkuna.show();
	    }
	    
    public static void main(String[] args){
        launch(Hirsipuu.class);
    } 
}
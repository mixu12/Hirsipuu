import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Sanalista {
    private ArrayList<String> sanalista;
    
    public Sanalista(){
        this.sanalista = new ArrayList<>();
    }
    
    public void lisaaSana(String lisattava){           
        if(!(sanalista.contains(lisattava))){
            sanalista.add(lisattava);
        }            
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
        String tiedostonimi = "\"C:\\\\Users\\\\kimjo\\\\Documents\\\\NetBeansProjects\\\\mavenproject3\\\\Miikan\\\\sanat.txt\"";
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
        String tiedostonimi = "\"C:\\\\Users\\\\kimjo\\\\Documents\\\\NetBeansProjects\\\\mavenproject3\\\\Miikan\\\\sanat.txt\"";
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
        this.sanalista.remove(sananNumero - 1);
        
    }
}

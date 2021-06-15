
import java.util.ArrayList;


public class ArvatutKirjaimet {
    private ArrayList<String> arvatutKirjaimet;
    
    public ArvatutKirjaimet(){
        this.arvatutKirjaimet = new ArrayList<>();
    }
    
    public void lisaaListaan(String kirjain){
        this.arvatutKirjaimet.add(kirjain);
    }
    
    public void tulostaArvatutKirjaimet(){
        for(String kirjaimet : this.arvatutKirjaimet){
            System.out.print(kirjaimet);
        }
    }
    
    public void tyhjennaArvatutKirjaimet(){
        this.arvatutKirjaimet.clear();
    }
}

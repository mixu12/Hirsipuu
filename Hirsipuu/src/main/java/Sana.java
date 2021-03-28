public class Sana {
    
    private String sana;
    private int vaarin;
    private int arvattu;
    
    public Sana(String sana){
        this.sana = sana;
        this.vaarin = 0;
        this.arvattu = 0;
        
    }
    
    public void vaarin(){
        this.vaarin++;
    }
    
    public void arvattu(){
        this.arvattu++;
    }
    
    public String etsittySana(){
        return sana;
    }
    
    public int virheet(){
        return this.vaarin;
    }
    
    public int arvatutKerrat(){
        return this.arvattu;
    }
}

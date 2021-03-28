import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Hirsipuu extends Application{
    
        @Override
        public void start(Stage ikkuna){
        BorderPane asettelu = new BorderPane();
        
        VBox painikkeet = new VBox();
        
        Button pelaa = new Button("Pelaa");
        Button lopeta = new Button("Lopeta");
        
        painikkeet.getChildren().addAll(pelaa, lopeta);
        
        asettelu.setCenter(painikkeet);
        
        Scene nakyma = new Scene(asettelu, 640, 480);
        
        ikkuna.setScene(nakyma);
        ikkuna.show();
    }
    
    public static void main(String[] args){
        launch(Hirsipuu.class);

    } 
}

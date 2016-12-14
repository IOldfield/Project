import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

public class SceneController2 
{    

    private static Stage stage;     

    public SceneController2()         
    {
        System.out.println("Initialising controllers...");

        if (stage != null)
        {
            System.out.println("Error, duplicate controller - terminating application!");
            System.exit(-1);
        }        

    } 

    public void initialize ()          
    {            
        System.out.println("Asserting controls...");
        try
        {

        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }   

        
    }

    public void prepareStageEvents(Stage stage)
    {
        System.out.println("Preparing stage events...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Exit button was clicked!");
                    Application.terminate();
                }
            });
    }     
}
    
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

public class SceneController 
{    

    private static Stage stage;     

    @FXML   private Label title;
    @FXML   private ListView centralDB;
    @FXML   private Button searchButton;
    @FXML   private TextField textField;
    @FXML   private Button addButton;
    @FXML   private Button refineButton;
    @FXML   private Button sortButton;

    public SceneController()         
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
            assert title != null : "Can't find title";
            assert centralDB != null : "Can't find CentralDB.";
            assert addButton != null : "Can't find add button.";
            assert refineButton != null : "Can't find refine button.";
            assert sortButton != null : "Can't find sort button.";
            assert searchButton != null : "Can't find search button.";
            assert textField != null : "Can't find text field.";

        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        System.out.println("Populating scene with items from the database...");     

        @SuppressWarnings("unchecked")
        List<Song> targetList = centralDB.getItems();  
        Song.readAll(targetList);
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

    @FXML   void addClicked()
    {
        System.out.println("Add was clicked!");        
    }

    @FXML   void refineClicked()
    {
        System.out.println("Refine was clicked!");        
    }

    @FXML   void sortClicked()
    {
        System.out.println("Sort was clicked!");        
    }

    @FXML   void searchClicked()
    {
        System.out.println("Search was clicked!");        
    }
    
    @FXML   void listViewClicked()
    {
        Song selectedItem = (Song) centralDB.getSelectionModel().getSelectedItem();
        
        if (selectedItem == null)
        {
            System.out.println("Nothing selected");
        }
        else
        {
            System.out.println(selectedItem +"(ID: " + selectedItem.getSongID() + ") is selected.");
            
            try
            {
                FXMLLoader loader = new FXMLLoader(Application.class.getResource("Scene2.fxml"));
                
                Stage stage = new Stage();
                stage.setTitle(selectedItem.getSongName());
                stage.setScene(new Scene(loader.load()));
                stage.show();
            }
            catch(Exception E)
            {
                System.out.println(E.getMessage());
            }
    }
}
}


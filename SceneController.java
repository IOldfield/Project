import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;

public class SceneController 
{    

    private static Stage stage;     
    
    @FXML   private Label title;
    @FXML   private Pane mainPane; 
    @FXML   private Pane tablePane;
    @FXML   private TableView centralDB;
    @FXML   private TableColumn name;
    @FXML   private TableColumn length;
    @FXML   private TableColumn artist;
    @FXML   private TableColumn people;
    @FXML   private TableColumn album;
    @FXML   private TableColumn rating;
    @FXML   private Button editButton;
    @FXML   private Button searchButton;
    @FXML   private TextField textField;
    @FXML   private Button addButton;
    @FXML   private Button deleteButton;
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
            assert mainPane != null : "Can't find main pane.";
            assert tablePane != null : "Can't find table pane.";
            assert name != null : "Can't find name column.";
            assert centralDB != null : "Can't find CentralDB.";
            assert length != null : "Can't find length column.";
            assert artist!= null : "Can't find artist column.";
            assert album!= null : "Can't find album column.";
            assert people!= null : "Can't find people column.";
            assert rating!= null : "Can't find rating column .";
            assert addButton != null : "Can't find add button.";
            assert editButton != null : "Can't find edit button.";
            assert deleteButton != null : "Can't find delete button.";
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
        <Song> targetList = centralDB.getItems();  
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

    /* The next three methods are event handlers for clicking on the buttons. 
     * The names of these methods are set in Scene Builder so they work automatically. */    
    @FXML   void addClicked()
    {
        System.out.println("Add was clicked!");        
    }

    @FXML   void editClicked()
    {
        System.out.println("Edit was clicked!");
    }

    @FXML   void deleteClicked()
    {
        System.out.println("Delete was clicked!");        
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
}



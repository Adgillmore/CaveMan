/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import uk.co.atgsoft.caveman.Wine.Wine;
import uk.co.atgsoft.caveman.Wine.WineImpl;

/**
 *
 * @author adam
 */
public class Main extends Application {
    
    private ComboBox<Wine> myWines;
    
    @Override
    public void start(Stage primaryStage) {
        
//        initDatabase();
        final Node winePanel = initWinePanel();
        final AnchorPane root = new AnchorPane();
        
        root.getChildren().addAll(winePanel);
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("CaveMan - The wine cave manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void initDatabase() {
        Connection c = null;
        Statement stmt = null;
        try {
//          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:test.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE WINE " +
                       "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                       " NAME           TEXT    NOT NULL, " + 
                       " PRODUCER       TEXT    NOT NULL, " + 
                       " VINTAGE        INT, " + 
                       " GRAPE         TEXT)"; 
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Table created successfully");
  
    }
    
    private Node initWinePanel() {
        final TextField textName = new TextField();
        textName.setPromptText("Hermitage, La Chapelle");
        
        final TextField textProducer = new TextField();
        textProducer.setPromptText("Paul Jaboulet Aine");
        
        final TextField textVintage = new TextField();
        textVintage.setPromptText("1961");
        
        final TextField textGrape = new TextField();
        textGrape.setPromptText("Syrah");
        
        final Button saveButton = new Button("Save");
        saveButton.disableProperty().bind(textName.textProperty().isEmpty()
                .or(textProducer.textProperty().isEmpty())
                .or(textGrape.textProperty().isEmpty())
                .or(textVintage.textProperty().isEmpty()));
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Wine wine = new WineImpl(textName.getText(), 
                        textProducer.getText(), 
                        Integer.parseInt(textVintage.getText()), 
                        textGrape.getText());
                insertWine(wine);
                System.out.println("Created new Wine " + wine.toString());
            }
        });
        
        myWines = new ComboBox<>();
        myWines.setCellFactory(new Callback<ListView<Wine>, ListCell<Wine>>() {
            @Override
            public ListCell<Wine> call(ListView<Wine> param) {
                final ListCell<Wine> cell = new ListCell<Wine>() {
                    
                    @Override
                    protected void updateItem(Wine item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) return;
                        setText(item.getName() + ", " + item.getProducer() 
                                + ", " + item.getVintage());
                    }
                };
                return cell;
            }
        });
        
        final Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final List<Wine> wines = getAllWines();
                myWines.getItems().setAll(wines);
            }
        });
        
        final VBox textFields = new VBox(10, textName, textProducer, textVintage, textGrape, saveButton, myWines, refreshButton);
        textFields.setPrefWidth(600);
        return textFields;
    }
    
    private void insertWine(final Wine wine) {
        Connection c = null;
        Statement stmt = null;
    try {
//      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "INSERT INTO WINE (NAME,PRODUCER,VINTAGE,GRAPE) " +
                   "VALUES (" 
              + "'" + wine.getName() + "', " 
              + "'" + wine.getProducer() + "', "
              + wine.getVintage() + ", "
              + "'" + wine.getGrape() + "');"; 
      stmt.executeUpdate(sql);

      stmt.close();
      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    System.out.println("Records created successfully");
    }
    
    private List<Wine> getAllWines() {
        final List<Wine> wines = new ArrayList<>();
        
        Connection c = null;
        Statement stmt = null;
        try {
//          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:test.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM WINE;" );
          while ( rs.next() ) {
             final Wine wine = new WineImpl(
                rs.getString("name"),
                rs.getString("producer"),
                rs.getInt("vintage"),
                rs.getString("grape"));
             wines.add(wine);
             
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
  
        
        return wines;
    }
    
    
    
}

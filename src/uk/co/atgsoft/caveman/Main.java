/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman;

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
import uk.co.atgsoft.caveman.database.DatabaseUtils;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineImpl;

/**
 *
 * @author adam
 */
public class Main extends Application {
    
    private ComboBox<Wine> myWines;
    
    @Override
    public void start(Stage primaryStage) {
        
        DatabaseUtils.createDatabase();
        final VBox root = new VBox(10, initWinePanel(), initPurchaseHistoryPanel());
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
                DatabaseUtils.insertWine(wine);
                System.out.println("Created new Wine " + wine.toString());
            }
        });
        
        final VBox textFields = new VBox(10, textName, textProducer, 
                textVintage, textGrape, saveButton);
        textFields.setPrefWidth(600);
        return textFields;
    }
    
    private Node initPurchaseHistoryPanel() {
        
        final Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final List<Wine> wines = DatabaseUtils.getAllWines();
                myWines.getItems().setAll(wines);
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
        
        final VBox purchaseHistory = new VBox(10, myWines, refreshButton);
        purchaseHistory.setPrefWidth(600);
        return purchaseHistory;
    }
    
}

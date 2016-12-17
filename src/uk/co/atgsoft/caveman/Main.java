/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman;

import com.sun.media.jfxmedia.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uk.co.atgsoft.caveman.Wine.Wine;
import uk.co.atgsoft.caveman.Wine.WineImpl;

/**
 *
 * @author adam
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        final AnchorPane root = new AnchorPane();
        
        final TextField textName = new TextField();
        textName.setPromptText("Hermitage, La Chapelle");
        
        final TextField textProducer = new TextField();
        textProducer.setPromptText("Paul Jaboulet Aine");
        
        final TextField textVintage = new TextField();
        textVintage.setPromptText("1961");
        
        final TextField textGrape = new TextField();
        textGrape.setPromptText("Syrah");
        
        final VBox textFields = new VBox(10, textName, textProducer, textVintage, textGrape);
        textFields.setPrefWidth(600);
        
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
                System.out.println("Created new Wine " + wine.toString());
            }
        });
        
        root.getChildren().addAll(textFields, saveButton);
        
        AnchorPane.setBottomAnchor(saveButton, 10.0);
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
    
}

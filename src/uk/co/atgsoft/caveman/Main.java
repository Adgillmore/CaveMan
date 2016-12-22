/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uk.co.atgsoft.caveman.database.DatabaseUtils;
import uk.co.atgsoft.caveman.database.dao.WineDao;
import uk.co.atgsoft.caveman.database.dao.WineDaoImpl;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineImpl;

/**
 *
 * @author adam
 */
public class Main extends Application {
    
    private TableView table;
    
    private WineDao wineDao;
    
    private ObservableList<Wine> wines;
    
    @Override
    public void start(Stage primaryStage) {
        DatabaseUtils.createDatabase();
        wineDao = new WineDaoImpl();
        wines = FXCollections.observableArrayList();
        final BorderPane root = new BorderPane(initPurchaseHistoryPanel(), initToolbar(), null, null, initWinePanel());
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
    
    private Node initToolbar() {
        final Button button = new Button("Add");
        final HBox toolbar = new HBox(button);
        return toolbar;
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
        
        final Button addButton = new Button("Add");
        addButton.disableProperty().bind(textName.textProperty().isEmpty()
                .or(textProducer.textProperty().isEmpty())
                .or(textGrape.textProperty().isEmpty())
                .or(textVintage.textProperty().isEmpty()));
        addButton.setOnAction((ActionEvent event) -> {
            final Wine wine = new WineImpl(textName.getText(),
                    textProducer.getText(),
                    Integer.parseInt(textVintage.getText()),
                    textGrape.getText());
            wineDao.insertWine(wine);
            wines.add(wine);
            System.out.println("Created new Wine " + wine.toString());
        });
        
        final Button removeButton = new Button("Remove");
        removeButton.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
        removeButton.setOnAction((ActionEvent event) -> {
        wines.remove(table.getSelectionModel().getFocusedIndex());
        wineDao.removeWine((Wine) table.getSelectionModel().getSelectedItem());
        });
        
        final VBox textFields = new VBox(10, textName, textProducer, 
                textVintage, textGrape, addButton, removeButton);
        textFields.setPrefWidth(250);
        return textFields;
    }
    
    private Node initPurchaseHistoryPanel() {
        table = new TableView();
        TableColumn name = new TableColumn("Name");
        TableColumn producer = new TableColumn("Producer");
        TableColumn vintage = new TableColumn("Vintage");
        TableColumn grape = new TableColumn("Grape");
        
        name.setCellValueFactory(
        new PropertyValueFactory<>("Name")
        );
        producer.setCellValueFactory(
        new PropertyValueFactory<>("Producer")
        );
        vintage.setCellValueFactory(
        new PropertyValueFactory<>("Vintage")
        );
        grape.setCellValueFactory(
        new PropertyValueFactory<>("Grape")
        );
        
        table.getColumns().addAll(name, producer, vintage, grape);
        wines.addAll(wineDao.getAllWines());
        table.setItems(wines);
        return table;
    }
    
}

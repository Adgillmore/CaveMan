/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman;

import java.math.BigDecimal;
import java.sql.Date;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import uk.co.atgsoft.caveman.database.dao.PurchaseDao;
import uk.co.atgsoft.caveman.database.dao.PurchaseDaoImpl;
import uk.co.atgsoft.caveman.database.dao.WineDao;
import uk.co.atgsoft.caveman.database.dao.WineDaoImpl;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineImpl;
import uk.co.atgsoft.caveman.wine.purchase.PurchaseRecord;
import uk.co.atgsoft.caveman.wine.purchase.PurchaseRecordImpl;

/**
 *
 * @author adam
 */
public class Main extends Application {
    
    private TableView table;
    
    private WineDao wineDao;
    
    private PurchaseDao purchaseDao;
    
    private ObservableList<Wine> wineList;
    
    private Dialog<Pair<String, String>> addWineDialog;
    
    private BooleanProperty saveDisabled = new SimpleBooleanProperty();
    
    private ObservableStringValue vintageLimit = new SimpleStringProperty("1850");
    
    @Override
    public void start(Stage primaryStage) {
        
        wineDao = new WineDaoImpl();
        purchaseDao = new PurchaseDaoImpl();
        wineList = FXCollections.observableArrayList();
        table = initTableView(wineList, wineDao);
        addWineDialog = initDialog(wineList, wineDao);
        
        final BorderPane root = new BorderPane(table, initToolbar(wineList, wineDao), 
                null, null, null);
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
    
    private Node initToolbar(final ObservableList<Wine> wines, final WineDao dao) {
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                addWineDialog.show();
            }
        });
        
        final Button removeButton = new Button("Remove");
        removeButton.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
        removeButton.setOnAction((ActionEvent event) -> {
            wines.remove(table.getSelectionModel().getFocusedIndex());
            dao.removeWine((Wine) table.getSelectionModel().getSelectedItem());
        });
        final HBox toolbar = new HBox(addButton, removeButton);
        return toolbar;
    }
    
    private Dialog initDialog(final ObservableList<Wine> wines, final WineDao dao) {
        // Init content
        final TextField textName = new TextField();
        textName.setPromptText("Hermitage, La Chapelle");
        final TextField textProducer = new TextField();
        textProducer.setPromptText("Paul Jaboulet Aine");
        final TextField textVintage = new TextField();
        textVintage.setPromptText("1961");
        final TextField textGrape = new TextField();
        textGrape.setPromptText("Syrah");
        final TextField textDate = new TextField();
        textDate.setPromptText("25/12/2016");
        final TextField textQuantity = new TextField();
        textQuantity.setPromptText("1");
        final VBox textFields = new VBox(10, textName, textProducer, 
                textVintage, textGrape, textDate, textQuantity);
        textFields.setPrefWidth(250);
        
        saveDisabled.bind(
                textName.textProperty().isEmpty()
                .or(textProducer.textProperty().isEmpty())
                .or(textVintage.textProperty().isEmpty())
                .or(textVintage.textProperty().lessThan(vintageLimit))
                .or(textGrape.textProperty().isEmpty())
                .or(textDate.textProperty().isEmpty())
                .or(textQuantity.textProperty().isEmpty()));
        
        // Init dialog
        final Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add New Wine");
        ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(textFields);
        final Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.disableProperty().bind(saveDisabled);
        saveButton.setOnAction((ActionEvent event) -> {
            
            final Wine wine = new WineImpl(textName.getText(),
                    textProducer.getText(),
                    Integer.parseInt(textVintage.getText()),
                    textGrape.getText());
            
            int id = 0;
            if (wines.contains(wine)) {
                id = wines.get(wines.indexOf(wine)).getId();
            } else {
                dao.insertWine(wine);
                wine.setId(dao.getId(wine));
                wines.add(wine);
            }
            
            final PurchaseRecord purchase = new PurchaseRecordImpl(wine, new BigDecimal(0), 
                    Integer.parseInt(textQuantity.getText()),
            BottleSize.BOTTLE, "", Date.valueOf(textDate.getText()));
            purchaseDao.addPurchase(purchase);
            System.out.println("Created new Wine " + wine.toString());
        });
        return dialog;
    }
    
    private TableView initTableView(final ObservableList<Wine> wines, final WineDao dao) {
        TableView table = new TableView();
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
        wines.addAll(dao.getAllWines());
        table.setItems(wines);
        return table;
    }
    
}

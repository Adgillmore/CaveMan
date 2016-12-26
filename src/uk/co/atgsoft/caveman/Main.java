/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman;

import java.io.IOException;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import uk.co.atgsoft.caveman.database.dao.PurchaseDao;
import uk.co.atgsoft.caveman.database.dao.PurchaseDaoImpl;
import uk.co.atgsoft.caveman.database.dao.WineDao;
import uk.co.atgsoft.caveman.database.dao.WineDaoImpl;
import uk.co.atgsoft.caveman.ui.WineDetailController;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineImpl;
import uk.co.atgsoft.caveman.wine.stock.StockRecord;
import uk.co.atgsoft.caveman.wine.stock.StockRecordImpl;

/**
 *
 * @author adam
 */
public class Main extends Application {
    
    private TableView table;
    
    private AnchorPane wineDetailDialog;
    
    private WineDetailController wineDetailController;
    
    private WineDao wineDao;
    
    private PurchaseDao purchaseDao;
    
    private ObservableList<Wine> wineList;
    
    private Dialog<Pair<String, String>> addWineDialog;
    
    private Dialog<Pair<String, String>> addStockDialog;
    
    private BooleanProperty saveDisabled = new SimpleBooleanProperty();
    
    private ObservableStringValue vintageLimit = new SimpleStringProperty("1850");
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        wineDao = new WineDaoImpl();
        purchaseDao = new PurchaseDaoImpl();
        wineList = FXCollections.observableArrayList();
        
        final FXMLLoader loader = new FXMLLoader();
        loader.load(getClass().getResourceAsStream("wine_detail.fxml"));
        wineDetailDialog = (AnchorPane) loader.getRoot();
        wineDetailController = loader.getController();
        table = initTableView(wineList, wineDao);
        
        addWineDialog = initDialog(wineList, wineDao);
        addStockDialog = initAddStockDialog(wineList);
        
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
                wineDetailController.setWine(new WineImpl());
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

        // Init dialog
        final Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add New Wine");
        ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(wineDetailDialog);
        final Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
//        saveButton.disableProperty().bind(saveDisabled);
        saveButton.setOnAction((ActionEvent event) -> {
            
            final Wine wine = wineDetailController.getWine();
            if (wine.getId() == null) {
                wine.setId(wine.getName() + ":" + wine.getProducer() + ":" + wine.getVintage());
                dao.insertWine(wine);
                wines.add(wine);
            } else {
                dao.updateWine(wine);
            }

            
//            final PurchaseRecord purchase = new PurchaseRecordImpl(wine, new BigDecimal(0), 
//                    Integer.parseInt(textQuantity.getText()),
//            BottleSize.BOTTLE, "", Date.valueOf(textDate.getText()));
//            purchaseDao.addPurchase(purchase);
//            System.out.println("Created new Wine " + wine.toString());
        });
        
        final Button addStockButton = new Button("Add Stock");
        addStockButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        return dialog;
    }
    
    private Dialog initAddStockDialog(final ObservableList<Wine> wines) {
        final TextField textQuantity = new TextField();
        textQuantity.setPromptText("6");
        
        final Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Stock");
        ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(textQuantity);
        final Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.setOnAction((ActionEvent event) -> {
            
            final StockRecord stock = new StockRecordImpl(wines.get(0));
        });
        return dialog;
        
    }
    
    private TableView initTableView(final ObservableList<Wine> wines, final WineDao dao) {
        TableView table = new TableView();
        TableColumn name = new TableColumn("Name");
        TableColumn producer = new TableColumn("Producer");
        TableColumn region = new TableColumn("Region");
        TableColumn country = new TableColumn("Country");
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
        region.setCellValueFactory(
        new PropertyValueFactory<>("Region")
        );
        country.setCellValueFactory(
        new PropertyValueFactory<>("Country")
        );
        
        table.getColumns().addAll(name, producer, region, country, vintage, grape);
        
        
        wines.addAll(dao.getAllWines());
        table.setItems(wines);
        table.setRowFactory(tv -> {
            final TableRow<Wine> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    wineDetailController.setWine(row.getItem());
                    addWineDialog.show();
                }
            });
            return row;
        });
        return table;
    }
    
}

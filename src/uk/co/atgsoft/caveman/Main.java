/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import uk.co.atgsoft.caveman.database.dao.PurchaseDao;
import uk.co.atgsoft.caveman.database.dao.PurchaseDaoImpl;
import uk.co.atgsoft.caveman.database.dao.StockDao;
import uk.co.atgsoft.caveman.database.dao.StockDaoImpl;
import uk.co.atgsoft.caveman.database.dao.WineDao;
import uk.co.atgsoft.caveman.database.dao.WineDaoImpl;
import uk.co.atgsoft.caveman.ui.CellarTableController;
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
    
    private AnchorPane wineDetailPane;
    
    private WineDetailController wineDetailController;
    
    private WineDao wineDao;
    
    private PurchaseDao purchaseDao;
    
    private StockDao stockDao;
    
    private ObservableList<StockRecord> wineList;
    
    private Dialog<Pair<String, String>> wineDetailDialog;
    
    private BooleanProperty saveDisabled = new SimpleBooleanProperty();
    
    private ObservableStringValue vintageLimit = new SimpleStringProperty("1850");
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        wineDao = new WineDaoImpl();
        purchaseDao = new PurchaseDaoImpl();
        stockDao = new StockDaoImpl();
        wineList = FXCollections.observableArrayList();
        wineList.addAll(stockDao.getAllStock());
        
        final FXMLLoader loader = new FXMLLoader();
        loader.load(WineDetailController.class.getResourceAsStream("wine_detail.fxml"));
        wineDetailPane = (AnchorPane) loader.getRoot();
        wineDetailController = loader.getController();
        table = initTableView(wineList);
        
        wineDetailDialog = initDialog(wineList, wineDao);
        
        final BorderPane root = new BorderPane(table, initToolbar(wineList, stockDao), 
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
    
    private Node initToolbar(final ObservableList<StockRecord> wines, final StockDao dao) {
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                wineDetailController.setStockRecord(new StockRecordImpl(new WineImpl()));
                wineDetailDialog.show();
            }
        });
        
        final Button removeButton = new Button("Remove");
        removeButton.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
        removeButton.setOnAction((ActionEvent event) -> {
            wines.remove(table.getSelectionModel().getFocusedIndex());
            dao.removeStock((StockRecord) table.getSelectionModel().getSelectedItem());
        });
        final HBox toolbar = new HBox(addButton, removeButton);
        return toolbar;
    }
    
    private Dialog initDialog(final ObservableList<StockRecord> stock, final WineDao dao) {

        // Init dialog
        final Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add New Wine");
        ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(wineDetailPane);
        final Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.setOnAction((ActionEvent event) -> {
            
            final Wine wine = wineDetailController.getWine();
            if (wine.getId() == null) {
                wine.setId(wine.getName() + ":" + wine.getProducer() + ":" + wine.getVintage());
                dao.insertWine(wine);
            } else {
                dao.updateWine(wine);
            }
            final StockRecord record = wineDetailController.getStockRecord();
            stockDao.addStock(record);
            stock.add(record);
        });
        return dialog;
    }
    
    private TableView initTableView(final ObservableList<StockRecord> wines) {
        final FXMLLoader loader = new FXMLLoader();
        try {
            table = loader.load(CellarTableController.class.getResourceAsStream("cellar_table.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final CellarTableController controller = loader.getController();
        controller.addAllStock(wines);
        table.setItems(wines);
        table.setRowFactory(callback -> {
            final TableRow<StockRecord> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    wineDetailController.setStockRecord(row.getItem());
                    wineDetailDialog.show();
                }
            });
            return row;
        });
        return table;
    }
    
}

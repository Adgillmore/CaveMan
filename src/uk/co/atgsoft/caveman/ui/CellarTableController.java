/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import uk.co.atgsoft.caveman.wine.stock.StockRecord;

/**
 * FXML Controller class
 *
 * @author adam.gillmore
 */
public class CellarTableController implements Initializable {

    @FXML private ObservableList<StockRecord> stock;
    
    @FXML private TableColumn<StockRecord, String> nameColumn;
    
    @FXML private TableColumn<StockRecord, String> producerColumn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new Callback<CellDataFeatures<StockRecord, String>, ObservableValue<String>>() {
            
            @Override
            public ObservableValue<String> call(final CellDataFeatures<StockRecord, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getWine().getName());
            }
        });
        
        producerColumn.setCellValueFactory(new Callback<CellDataFeatures<StockRecord, String>, ObservableValue<String>>() {
            
            @Override
            public ObservableValue<String> call(final CellDataFeatures<StockRecord, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getWine().getProducer());
            }
        });
    }    
    
    public ObservableList<StockRecord> getStock() {
        return stock;
    }
    
    public void addAllStock(ObservableList<StockRecord> stock) {
        stock.addAll(stock);
    }
}

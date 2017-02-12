/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.ui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import uk.co.atgsoft.caveman.wine.record.stock.StockRecord;

/**
 * FXML Controller class
 *
 * @author adam.gillmore
 */
public class CellarTableController {

    @FXML private TableColumn<StockRecord, String> nameColumn;
    
    @FXML private TableColumn<StockRecord, String> producerColumn;
    
    @FXML private TableColumn<StockRecord, String> regionColumn;
    
    @FXML private TableColumn<StockRecord, String> countryColumn;
    
    @FXML private TableColumn<StockRecord, Integer> vintageColumn;
    
    @FXML private TableColumn<StockRecord, String> grapeColumn;
    
    @FXML private TableColumn<StockRecord, Integer> quantityColumn;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory((final CellDataFeatures<StockRecord, String> param) 
                -> new ReadOnlyObjectWrapper<>(param.getValue().getWine().getName()));
        producerColumn.setCellValueFactory((final CellDataFeatures<StockRecord, String> param) 
                -> new ReadOnlyObjectWrapper<>(param.getValue().getWine().getProducer()));
        regionColumn.setCellValueFactory((final CellDataFeatures<StockRecord, String> param) 
                -> new ReadOnlyObjectWrapper<>(param.getValue().getWine().getRegion()));
        countryColumn.setCellValueFactory((final CellDataFeatures<StockRecord, String> param) 
                -> new ReadOnlyObjectWrapper<>(param.getValue().getWine().getCountry()));
        vintageColumn.setCellValueFactory((final CellDataFeatures<StockRecord, Integer> param) 
                -> new ReadOnlyObjectWrapper<>(param.getValue().getWine().getVintage()));
        grapeColumn.setCellValueFactory((final CellDataFeatures<StockRecord, String> param) 
                -> new ReadOnlyObjectWrapper<>(param.getValue().getWine().getGrape()));
        
        quantityColumn.setCellValueFactory((final CellDataFeatures<StockRecord, Integer> param) 
                -> new ReadOnlyObjectWrapper<>(param.getValue().getNumberOfBottles()));
    }    

}

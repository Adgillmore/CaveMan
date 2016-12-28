/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import uk.co.atgsoft.caveman.wine.Wine;

/**
 * FXML Controller class
 *
 * @author adam.gillmore
 */
public class CellarTableController implements Initializable {

//    @FXML private TableView cellarTable;
    
    @FXML private ObservableList<Wine> wines;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public ObservableList<Wine> getStock() {
        return wines;
    }
    
    public void addAllStock(ObservableList<Wine> stock) {
        wines.addAll(stock);
    }
}

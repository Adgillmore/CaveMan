/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseEntryImpl;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseEntry;

/**
 *
 * @author adam.gillmore
 */
public class AddPurchaseController {
    
    @FXML private TextField vendorText;
    
    @FXML private DatePicker datePicker;
    
    @FXML private TextField priceText;
    
    @FXML private ComboBox<BottleSize> sizePicker;
    
    @FXML private TextField quantityText;
    
    private BooleanProperty validInput;

    public AddPurchaseController() {
        validInput = new SimpleBooleanProperty();
    }
    
    @FXML
    private void initialize() {
        validInput.bind(vendorText.textProperty().isNotEmpty()
            .and(datePicker.valueProperty().isNotNull())
            .and(priceText.textProperty().isNotNull())
            .and(sizePicker.valueProperty().isNotNull())
            .and(quantityText.textProperty().isNotEmpty()));
        
        datePicker.setValue(LocalDate.now());
        sizePicker.setItems(FXCollections.observableArrayList(BottleSize.values()));
        sizePicker.getSelectionModel().select(BottleSize.STANDARD);
        quantityText.setText("1");
    }
    
    PurchaseEntry getPurchase(final Wine wine) {
        if (wine == null) {
            throw new IllegalArgumentException("Wine cannot be null");
        }
        return new PurchaseEntryImpl(null, wine, new BigDecimal(Float.parseFloat(priceText.getText())), 
                Integer.parseInt(quantityText.getText()), sizePicker.getValue(), vendorText.getText(), 
                datePicker.getValue());
    }
    
    BooleanProperty validInputProperty() {
        return validInput;
    }
}

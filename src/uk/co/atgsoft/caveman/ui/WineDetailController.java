/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.ui;

import java.math.BigDecimal;
import java.util.Map.Entry;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.stock.StockEntry;
import uk.co.atgsoft.caveman.wine.stock.StockRecord;

/**
 *
 * @author adam.gillmore
 */
public class WineDetailController {
    
    @FXML private TextField nameText;
    
    @FXML private TextField producerText;
    
    @FXML private TextField regionText;
    
    @FXML private TextField countryText;
    
    @FXML private TextField grapesText;
    
    @FXML private TextField vintageText;
    
    @FXML private TextField alcoholText;
    
    @FXML private TextField colourText;
    
    @FXML private TextField priceText;
    
    @FXML private TableColumn<StockEntry, BottleSize> sizeColumn;
    
    @FXML private TableColumn<StockEntry, Integer> quantityColumn;
    
    @FXML private TableView<StockEntry> stockTable;
    
    private Wine mWine;
    
    private StockRecord mStock;
    
    @FXML
    private void initialize() {
        nameText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null) return;
                mWine.setName(newValue);
            }
        });
        
        producerText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null) return;
                mWine.setProducer(newValue);
            }
        });
        
        regionText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null) return;
                mWine.setRegion(newValue);
            }
        });
        
        countryText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null) return;
                mWine.setCountry(newValue);
            }
        });
        
        grapesText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null) return;
                mWine.setGrape(newValue);
            }
        });
        
        vintageText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null) return;
                try {
                    mWine.setVintage(Integer.parseInt(newValue));
                } catch (NumberFormatException ex) {
                    
                }
                
            }
        });
        
        priceText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null) return;
                mWine.setPrice(new BigDecimal(newValue));
            }
        });
        
        alcoholText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null) return;
                mWine.setAlcohol(Float.parseFloat(newValue));
            }
        });
        
        colourText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null) return;
                try {
                    mWine.setColour(WineColour.valueOf(newValue.toUpperCase()));
                } catch (final IllegalArgumentException ex) {
                    // no op.
                }
                
            }
        });
        
        sizeColumn.setCellValueFactory(new Callback<CellDataFeatures<StockEntry, BottleSize>, ObservableValue<BottleSize>>() {
            @Override
            public ObservableValue<BottleSize> call(final CellDataFeatures<StockEntry, BottleSize> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getBottleSize()); // can change this if using javafx properties
            }
        });
        
        quantityColumn.setCellValueFactory(new Callback<CellDataFeatures<StockEntry, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(final CellDataFeatures<StockEntry, Integer> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getQuantity()); // can change this if using javafx properties
            }
        });
        
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            
            @Override
            public String toString(final Integer object) {
                return Integer.toString(object);
            }

            @Override
            public Integer fromString(final String string) {
                int number = 0;
                try {
                    number = Integer.parseInt(string);
                } catch (NumberFormatException ex) {
                    //
                }
                return number;
            }
        }));
        sizeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(BottleSize.values())));
        
        quantityColumn.setEditable(true);
        stockTable.setEditable(true);
    }
    
    public void setWine(final Wine wine) {
        if (wine == null) {
            throw new IllegalArgumentException("Wine cannot be null");
        }
        mWine = null;
        nameText.setText(wine.getName());
        producerText.setText(wine.getProducer());
        regionText.setText(wine.getRegion());
        countryText.setText(wine.getCountry());
        vintageText.setText(Integer.toString(wine.getVintage()));
        alcoholText.setText(Float.toString(wine.getAlcohol()));
        if (wine.getWineColour() != null) colourText.setText(wine.getWineColour().toString());
        if (wine.getPrice() != null) priceText.setText(wine.getPrice().toString());
        grapesText.setText(wine.getGrape());
        mWine = wine;
    }
    
    public Wine getWine() {
        return mWine;
    }
    
    public void setStockRecord(StockRecord stock) {
        mStock = stock;
        stockTable.getItems().clear();
        for (Entry<BottleSize, StockEntry> entry : stock.getStock().entrySet()) {
            stockTable.getItems().add(entry.getValue());
        }
        
        setWine(stock.getWine());
    }
    
    public StockRecord getStockRecord() {
        return mStock;
    }
}

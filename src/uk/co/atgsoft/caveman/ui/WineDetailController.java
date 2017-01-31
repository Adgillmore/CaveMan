/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.ui;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Pair;
import javafx.util.StringConverter;
import uk.co.atgsoft.caveman.Main;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.WineStyle;
import uk.co.atgsoft.caveman.wine.purchase.PurchaseRecord;

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
    
    @FXML private ComboBox<WineStyle> styleText;
    
    @FXML private Button addStockButton;
    
    @FXML private Button depleteStockButton;
    
    @FXML private TableColumn<PurchaseRecord, BottleSize> sizeColumn;
    
    @FXML private TableColumn<PurchaseRecord, Integer> quantityColumn;
    
    @FXML private TableView<PurchaseRecord> purchaseTable;
    
    private Dialog<Pair<String, String>> purchaseDialog;
    
    private Wine mWine;
    
    @FXML
    private void initialize() {
        initTextBoxes();
        initPurchaseTable();
        initPurchaseDialog();
        initStockButtons();
    }
    
    private void initTextBoxes() {
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
                if (mWine == null || newValue.isEmpty()) return;
                try {
                    mWine.setVintage(Integer.parseInt(newValue));
                } catch (NumberFormatException ex) {
                    
                }
                
            }
        });
        
        
        
        alcoholText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null || newValue.isEmpty()) return;
                mWine.setAlcohol(Float.parseFloat(newValue));
            }
        });
        
        colourText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (mWine == null || newValue.isEmpty()) return;
                try {
                    mWine.setColour(WineColour.valueOf(newValue.toUpperCase()));
                } catch (final IllegalArgumentException ex) {
                    // no op.
                }
                
            }
        });
        
        styleText.itemsProperty().set(FXCollections.observableArrayList(WineStyle.values()));
        styleText.valueProperty().addListener(new ChangeListener<WineStyle>() {
            @Override
            public void changed(ObservableValue<? extends WineStyle> observable, WineStyle oldValue, WineStyle newValue) {
                if (mWine != null) mWine.setStyle(newValue);
            }
        });
    }
    
    private void initPurchaseTable() {
        sizeColumn.setCellValueFactory(new Callback<CellDataFeatures<PurchaseRecord, BottleSize>, ObservableValue<BottleSize>>() {
            @Override
            public ObservableValue<BottleSize> call(final CellDataFeatures<PurchaseRecord, BottleSize> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getBottleSize()); // can change this if using javafx properties
            }
        });
        
        quantityColumn.setCellValueFactory(new Callback<CellDataFeatures<PurchaseRecord, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(final CellDataFeatures<PurchaseRecord, Integer> param) {
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
        purchaseTable.setEditable(true);
    }
    
    private Dialog<Pair<String, String>> initPurchaseDialog() {
        Pane dialogPane = null;
        final FXMLLoader loader = new FXMLLoader();
        try {
            dialogPane = loader.load(AddPurchaseController.class.getResourceAsStream("add_purchase.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final AddPurchaseController controller = loader.getController();
        purchaseDialog = new Dialog<>();
        purchaseDialog.setTitle("Add a purchase");
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        purchaseDialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        purchaseDialog.getDialogPane().setContent(dialogPane);
        final Button saveButton = (Button) purchaseDialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.setOnAction((ActionEvent event) -> {
            purchaseTable.getItems().add(controller.getPurchase(mWine));
            mWine.setPrice(controller.getPurchase(mWine).getPrice());
        });
        saveButton.disableProperty().bind(controller.validInputProperty().not());
        return purchaseDialog;
    }
    
    private void initStockButtons() {
        addStockButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                purchaseDialog.show();
            }
        });
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
        colourText.setText(wine.getWineColour() == null ? "" : wine.getWineColour().toString());
//        priceText.setText(wine.getPrice() == null ? "" : wine.getPrice().toString());
        styleText.getSelectionModel().select(wine.getStyle());
        grapesText.setText(wine.getGrape());
        mWine = wine;
    }
    
    public Wine getWine() {
        return mWine;
    }

    public void setPurchaseRecords(final List<PurchaseRecord> records) {
        if (records == null) {
            throw new IllegalArgumentException("Records cannot be null");
        }
        final ObservableList<PurchaseRecord> list = purchaseTable.getItems();
        list.clear();
        list.addAll(records);
    }
    
    public List<PurchaseRecord> getPurchaseRecords() {
        return purchaseTable.getItems();
    }
    
    private void reset() {
        
    }
}

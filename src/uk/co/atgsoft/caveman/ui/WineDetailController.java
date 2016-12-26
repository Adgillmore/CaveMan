/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.ui;

import java.math.BigDecimal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineColour;

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
    
    private Wine mWine;
    
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
                mWine.setVintage(Integer.parseInt(newValue));
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
        
    }
    
    public void setWine(final Wine wine) {
        if (wine == null) {
            throw new IllegalArgumentException("Wine cannot be null");
        }
        
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
}

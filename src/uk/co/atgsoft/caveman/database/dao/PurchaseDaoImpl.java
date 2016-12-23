/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.util.List;
import uk.co.atgsoft.caveman.database.DatabaseUtils;
import uk.co.atgsoft.caveman.wine.purchase.PurchaseRecord;

/**
 *
 * @author adam.gillmore
 */
public class PurchaseDaoImpl implements PurchaseDao {

    public PurchaseDaoImpl() {
        DatabaseUtils.createTable("CREATE TABLE IF NOT EXISTS PURCHASE " +
           "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
           " WINE_ID INTEGER, " +
           " PRICE INTEGER    NOT NULL, " + 
           " DATE TEXT    NOT NULL, " +
           " VENDOR TEXT NOT NULL, " +
           " QUANTITY INTEGER, " + 
           " SIZE TEXT, " + 
           " FOREIGN KEY(WINE_ID) REFERENCES WINE(ID))");
    }

    
    
    @Override
    public void addPurchase(PurchaseRecord purchase) {
        DatabaseUtils.executeStatement(
        "INSERT INTO PURCHASE (WINE_ID, PRICE, DATE, QUANTITY, VENDOR, SIZE)"
                + "VALUES ("
                + "'" + purchase.getWine().getId() + "', "
                + "'" + purchase.getPrice() + "', "
                + "'" + purchase.getDate() + "', "
                + "'" + purchase.getQuantity() + "', "
                + "'" + purchase.getVendor() + "', "
                + "'" + purchase.getBottleSize() + "');");
    }

    @Override
    public void removePurchase(PurchaseRecord purchase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePurchase(PurchaseRecord purchase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PurchaseRecord> getAllPurchases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

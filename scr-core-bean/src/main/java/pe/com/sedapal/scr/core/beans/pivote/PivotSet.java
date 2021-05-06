/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans.pivote;

import java.util.List;


/**
 * The Class PivotSet.
 */
public class PivotSet {
    
    /** The item. */
    private List<Item> item;

    /**
     * Gets the item.
     *
     * @return the item
     */
    public List<Item> getItem() {
        return item;
    }
    
    /**
     * Sets the item.
     *
     * @param item the new item
     */
    public void setItem(List<Item> item) {
        this.item = item;
    }
}
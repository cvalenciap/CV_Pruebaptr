/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans.pivote;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;


/**
 * The Class Column.
 */
public class Column {
	
	/** The name. */
	private String name;
    
    /** The value. */
    private String value;

    /**
     * Gets the name.
     *
     * @return the name
     */
    @XmlAttribute
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the value.
     *
     * @return the value
     */
    @XmlValue
    public String getValue() {
        return value;
    }
    
    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }
}

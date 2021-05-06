package pe.com.sedapal.common.core.beans;

import java.io.Serializable;

public class SelectItemBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 137498342657462193L;
	
	private String value;
	private String label;
	private String selected="";
	
	public SelectItemBean() {
		super();
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}

}

package com.sogeti.rental.ui.views;

import org.eclipse.swt.SWT;

public interface RentalUIConstants {
	public static final String OBJETS_A_LOUER = "Rental Objects"; 
	public static final String LOCATIONS = "Rental"; 
	public static final String CUSTOMERS = "Customers";
	
	public static final String AGENCY = "AGENCY";
	public static final String USER = "USER";
	

	public static final String PREF_OBJECT_COLOR = "OBJECT_COLOR";
	public static final String PREF_RENTAL_COLOR = "RENTAL_COLOR";
	public static final String PREF_CUSTOMER_COLOR = "CUSTOMER_COLOR";

	
	public enum IMG {
		AGENCGY_NODE("icons/Agency.png", AGENCY, SWT.COLOR_BLACK),
		USER_IMG("icons/User.png", USER),
		
		RENTAL_OBJECTS_NODE("icons/RentalObjects.png", OBJETS_A_LOUER, SWT.COLOR_BLUE), 
		RENTALS_NODE("icons/Rentals.png", LOCATIONS, SWT.COLOR_BLUE),
		CUSTOMERS_NODE("icons/Customers.png", CUSTOMERS, SWT.COLOR_BLUE)
		
		, ICON_EXPAND("icons/expand_all.png", "+")
		, ICON_COLLAPSE("icons/collapse_all.png", "-")
		;
		private String _url;
		private String _label;
		private int _color = SWT.COLOR_BLACK;
		private IMG(String text, String label) { _url = text; _label=label; }
		private IMG(String text, String label, int c) { 
			_url = text;
			_label=label; 
		    _color = c;
		}
		
		public String path() { return _url; } 
		public String label() { return _label; }
		
		public static IMG getImg(String key) { 
			for ( IMG img : IMG.values()) {
				if (img._label.equals(key))
					return img;
			}
			return null;
		}
		public int getColor() { 
			return _color;
		}
	}
}

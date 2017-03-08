package com.sogeti.rental.ui.views;

 

public interface RentalUIConstants {
	public static final String OBJETS_À_LOUER = "Objets à louer"; 
	public static final String LOCATIONS = "RENTAL"; 
	public static final String CUSTOMERS = "CUSTOMERS";
	
	public enum IMG {
		AGENCGY_IMG("icons/Agency.png", "AGENCY"),
		USER_IMG("icons/User.png", "USER"),
		ICONS_RENTAL_OBJECTS_PNG("icons/RentalObjects.png", OBJETS_À_LOUER), 
		ICONS_RENTALS_PNG("icons/Rentals.png", "LOCATIONS"),
		ICONS_CUSTOMERS_PNG("icons/Customers.png", CUSTOMERS)
		
		, ICONS_EXPAND("icons/expand_all.png", "+")
		, ICONS_COLLAPSE("icons/collapse_all.png", "-")
		;
		private String _url;
		private String _label;
		private IMG(String text, String label) { _url = text; _label=label; }
		
		public String path() { return _url; }  
		
		public static IMG getImg(String key) { 
			for ( IMG img : IMG.values()) {
				if (img._label.equals(key))
					return img;
			}
			return null;
		}
	}
}

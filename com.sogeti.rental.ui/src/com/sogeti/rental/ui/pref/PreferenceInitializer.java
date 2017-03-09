package com.sogeti.rental.ui.pref;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.sogeti.rental.ui.RentalUIActivator;
import com.sogeti.rental.ui.views.RentalUIConstants;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer implements RentalUIConstants {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
//		store.setDefault(PreferenceConstants.P_BOOLEAN, true);
//		store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
//		store.setDefault(PreferenceConstants.P_STRING, "Default value");
		
		store.setDefault(RentalUIConstants.PREF_CUSTOMER_COLOR, StringConverter.asString(new RGB(0, 153, 0)));
		store.setDefault(RentalUIConstants.PREF_OBJECT_COLOR, StringConverter.asString(new RGB(255, 0, 0)));
		store.setDefault(RentalUIConstants.PREF_RENTAL_COLOR, StringConverter.asString(new RGB(0, 0, 153)));
	}

}

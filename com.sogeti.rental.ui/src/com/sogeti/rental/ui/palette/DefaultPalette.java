package com.sogeti.rental.ui.palette;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.RentalUIActivator;
import com.sogeti.rental.ui.views.RentalProvider.Node;
import com.sogeti.rental.ui.views.RentalUIConstants;

public class DefaultPalette implements IColorProvider, RentalUIConstants {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) { 
		
		if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(IMG.AGENCGY_NODE.getColor());
		} 
		
		if (element instanceof Node) {
			return Display.getCurrent().getSystemColor(((Node)element).getColor());
		} 
 
		String sColor = null;
		
		if (element instanceof Customer) {
			sColor = RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_CUSTOMER_COLOR);			
		}
		
		else if (element instanceof RentalObject) { 
			sColor = RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_OBJECT_COLOR);
		}
		
		else if (element instanceof Rental) { 
			sColor = RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_RENTAL_COLOR);
		}
		
		if (sColor!=null)
			return getAColor(sColor); 
		
		return Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);	
	}

	@Override
	public Color getBackground(Object element) { 
		return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
	}

	/**
	 * 
	 * @param rgbKey
	 * @return
	 */
	public static Color getAColor(String rgbKey) {
		ColorRegistry cr = JFaceResources.getColorRegistry();
		Color col = cr.get(rgbKey);
		if (col == null) {
			cr.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = cr.get(rgbKey);
		}
		
		return col; 
	}


}

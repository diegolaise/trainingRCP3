package com.sogeti.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.RentalUIActivator; 

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstants {

	private static final Object[] EMPTY_RESULT = new  Object[0]; 
 
	private class Node {
		private RentalAgency _agency;
		private String 		_label;
		private IMG 		_img;
		//private Color       _color;
		
		public Node(RentalAgency ag, String lbl) {
			_agency = ag;
			_label  = lbl; 
			_img    = IMG.getImg(_label);
		} 
		
		public IMG getImage() {
			return _img;
		}
		
		public int getColor() {
			return _img.getColor();
		}
		
		public Object[] getChildren() {
			if ( CUSTOMERS.equals(_label) )
				return _agency.getCustomers().toArray();
			
			if ( LOCATIONS.equals(_label) )
				return _agency.getRentals().toArray();
			
			if ( OBJETS_A_LOUER.equals(_label) )
				return _agency.getObjectsToRent().toArray();
			
			return EMPTY_RESULT;
		}

		public String getLabel() { 
			return _label;
		} 
	};


	@Override
	public Object[] getElements(Object inputElement) {

		if (inputElement instanceof Collection<?>) {
			return ((Collection<?>) inputElement).toArray();
		}
		return EMPTY_RESULT;
	}

	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof RentalAgency) {
			RentalAgency ag  = (RentalAgency) parentElement;
			return new Node[] { new Node(ag,  CUSTOMERS)
						, new Node(ag,  LOCATIONS) 
						, new Node(ag,  OBJETS_A_LOUER)
						};  
		} 
		
		if (parentElement instanceof Node) {
			return ((Node)parentElement).getChildren(); 
		} 

		return EMPTY_RESULT;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return ( (element instanceof Node) || (element instanceof RentalAgency));
	}

	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		}

		if (element instanceof Customer) {
			return ((Customer)element).getDisplayName();
		}
		
		if (element instanceof RentalObject) {
			return ((RentalObject)element).getName();
		}
		
		if (element instanceof Node)
			return ((Node)element).getLabel();

		return element.toString();
	}
	

	
	/**
	 * The <code>LabelProvider</code> implementation of this
	 * <code>ILabelProvider</code> method returns <code>null</code>.
	 * Subclasses may override.
	 */
	@Override
	public Image getImage(Object element) {
		IMG path = null;
		if (element instanceof RentalAgency) {
			path = IMG.AGENCGY_IMG;
		}
		
		else if (element instanceof Node) {
			path =  ((Node)element).getImage();
		} 
		
		else if (element instanceof Customer) {
			path =  IMG.USER_IMG ;
		}
	
		if (path==null) return null;
		
		return RentalUIActivator.getDefault().getImageRegistry().get(path.name()); 
	}

	@Override
	public Color getForeground(Object element) { 
		
		if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		}
		
		String sColor = null;
		
		if (element instanceof Node) {
			return Display.getCurrent().getSystemColor(((Node)element).getColor());
		} 
 
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
		// TODO Auto-generated method stub
		return null;
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

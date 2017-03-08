package com.sogeti.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.Activator;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	private static final String OBJETS_À_LOUER = "Objets à louer"; 
	private static final String LOCATIONS = "Locations"; 
	private static final String CUSTOMERS = "Customers";

	private static final Object[] EMPTY_RESULT = new  Object[0];
 
	
	private class Node {
		private RentalAgency _agency;
		private String 		_label;
		private String 		_img;
		
		public Node(RentalAgency ag, String lbl, String img) {
			_agency = ag;
			_label = lbl;
			_img = img;
		}
		
		public String getImage() {
			return _img;
		}
		
		public Object[] getChildren() {
			if (CUSTOMERS.equals(_label) )
				return _agency.getCustomers().toArray();
			
			if (LOCATIONS.equals(_label) )
				return _agency.getRentals().toArray();
			
			if (OBJETS_À_LOUER.equals(_label) )
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
			return new Node[] { new Node(ag, CUSTOMERS, Activator.ICONS_CUSTOMERS_PNG)
						, new Node(ag, LOCATIONS, Activator.ICONS_RENTALS_PNG)
						, new Node(ag, OBJETS_À_LOUER, Activator.ICONS_RENTAL_OBJECTS_PNG)};  
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
		if (element instanceof RentalAgency) {
			return Activator.getDefault().getImageRegistry().get( Activator.AGENCGY_IMG );
		}
		
		if (element instanceof Node) {
			return Activator.getDefault().getImageRegistry().get( ((Node)element).getImage());
		} 
		
		if (element instanceof Customer) {
			return Activator.getDefault().getImageRegistry().get( Activator.USER_IMG );
		}
	
		return null; 
	}

	@Override
	public Color getForeground(Object element) { 
		
		if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		}
//		
//		if (element instanceof Node) {
//			return Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
//		} 
 
		if (element instanceof Customer) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		}
		
		return Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
	
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}


}

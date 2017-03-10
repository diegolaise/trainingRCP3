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
 
	/**
	 * 
	 * @author mueloi
	 *
	 */
	public class Node {
		private RentalAgency _agency;
		private String 		_label;
		private IMG 		_img; 
		
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((_agency == null) ? 0 : _agency.hashCode());
			result = prime * result + ((_img == null) ? 0 : _img.hashCode());
			result = prime * result + ((_label == null) ? 0 : _label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (_agency == null) {
				if (other._agency != null)
					return false;
			} else if (!_agency.equals(other._agency))
				return false;
			if (_img != other._img)
				return false;
			if (_label == null) {
				if (other._label != null)
					return false;
			} else if (!_label.equals(other._label))
				return false;
			return true;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
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
			path = IMG.AGENCGY_NODE;
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

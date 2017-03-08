package com.sogeti.rental.ui.views;

import java.util.Arrays;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalFactory;
import com.sogeti.rental.core.RentalCoreActivator;

public class AgencyView extends ViewPart {

	public AgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		TreeViewer tree = new TreeViewer(parent);
		
		RentalAgency a1 = RentalCoreActivator.getAgency();
		
		RentalProvider provider = new RentalProvider();
		tree.setContentProvider(provider);
		tree.setLabelProvider(provider);
		
		//RentalAgency a2 = RentalAgencyGenerator.createSampleAgency(); 
		RentalAgency a2 = RentalFactory.eINSTANCE.createRentalAgency();
		a2.setName("Lyon");
		Customer c = RentalFactory.eINSTANCE.createCustomer();
		c.setFirstName("Toto");
		c.setLastName("Name");
		a2.getCustomers().add(c);
		 
		tree.setInput(Arrays.asList(new RentalAgency[] {a1, a2}));	
		
		tree.expandAll();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}

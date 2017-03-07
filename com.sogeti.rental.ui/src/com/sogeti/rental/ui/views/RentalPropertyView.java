package com.sogeti.rental.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalPropertyView extends ViewPart {

	Label rentedObjectLabel;
	Label tName;
	
	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		Label rentedObjectLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		GridData gd2 = new GridData(); 
		gd2.horizontalAlignment = SWT.FILL;
		gd2.horizontalSpan = 1;
		Label label = new Label(infoGroup, SWT.BORDER);
		label.setText("Lou� �");
		label.setLayoutData(gd2);
		 
		GridData gd3 = new GridData(); 
		gd3.horizontalAlignment = SWT.FILL;
		gd3.horizontalSpan = 1;
		tName = new Label(infoGroup, SWT.BORDER);
		tName.setText("");
		label.setLayoutData(gd3);
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
		
	}

	private void setRental(Rental rental) {
		// TODO Auto-generated method stub
		rentedObjectLabel.setText(rental.getRentedObject().getName());
		tName.setText(rental.getCustomer().getDisplayName());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}

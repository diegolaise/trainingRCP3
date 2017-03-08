package com.sogeti.rental.ui.views;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalPropertyView extends ViewPart implements ISelectionListener {

	Label rentedObjectLabel;
	Label tName;
	private GridData gd_1;
	private Group grpDatesDeLocation;
	private Label lblDu;
	private Label dateDebut;
	private Label lblAu;
	private Label dateFin;
	
	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		GridData gd_infoGroup = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_infoGroup.widthHint = 396;
		infoGroup.setLayoutData(gd_infoGroup);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.widthHint = 379;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		gd = new GridData(); 
		Label label = new Label(infoGroup, SWT.NONE);
		gd.horizontalAlignment = SWT.FILL;
		label.setText("Loué à");
		label.setLayoutData(gd);
		 
		gd_1 = new GridData(); 
		gd_1.widthHint = 127;
		gd_1.horizontalAlignment = SWT.FILL;
		tName = new Label(infoGroup, SWT.NONE);
		tName.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		tName.setText("");
		label.setLayoutData(gd_1);
		new Label(parent, SWT.NONE);
		
		grpDatesDeLocation = new Group(parent, SWT.NONE);
		GridData gd_grpDatesDeLocation = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_grpDatesDeLocation.widthHint = 402;
		grpDatesDeLocation.setLayoutData(gd_grpDatesDeLocation);
		grpDatesDeLocation.setText("Dates de location");
		grpDatesDeLocation.setLayout(new GridLayout(2, false));
		
		lblDu = new Label(grpDatesDeLocation, SWT.NONE);
		GridData gd_lblDu = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblDu.widthHint = 132;
		lblDu.setLayoutData(gd_lblDu);
		lblDu.setText("du :");
		
		dateDebut = new Label(grpDatesDeLocation, SWT.NONE);
		dateDebut.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false, 1, 1));
		dateDebut.setText("date de debut");
		
		lblAu = new Label(grpDatesDeLocation, SWT.NONE);
		lblAu.setText("au :");
		
		dateFin = new Label(grpDatesDeLocation, SWT.NONE);
		dateFin.setText("Date de fin");
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
		
	}

	private void setRental(Rental rental) {
		rentedObjectLabel.setText(rental.getRentedObject().getName());
		tName.setText(rental.getCustomer().getDisplayName());
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		dateDebut.setText(formatter.format(rental.getStartDate()));
		dateFin.setText(formatter.format(rental.getEndDate()));
		
	}
	
	public void init(IViewSite site) throws PartInitException{ 
		super.init(site); 
		site.getPage().addSelectionListener(this);
	}
	
	public void dispose()  {  
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object selected  = ((IStructuredSelection)selection).getFirstElement();
			if (selected instanceof Rental) { 
				setRental((Rental)selected);
			}
		}
		
	}

}

package com.sogeti.rental.ui.views;

import java.util.Arrays;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalFactory;
import com.sogeti.rental.core.RentalCoreActivator;
import com.sogeti.rental.ui.RentalUIActivator; 

public class AgencyView extends ViewPart implements IPropertyChangeListener {

	TreeViewer _treeView;
			
	public AgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite.setLayout(new GridLayout(2, false));
		
		Button button = new Button(composite, SWT.CENTER);
		button.setText("+");
		//button.setImage( Activator.getDefault().getImageRegistry().get( IMG.ICONS_EXPAND.name()));
		button.setToolTipText("Expand All");
		
		Button button_1 = new Button(composite, SWT.CENTER);
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 21;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("-");
		//button_1.setImage( Activator.getDefault().getImageRegistry().get(IMG.ICONS_COLLAPSE.name()) );
		button_1.setToolTipText("Collapse All");
				
		_treeView = new TreeViewer(parent);
		Tree tree_1 = _treeView.getTree();
		tree_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		RentalAgency a1 = RentalCoreActivator.getAgency();
		
		RentalProvider provider = new RentalProvider();
		_treeView.setContentProvider(provider);
		_treeView.setLabelProvider(provider);
		
		//RentalAgency a2 = RentalAgencyGenerator.createSampleAgency(); 
		RentalAgency a2 = RentalFactory.eINSTANCE.createRentalAgency();
		a2.setName("Lyon");
		Customer c = RentalFactory.eINSTANCE.createCustomer();
		c.setFirstName("Toto");
		c.setLastName("Name");
		a2.getCustomers().add(c);
		 
		_treeView.setInput(Arrays.asList(new RentalAgency[] {a1, a2}));		
		_treeView.expandAll();
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_treeView.expandAll();
			}
		});
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_treeView.collapseAll();
			}
		});
		
		//Set provider to this
		getSite().setSelectionProvider(_treeView);
	}
	
	public void init(IViewSite site) throws PartInitException{ 
		super.init(site);  
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}
	
	public void dispose()  {  
		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		_treeView.refresh();
	} 
}

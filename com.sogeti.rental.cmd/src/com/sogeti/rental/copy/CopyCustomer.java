package com.sogeti.rental.copy;

import org.eclipse.swt.dnd.*;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.opcoach.training.rental.Customer;

public class CopyCustomer extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("Copy Customer");
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		
		if (currentSelection instanceof IStructuredSelection) {
			Object sel = ((IStructuredSelection)currentSelection).getFirstElement();
	
			if (sel instanceof Customer) { 
			 	Clipboard clipboard = new Clipboard(Display.getCurrent());
				String textData = ((Customer)sel).getDisplayName();
				String rtfData = "{\\rtf1\\b\\i "+textData+"}";
				TextTransfer textTransfer = TextTransfer.getInstance();
				RTFTransfer rtfTransfer = RTFTransfer.getInstance();
				Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer};
				Object[] data = new Object[]{textData, rtfData};
				clipboard.setContents(data, transfers);
				clipboard.dispose();
			}
		}
		
		return null;
	}

}

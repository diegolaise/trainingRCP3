package com.sogeti.rental.ui.palette;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.sogeti.rental.ui.views.RentalUIConstants;

public class DaltonienPalette implements IColorProvider, RentalUIConstants{

	public DaltonienPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) { 
		return Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
	}

	@Override
	public Color getBackground(Object element) { 
		return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE);
	}

}

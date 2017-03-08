package com.sogeti.rental.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.sogeti.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	

	public static final String AGENCGY_IMG = "icons/Agency.png"; 
	public static final String USER_IMG = "icons/User.png"; 
	public static final String ICONS_RENTAL_OBJECTS_PNG = "icons/RentalObjects.png"; 
	public static final String ICONS_RENTALS_PNG = "icons/Rentals.png"; 
	public static final String ICONS_CUSTOMERS_PNG = "icons/Customers.png";

	public enum IMG {
		AGENCGY_IMG("icons/Agency.png"),
		USER_IMG("icons/User.png"),
		ICONS_RENTAL_OBJECTS_PNG("icons/RentalObjects.png"), 
		ICONS_RENTALS_PNG("icons/Rentals.png"),
		ICONS_CUSTOMERS_PNG("icons/Customers.png")
		;
		private String _foldername;
		private IMG(String text) {
			_foldername = text;
		}
		public String path() { return _foldername; }  
//		@Override
//		public String toString() {
//			return _foldername;
//		}
	}
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	@Override
    protected void initializeImageRegistry(ImageRegistry reg) {
        Bundle b = FrameworkUtil.getBundle(this.getClass());
        reg.put(AGENCGY_IMG, ImageDescriptor.createFromURL(b.getEntry(AGENCGY_IMG)));
        reg.put(USER_IMG, ImageDescriptor.createFromURL(b.getEntry(USER_IMG)));
        reg.put(ICONS_RENTAL_OBJECTS_PNG, ImageDescriptor.createFromURL(b.getEntry(ICONS_RENTAL_OBJECTS_PNG))); 
	    reg.put(ICONS_RENTALS_PNG, ImageDescriptor.createFromURL(b.getEntry(ICONS_RENTALS_PNG)));
	    reg.put(ICONS_CUSTOMERS_PNG, ImageDescriptor.createFromURL(b.getEntry(ICONS_CUSTOMERS_PNG)));
	    
//	    for (IMG img : IMG.values()) {
//	    	 reg.put(img.name(), ImageDescriptor.createFromURL(b.getEntry(img.path())));
//	    }
    }

	
}

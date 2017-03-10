package com.sogeti.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.sogeti.rental.ui.views.RentalUIConstants;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin implements RentalUIConstants {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.sogeti.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;

	// The palette Manager
	private Map<String, Palette> paletteManager = new HashMap<String, Palette>();

	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		// readDriverExtension();
		readPaletteExtension();
	}

	/**
	 * Read palettes extensions
	 */
	private void readPaletteExtension() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement e : reg.getConfigurationElementsFor("com.sogeti.rental.ui.palette")) {
			try {
				Palette p = new Palette(e.getAttribute("id")
										, e.getAttribute("name")
										, (IColorProvider) e.createExecutableExtension("paletteClass"));
				paletteManager.put(e.getAttribute("id"), p);
			} 
			catch (CoreException e1) {
				e1.printStackTrace();
			} 
		}
		System.out.println("All palettes extensions: " + paletteManager);
	}

	// private void readDriverExtension() {
	// IExtensionRegistry reg = Platform.getExtensionRegistry();
	// for (IConfigurationElement e :
	// reg.getConfigurationElementsFor("org.eclipse.ui.views")) {
	// if (e.getName().equals("view")) {
	// System.out.println("PlugIn: " + e.getNamespaceIdentifier()
	// + "\tview: " + e.getAttribute("name"));
	// }
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
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
	public static RentalUIActivator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 *
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {

		// platform:/plugin/org.eclipse.wst.xmleditor.doc.user/images/doctype.gif
		Bundle b = FrameworkUtil.getBundle(this.getClass());
		for (IMG img : IMG.values()) {
			reg.put(img.name(), ImageDescriptor.createFromURL(b.getEntry(img.path())));
		}
	}

	public Map<String, Palette> getPaletteManager() { 
		return paletteManager;
	}

}

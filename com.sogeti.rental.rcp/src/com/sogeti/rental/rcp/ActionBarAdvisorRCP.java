package com.sogeti.rental.rcp;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.actions.ActionFactory;

public class ActionBarAdvisorRCP extends ActionBarAdvisor {
	private IAction preferencesAction;
	private IAction quitAction;

	public ActionBarAdvisorRCP(IActionBarConfigurer configurer) {
		super(configurer);
		
	}

	 @Override
    protected void fillMenuBar(IMenuManager menuBar) {
	 	
	 	MenuManager menuFileManager = new MenuManager("Menu FileManager");
	 	menuFileManager.setMenuText("File");
	 	menuBar.add(menuFileManager);
	 	menuFileManager.add(quitAction);
	 	
	 	MenuManager menuWindowManager = new MenuManager("Menu WindowManager");
	 	menuWindowManager.setMenuText("Window");
	 	menuBar.add(menuWindowManager);
	 	menuWindowManager.add(preferencesAction);
        // do nothing
	 	
	 
    }

    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {
    	
    	ToolBarManager toolBarManager = new ToolBarManager();
    	coolBar.add(toolBarManager);
        // do nothing
    }

	@Override
    protected void makeActions(IWorkbenchWindow window) {
        // do nothing
		{
			preferencesAction = ActionFactory.PREFERENCES.create(window);
			register(preferencesAction);
		}
		{
			quitAction = ActionFactory.QUIT.create(window);
			register(quitAction);
		}
    }

}

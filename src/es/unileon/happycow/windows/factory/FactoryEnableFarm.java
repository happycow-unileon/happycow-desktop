package es.unileon.happycow.windows.factory;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.controller.EnableFarmController;
import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.gui.PanelEnableFarm;
import javax.swing.JPanel;

/**
 * Create the panel and controller of the login window
 * @author dorian
 */
public class FactoryEnableFarm extends IFactory {
    /**
     * Panel concreto
     */
    private PanelEnableFarm panel;
    /**
     * Controlador concreto
     */
    private EnableFarmController controller;

    public FactoryEnableFarm(Parameters parameters) {
        super(parameters);
    }

    /**
     * 
     * @return 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public Controller getController() {
        if(controller==null){
            createController();
        }
        return controller;
    }
    
    /**
     * 
     * @return 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getPanel() 
     */
    @Override
    public JPanel getPanel() {
        if(panel==null){
            createPanel();
        }
        return panel;
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createController() 
     */
    @Override
    public void createController() {
        if(panel==null){
            createPanel();
        }
        
        if(controller==null){
            controller=new EnableFarmController(panel);
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel() 
     */
    @Override
    public void createPanel() {
        if(panel==null){
            panel=new PanelEnableFarm();
        }
        
        //if the controller exists, set the controller to the panel
        if(controller!=null){
            panel.setController(controller);
        }
    }
   
}

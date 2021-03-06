package es.unileon.happycow.controller;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.windows.Window;
import es.unileon.happycow.gui.PanelEnableFarm;
import es.unileon.happycow.handler.IdHandler;

/**
 * Controller of the list of disabled farms
 * @author dorian
 */
public class EnableFarmController extends ButtonFarmDetailsIController{
     /**
     * Panel concreto
     */
    private final PanelEnableFarm panel;

    /**
     * Constructor
     * @param panel 
     */
    public EnableFarmController(PanelEnableFarm panel) {
        this.panel = panel;
    }

    /**
     * Come back
     */
    public void comeBack(){
        controller.setState(Window.LIST_FARMS);
    }
    
    @Override
    public void execute(IdHandler id) {
        //habilitar en la base de datos el id seleccionado
        Database.getInstance().enableFarm(id);
        panel.changeList(Database.getInstance().getDisabledFarms());
    }

    @Override
    public void onResume(Parameters parameters) {
        panel.changeList(Database.getInstance().getDisabledFarms());
    }
    
}

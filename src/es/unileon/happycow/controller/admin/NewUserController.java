package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.admin.NewUser;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import es.unileon.happycow.model.User;

/**
 * Controller for new user
 * @author dorian
 */
public class NewUserController extends Controller{
    /**
     * panel
     */
    private final NewUser panel;

    /**
     * Constructor
     * @param panel 
     */
    public NewUserController(NewUser panel) {
        this.panel = panel;
    }
    
    /**
     * Save the user
     */
    public void saveUser(){
        String name=panel.getNameUser();
        IdHandler id=new IdUser(name);
        User user=Database.getInstance().getUser(id);
        
        if(user!=null){
            panel.setWarning("Ya existe el usuario, introduzca otro nombre.");
        }else{
            user=new User(id, panel.getPasswd(), panel.getRol());
            if(Database.getInstance().newUser(user)){
                panel.setWarning("Usuario correctamente añadido.");
            }else{
                panel.setWarning("Fallo al guardar el usuario. Pruebe otra vez.");
            }
        }
    }

}

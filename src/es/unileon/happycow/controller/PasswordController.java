package es.unileon.happycow.controller;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.database.concreteDatabase.DefaultDatabase;
import es.unileon.happycow.gui.PanelPassword;
import es.unileon.happycow.model.User;

/**
 *
 * @author dorian
 */
public class PasswordController extends IController{
    private final PanelPassword panel;

    public PasswordController(PanelPassword panel) {
        this.panel = panel;
    }
    
    public void changePassword(){
        String message="";
        if(panel.isPasswordMatch()){
            String oldPassword=DefaultDatabase.encript(panel.getOldPassword());
            User user=Database.getInstance().getUser();
            String userPassword=user.getPassword();
            if(oldPassword.compareTo(userPassword)==0){
                if(!Database.getInstance().changePassword(user.getId(), 
                        DefaultDatabase.encript(panel.getNewPassword()))){
                    message="Error cambiando la contraseña";
                }else{
                    controller.comeBack();
                }
            }else{
                message="La contraseña actual no es correcta";
            }
        }else{
            message="Las nuevas contraseñas no coinciden";
        }
        
        panel.setMessage(message);
    }
    
    public void cancel(){
        controller.comeBack();
    }

}

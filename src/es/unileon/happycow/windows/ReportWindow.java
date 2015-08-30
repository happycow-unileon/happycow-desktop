package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class ReportWindow extends IWindow{
    public static Window TYPE=Window.LOGIN;

    public ReportWindow(IFactory factory) {
        super("Reporte",true, true, new IdWindow(TYPE, false), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
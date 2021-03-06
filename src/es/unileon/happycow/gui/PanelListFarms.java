package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ButtonListFarmIController;
import es.unileon.happycow.controller.ListFarmsController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.help.HelpSystem;
import es.unileon.happycow.help.HelpTheme;
import es.unileon.happycow.model.Farm;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;

/**
 * Panel from the list of farms to select
 *
 * @author dorian
 */
public class PanelListFarms extends javax.swing.JPanel {

    /**
     * Controller of the panel
     */
    private ListFarmsController controller;

    /**
     * Constructor
     */
    public PanelListFarms() {
        initComponents();
        this.controller = null;
    }

    /**
     * set the controller
     *
     * @param controller the controller
     */
    public void setController(ListFarmsController controller) {
        this.controller = controller;
        //for every component of the panel
        for (int i = 0; i < panelList.getComponentCount(); i++) {
            //get the panel of the farm
            PanelFarmList panelFarm = (PanelFarmList) panelList.getComponent(i);
            //set the controller to the farm's panel
            panelFarm.setController((ButtonListFarmIController) controller);
        }
    }

    /**
     * Change the farm's list
     *
     * @param list new farm's list
     */
    public void changeList(List<Farm> list) {
        //remove all components
        panelList.removeAll();
        //add the new list
        addList(list);
    }

    /**
     * add the list of farms to the panel
     *
     * @param list farm's list
     */
    private void addList(List<Farm> list) {
        //if not null...
        if (list != null) {
            //for every farm
            for (Farm farm : list) {
                //create a new panel with its controller and add it to the list of the panel
                panelList.add(new PanelFarmList(farm, controller, "Pulse para gestionar la granja"));
            }
        }
    }

    /**
     * Remove a farm from the list
     *
     * @param id farm's identifier
     */
    public void removeFarm(IdHandler id) {
        //farm's index
        int target = -1;
        //looking in every component
        for (int i = 0; i < panelList.getComponentCount() && target < 0; i++) {
            //get the panel
            PanelFarmList panelFarm = (PanelFarmList) panelList.getComponent(i);
            //check the identifier
            if (panelFarm.getId().compareTo(id) == 0) {
                //if is the farm, store the index
                target = i;
            }
        }

        //if farm was found
        if (target != -1) {
            //remove it from the list
            panelList.remove(target);
            //repaint the list panel
            this.revalidate();
        }
    }

    /**
     * Add a new farm to the list
     *
     * @param farm farm to add
     */
    public void addFarm(Farm farm) {
        //create the panel
        PanelFarmList panel = new PanelFarmList(farm, null);
        //add it
        panelList.add(panel);
        //repaint the list panel
        panelList.revalidate();
    }

    /**
     * Init the components
     */
    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    /**
     * Create the components
     */
    private void createComponents() {
        scrollFarms = new javax.swing.JScrollPane();
        panelList = new javax.swing.JPanel();
        buttonNewFarm = new javax.swing.JButton("Nueva granja");
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon(
                getClass().getResource("/images/help.png")));
        buttonEnable = new javax.swing.JButton("Habilitar granja deshabilitada");
        buttonExcel = new javax.swing.JButton("Exportar a excel");

    }

    /**
     * Configure the components
     */
    private void configureComponents() {
        this.setPreferredSize(new Dimension(537, 419));
        this.setMinimumSize(new Dimension(537, 419));
        this.setMaximumSize(new Dimension(650, 500));
        //set a box layout
        BoxLayout layout = new BoxLayout(panelList, BoxLayout.Y_AXIS);
        panelList.setLayout(layout);
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
        scrollFarms.setViewportView(panelList);

    }

    /**
     * Add the events to the components
     */
    private void addEvents() {

        //button add new farm
        buttonNewFarm.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewFarmActionPerformed();
            }
        });

        //button enable button
        buttonEnable.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnableActionPerformed();
            }
        });

        //button excel
        buttonExcel.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonExcelActionPerformed();
            }
        });

        buttonHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                HelpSystem.getInstance().seeHelp(HelpTheme.ListFarm);
            }
        });
    }

    /**
     * Add the components to the panel with the layout
     */
    private void addLayout() {
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());

        // scroll farm
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollFarms, gridBagConstraints);

        //button enable
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonEnable, gridBagConstraints);

        //button new farm
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonNewFarm, gridBagConstraints);

        //button help
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);

        //button excel
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonExcel, gridBagConstraints);
    }

    /**
     * Event of new farm
     */
    private void buttonNewFarmActionPerformed() {
        controller.newFarm();
    }

    /**
     * Event of enable a disabled farm
     */
    private void buttonEnableActionPerformed() {
        controller.enableFarm();
    }

    /**
     * Event export excel
     */
    private void buttonExcelActionPerformed() {
        controller.exportExcel();
    }

    /**
     * button help
     */
    private javax.swing.JButton buttonHelp;
    /**
     * button new farm
     */
    private javax.swing.JButton buttonNewFarm;
    /**
     * button enable farm
     */
    private javax.swing.JButton buttonEnable;
    /**
     * panel of farm's list
     */
    private javax.swing.JPanel panelList;
    /**
     * scroll of the panelof list farm
     */
    private javax.swing.JScrollPane scrollFarms;
    /**
     * button to export excel
     */
    private javax.swing.JButton buttonExcel;

}

package ui.actions;

import ui.CarGameGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoadAction extends AbstractAction {
    private final CarGameGUI gui;

    public LoadAction(CarGameGUI gui) {
        super("Load Garage");
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            gui.setGarage(gui.getJsonReader().read());
            gui.refreshList();
            gui.getDetailsArea().setText("");
            gui.printToConsole("Loaded garage from " + gui.getJsonStore());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(gui, "Unable to read from file.");
        }
    }
}
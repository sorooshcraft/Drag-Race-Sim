package ui.actions;

import ui.CarGameGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class SaveAction extends AbstractAction {
    private final CarGameGUI gui;

    public SaveAction(CarGameGUI gui) {
        super("Save Garage");
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            gui.getJsonWriter().open();
            gui.getJsonWriter().write(gui.getGarage());
            gui.getJsonWriter().close();
            gui.getDetailsArea().setText("");
            gui.printToConsole("Saved garage to " + gui.getJsonStore());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(gui, "Unable to write to file.");
        }
    }
}
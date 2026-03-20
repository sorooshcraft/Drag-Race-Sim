package ui.actions;

import ui.CarGameGUI;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

// Action class to load the garage state from a JSON file
@ExcludeFromJacocoGeneratedReport
public class LoadAction extends AbstractAction {
    private final CarGameGUI gui;

    // EFFECTS: initializes the action with a name and stores the GUI reference
    public LoadAction(CarGameGUI gui) {
        super("Load Garage");
        this.gui = gui;
    }

    // MODIFIES: gui
    // EFFECTS: reads the garage from file, updates the GUI data, and refreshes the display;
    //          shows error message if file cannot be read.
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
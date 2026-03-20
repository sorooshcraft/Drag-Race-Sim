package ui.actions;

import ui.CarGameGUI;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

// Action class to save the current state of the garage to a JSON file
@ExcludeFromJacocoGeneratedReport
public class SaveAction extends AbstractAction {
    private final CarGameGUI gui;

    // EFFECTS: initializes the action with a name and stores the GUI reference
    public SaveAction(CarGameGUI gui) {
        super("Save Garage");
        this.gui = gui;
    }

    // MODIFIES: gui
    // EFFECTS: writes the garage data to the JSON store; shows error if file is not found
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